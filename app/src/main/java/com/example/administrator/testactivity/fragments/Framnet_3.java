package com.example.administrator.testactivity.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.testactivity.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class Framnet_3 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view  = inflater.inflate(R.layout.fragment_three,container, false);
        start();
        return view;
    }

    public void  start(){
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("test1");
                e.onNext("test2");
                e.onNext("test3");

                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("Framnet_3     ","onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i("Framnet_3       ",s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("Framnet_3       ","onError");
            }

            @Override
            public void onComplete() {
                Log.i("Framnet_3       ","onComplete");
            }
        });
    }
}
