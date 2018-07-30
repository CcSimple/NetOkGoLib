package com.cc.net.callback;

import android.content.Context;

import com.cc.net.loader.Loader;
import com.cc.net.loader.LoaderStyle;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * @author: Administrator on 2018-07-30 10:39
 * @github: https://github.com/CcSimple
 * @describe: 带加载框的文本回调
 */

public abstract class StringLoadCallback extends AbsCallback<String> {
    private LoaderStyle mStyle;
    private boolean mCancel;
    private Context mContext;
    private StringConvert convert;
    public StringLoadCallback(Context context) {
        this.mContext=context;
        this.mCancel = false;
        convert = new StringConvert();
    }
    public StringLoadCallback(Context mContext,boolean mCancel) {
        convert = new StringConvert();
        this.mContext = mContext;
        this.mCancel = mCancel;
    }
    public StringLoadCallback(Context context, LoaderStyle style, boolean cancel) {
        convert = new StringConvert();
        this.mContext=context;
        this.mStyle=style;
        this.mCancel=cancel;
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        if (mStyle!=null){
            Loader.showLoading(mContext,mStyle,mCancel);
        }else {
            Loader.showLoading(mContext,mCancel);
        }
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        Loader.stopLoading();
    }

    @Override
    public void onFinish() {
        super.onFinish();
        Loader.stopLoading();
    }

    @Override
    public String convertResponse(okhttp3.Response response) throws Throwable {
        String s = convert.convertResponse(response);
        response.close();
        return s;
    }
}
