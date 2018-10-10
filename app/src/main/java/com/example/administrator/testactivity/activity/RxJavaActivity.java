package com.example.administrator.testactivity.activity;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.testactivity.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        btn1 = findViewById(R.id.bt_rx1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rx1:
                rx_java2_no();
                break;
            default:
                break;
        }
    }

    /**
     * RxJava支持背压的写法
     */
    public void  rx_java2()
    {
        /**
         * 支持背压的RxJava2
         */
        Flowable.create((FlowableOnSubscribe<String>) e -> {
          if (!e.isCancelled()){

              e.onNext("test");
              e.onComplete();
          }
        }, BackpressureStrategy.DROP).subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
                Log.i("hb","onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i("hb","onSubscribe: "+s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.i("hb","onComplete");
            }
        });

    }
    
    public void rx_java2_no(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                e.onNext("test2");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String o) {
                Log.i("hb","onNext: "+o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i("hb","onComplete");
            }
        });
    }
}
