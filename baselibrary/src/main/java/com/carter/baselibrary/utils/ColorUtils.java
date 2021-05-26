package com.carter.baselibrary.utils;

import android.graphics.Color;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

import com.carter.baselibrary.BaseApplication;

/**
 * 颜色处理工具类
 */
public class ColorUtils {
    /**
     * 解析颜色
     */
    public static int parseColor(String colorStr, int defaultColor) {
        if (TextUtils.isEmpty(colorStr)) {
            return defaultColor;
        }
        try {
            if (!colorStr.startsWith("#")) {
                colorStr = "#" + colorStr;
            }
            int color = Color.parseColor(colorStr);
            return color;
        } catch (Exception e) {
            return defaultColor;
        }
    }

    public static int parseColor(String colorStr) {
        if (TextUtils.isEmpty(colorStr)) {
            return 0;
        }
        try {
            if (!colorStr.startsWith("#")) {
                colorStr = "#" + colorStr;
            }
            return Color.parseColor(colorStr);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 解析颜色
     */
    public static int parseColor(int color) {
        return ContextCompat.getColor(BaseApplication.Companion.getContext(), color);
    }

    /**
     * 设置html字体色值
     */
    public static String setTextColor(String text, String color) {
        return "<font color=#" + color + ">" + text + "</font>";
    }

}
