package com.example.administrator.testactivity.custom;

import android.util.Log;

import com.example.administrator.testactivity.MyApp;
import com.example.administrator.testactivity.bean.DownloadInfo;
import com.example.administrator.testactivity.utils.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadManager {
    private static final AtomicReference<DownloadManager> INSTANCE = new AtomicReference<>();
    private HashMap<String, Call> downCalls;//用来存放各个下载的请求
    private OkHttpClient mClient;//OKHttpClient;

    //获得一个单例类
    public static DownloadManager getInstance() {
        for (; ; ) {
            DownloadManager current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new DownloadManager();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    private DownloadManager() {
        downCalls = new HashMap<>();
        mClient = new OkHttpClient.Builder().build();
    }

    /**
     * 开始下载
     *
     * @param url              下载请求的网址
     * @param downLoadObserver 用来回调的接口
     */
    public void download(String url, DownloadObserver downLoadObserver) {
        Observable.just(url)
                //call的map已经有了,就证明正在下载,则这次不下载
                .filter(s -> downCalls.containsKey(s))
                .flatMap(s -> Observable.just(createDownInfo(s)))
                //检测本地文件夹,生成新的文件名
                .map(this::getRealFileName)
                //下载
                .flatMap(downloadInfo -> Observable.create(new DownloadSubscribe(downloadInfo)))
                //在主线程回调
                .observeOn(AndroidSchedulers.mainThread())
                //在子线程执行
                .subscribeOn(Schedulers.io())
                //添加观察者
                .subscribe(downLoadObserver);

    }

    public void cancel(String url) {
        Call call = downCalls.get(url);
        if (call != null) {
            call.cancel();//取消
        }
        downCalls.remove(url);
    }

    /**
     * 创建DownInfo
     *
     * @param url 请求网址
     * @return DownInfo
     */
    private DownloadInfo createDownInfo(String url) {
        DownloadInfo downloadInfo = new DownloadInfo(url);
        //获得文件大小
        long contentLength = getContentLength(url);
        downloadInfo.setTotal(contentLength);
        String fileName = url.substring(url.lastIndexOf("/"));
        downloadInfo.setFilename(fileName);
        return downloadInfo;
    }

    private DownloadInfo getRealFileName(DownloadInfo downloadInfo) {
        String fileName = downloadInfo.getFilename();
        long downloadLength = 0, contentLength = downloadInfo.getTotal();
        Log.e("path-----------",fileName);
        File file = new File(MyApp.sContext.getFilesDir(), fileName);
        if (file.exists()) {
            //找到了文件,代表已经下载过,则获取其长度
            downloadLength = file.length();
        }
        //之前下载过,需要重新来一个文件
        int i = 1;
        while (downloadLength >= contentLength) {
            int dotIndex = fileName.lastIndexOf(".");
            String fileNameOther;
            if (dotIndex == -1) {
                fileNameOther = fileName + "(" + i + ")";
            } else {
                fileNameOther = fileName.substring(0, dotIndex)
                        + "(" + i + ")" + fileName.substring(dotIndex);
            }
            File newFile = new File(MyApp.sContext.getFilesDir(), fileNameOther);
            file = newFile;
            downloadLength = newFile.length();
            i++;
        }
        //设置改变过的文件名/大小
        downloadInfo.setProgress(downloadLength);
        downloadInfo.setFilename(file.getName());
        return downloadInfo;
    }

    private class DownloadSubscribe implements ObservableOnSubscribe<DownloadInfo> {
        private DownloadInfo downloadInfo;

        public DownloadSubscribe(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
        }

        @Override
        public void subscribe(ObservableEmitter<DownloadInfo> e) throws Exception {
            String url = downloadInfo.getUrl();
            //已经下载好的长度
            long downloadLength = downloadInfo.getProgress();
            //文件的总长度
            long contentLength = downloadInfo.getTotal();
            //初始进度信息
            e.onNext(downloadInfo);

            Request request = new Request.Builder()
                    //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
                    .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
                    .url(url)
                    .build();
            Call call = mClient.newCall(request);
            //把这个添加到call里,方便取消
            downCalls.put(url, call);
            Response response = call.execute();

            File file = new File(MyApp.sContext.getFilesDir(), downloadInfo.getFilename());
            InputStream is = null;
            FileOutputStream fileOutputStream = null;
            try {
                is = response.body().byteStream();
                fileOutputStream = new FileOutputStream(file, true);
                //缓冲数组2kB
                byte[] buffer = new byte[2048];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    downloadLength += len;
                    downloadInfo.setProgress(downloadLength);
                    e.onNext(downloadInfo);
                }
                fileOutputStream.flush();
                downCalls.remove(url);
            } finally {
                //关闭IO流
                IOUtil.closeAll(is, fileOutputStream);

            }
            e.onComplete();//完成
        }
    }

    /**
     * 获取下载长度
     *
     * @param downloadUrl
     * @return
     */
    private long getContentLength(String downloadUrl){
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength == 0 ? DownloadInfo.TOTAL_ERROR : contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DownloadInfo.TOTAL_ERROR;
    }


}
