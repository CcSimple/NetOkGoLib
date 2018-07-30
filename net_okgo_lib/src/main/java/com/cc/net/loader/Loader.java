package com.cc.net.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.cc.net.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;

/**
 * @author: Administrator on 2017-11-28 16:23
 * @github: https://github.com/CcSimple
 * 网络请求时,对话框显示,关闭
 */

public class Loader {
    //缩放比例
    private static final int LOADER_SIZE_SCALE=8;
    //偏移量
    private static final int LOADER_OFFSET_SCALE=10;

    //Dialog集合,方便统一管理
    private static final ArrayList<AppCompatDialog> LOADERS =new ArrayList<>();
    //默认样式
    private static final String DEFAULT_LOADER=LoaderStyle.BallPulseIndicator.name();


    public static void showLoading(Context context,Enum<LoaderStyle> type,boolean cancel){
        showLoading(context,type.name(),cancel);
    }

    private static void showLoading(Context context, String type,boolean cancel) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);
        dialog.setCanceledOnTouchOutside(cancel);
        dialog.setCancelable(cancel);
        int deviceWidth = DimenUtil.getScreenWidth(context);
        int deviceHeight = DimenUtil.getScreenHeight(context);

        final Window dialogWindow =dialog.getWindow();
        if (dialogWindow!=null){
            WindowManager.LayoutParams lp =dialogWindow.getAttributes();
            lp.width=deviceWidth/LOADER_SIZE_SCALE;
            lp.height=deviceHeight/LOADER_SIZE_SCALE;
            lp.height=lp.height+deviceHeight/LOADER_OFFSET_SCALE;
            lp.gravity= Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER,false);
    }
    public static void showLoading(Context context,boolean cancel){
        showLoading(context,DEFAULT_LOADER,cancel);
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS) {
            if (dialog!=null){
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
