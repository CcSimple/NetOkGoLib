package com.cc.net.loader;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author: Administrator on 2017-11-28 16:31
 * @github: https://github.com/CcSimple
 */

public class DimenUtil {
    /**
     * 屏幕宽度
     * @return
     */
    public static int getScreenWidth(Context context){
        final Resources resources = context.getResources();
        final DisplayMetrics dm= resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    /**
     * 屏幕高度
     * @return
     */
    public static int getScreenHeight(Context context){
        final Resources resources = context.getResources();
        final DisplayMetrics dm= resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    // 将px值转换为dip或dp值，保证尺寸大小不变
    public static int px2dip(Context context,float pxValue) {
        final Resources resources = context.getResources();
        final float scale = resources.getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 将dip或dp值转换为px值，保证尺寸大小不变
    public static int dip2px(Context context,float dipValue) {
        final Resources resources = context.getResources();
        final float scale = resources.getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    // 将px值转换为sp值，保证文字大小不变
    public static int px2sp(Context context,float pxValue) {
        final Resources resources = context.getResources();
        final float fontScale = resources.getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    // 将sp值转换为px值，保证文字大小不变
    public static int sp2px(Context context,float spValue) {
        final Resources resources = context.getResources();
        final float fontScale = resources.getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }





}
