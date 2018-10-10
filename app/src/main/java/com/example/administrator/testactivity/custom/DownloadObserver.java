package com.example.administrator.testactivity.custom;

import com.example.administrator.testactivity.bean.DownloadInfo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DownloadObserver implements Observer<DownloadInfo>{
    protected Disposable d; //用于取消注册的坚挺着
    protected DownloadInfo downloadInfo;


    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
