package com.example.lvzhenwei.rxandroid2demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends Activity {

    private Observable<String> mObservable = null;

    private Observer<String> mObserver = null;

    private TextView mLogCat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogCat = (TextView) findViewById(R.id.mLogCat);
    }

    //创建被观察者
    private void createObservable(){
        printLogCat("createObservable");

        mObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                printLogCat("subscribe... e.onNext()...");
                emitter.onNext("Zyao89");
                emitter.onComplete();
                printLogCat("subscribe... e.onComplete()...");
            }

        });
    }
    //创建观察者
    private void createObserver(){
       printLogCat("createObserver");
        mObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                printLogCat("onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                printLogCat("onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printLogCat("onError");
            }

            @Override
            public void onComplete() {
                printLogCat("onComplete");
            }
        };
    }

    private void subscrible(){
        if (mObservable != null && mObserver != null)
        {

            printLogCat("subscribe...  mObservable.subscribe(mObserver)");
            mObservable.subscribe(mObserver);
        }

    }

    //订阅


    private void printLogCat(Object... texts)
    {
        for (Object text : texts)
        {
            mLogCat.append(text + "\n");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        subscrible();
        return super.onTouchEvent(event);
    }
}
