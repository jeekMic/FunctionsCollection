package com.example.administrator.testactivity.activity;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.testactivity.MainActivity;
import com.example.administrator.testactivity.R;
import com.example.administrator.testactivity.bean.DownloadInfo;
import com.example.administrator.testactivity.custom.DownloadManager;
import com.example.administrator.testactivity.custom.DownloadObserver;


public class MyDownloadThread extends AppCompatActivity implements View.OnClickListener {
    private Button downloadBtn1, downloadBtn2, downloadBtn3;
    private Button cancelBtn1, cancelBtn2, cancelBtn3;
    private ProgressBar progress1, progress2, progress3;
    private String url1 = "http://192.168.60.26:8089/test.zip";
    private String url2 = "http://192.168.60.26:8089/VisioViewer.exe";
    private String url3 = "http://192.168.60.26:8089/ArcGIS.pdf";
/*
    private String url1 = "http://shouji.360tpcdn.com/180726/36163ebbbbfcbe617a772c7cebaed556/com.tencent.mobileqq_884.apk";
    private String url2 = "http://shouji.360tpcdn.com/180726/36163ebbbbfcbe617a772c7cebaed556/com.tencent.mobileqq_884.apk";
    private String url3 = "http://shouji.360tpcdn.com/180726/36163ebbbbfcbe617a772c7cebaed556/com.tencent.mobileqq_884.apk";
*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_download_thread);
        initView();
    }

    private void initView() {
        downloadBtn1 = findViewById(R.id.main_button_down1);
        downloadBtn2 = findViewById(R.id.main_button_down2);
        downloadBtn3 = findViewById(R.id.main_button_down3);

        cancelBtn1 = findViewById(R.id.main_button_cancel1);
        cancelBtn2 = findViewById(R.id.main_button_cancel2);
        cancelBtn3 = findViewById(R.id.main_button_cancel3);

        progress1 = findViewById(R.id.main_progress1);
        progress2 = findViewById(R.id.main_progress2);
        progress3 = findViewById(R.id.main_progress3);

        downloadBtn1.setOnClickListener(this);
        downloadBtn2.setOnClickListener(this);
        downloadBtn3.setOnClickListener(this);

        cancelBtn1.setOnClickListener(this);
        cancelBtn2.setOnClickListener(this);
        cancelBtn3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_button_down1:
                DownloadManager.getInstance().download(url1, new DownloadObserver() {
                    @Override
                    public void onNext(DownloadInfo value) {
                        super.onNext(value);
                        progress1.setMax((int) value.getTotal());
                        progress1.setProgress((int) value.getProgress());
                    }

                    @Override
                    public void onComplete() {
                        if(downloadInfo != null){
                            Toast.makeText(MyDownloadThread.this,
                                    downloadInfo.getFilename() + "-DownloadComplete",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.main_button_down2:
                DownloadManager.getInstance().download(url2, new DownloadObserver() {
                    @Override
                    public void onNext(DownloadInfo value) {
                        super.onNext(value);
                        progress2.setMax((int) value.getTotal());
                        progress2.setProgress((int) value.getProgress());
                    }

                    @Override
                    public void onComplete() {
                        if(downloadInfo != null){
                            Toast.makeText(MyDownloadThread.this,
                                    downloadInfo.getFilename() + Uri.encode("下载完成"),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.main_button_down3:
                DownloadManager.getInstance().download(url3, new DownloadObserver() {
                    @Override
                    public void onNext(DownloadInfo value) {
                        super.onNext(value);
                        progress3.setMax((int) value.getTotal());
                        progress3.setProgress((int) value.getProgress());
                    }

                    @Override
                    public void onComplete() {
                        if(downloadInfo != null){
                            Toast.makeText(MyDownloadThread.this,
                                    downloadInfo.getFilename() + "下载完成",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.main_button_cancel1:
                DownloadManager.getInstance().cancel(url1);
                break;
            case R.id.main_button_cancel2:
                DownloadManager.getInstance().cancel(url2);
                break;
            case R.id.main_button_cancel3:
                DownloadManager.getInstance().cancel(url3);
                break;
            default:
                    break;
        }
    }
}
