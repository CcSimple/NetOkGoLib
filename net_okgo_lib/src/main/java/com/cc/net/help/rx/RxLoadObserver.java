package com.cc.net.help.rx;

import android.content.Context;

import com.cc.net.loader.Loader;
import com.cc.net.loader.LoaderStyle;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: Administrator on 2018-04-13 15:27
 * @github: https://github.com/CcSimple
 */

public abstract class RxLoadObserver<T>  implements Observer<T> {
    private LoaderStyle mStyle;
    private boolean mCancel;
    private Context mContext;

    public RxLoadObserver(Context context) {
        this.mContext=context;
        this.mCancel = false;
    }

    public RxLoadObserver(Context mContext,boolean mCancel) {
        this.mContext = mContext;
        this.mCancel = mCancel;
    }

    public RxLoadObserver(Context context, LoaderStyle style, boolean cancel) {
        this.mContext=context;
        this.mStyle=style;
        this.mCancel=cancel;
    }


    @Override
    public void onSubscribe(Disposable d){
        if (mStyle!=null){
            Loader.showLoading(mContext,mStyle,mCancel);
        }else {
            Loader.showLoading(mContext,mCancel);
        }
        _onSubscribe(d);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        Loader.stopLoading();
        _onError(e.getMessage());
    }
    @Override
    public void onComplete() {
        Loader.stopLoading();
        _onComplete();
    }


    public void _onSubscribe(Disposable d) {

    }
    public void _onComplete() {

    }
    //必须实现
    public abstract void _onNext(T t);
    public abstract void _onError(String e);

}
