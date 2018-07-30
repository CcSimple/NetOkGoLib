package com.cc.net.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @author: Administrator on 2018-04-18 16:31
 * @github: https://github.com/CcSimple
 * 反射获取AVLoadingIndicatorView 中的样式
 */

public final class LoaderCreator {
    private static final WeakHashMap<String,Indicator> LOADING_MAP = new WeakHashMap<>();

    //缓存方式获取
    static AVLoadingIndicatorView create(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView =new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type)==null){
            final Indicator indicator =getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    //反射获取AVLoadingIndicatorView
    private static Indicator getIndicator(String name){
        if (name==null||name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName =new StringBuilder();
        if (!name.contains(".")){
            final String defaultPackageName =AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
