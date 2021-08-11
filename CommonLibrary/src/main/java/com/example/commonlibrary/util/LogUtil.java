package com.example.commonlibrary.util;

import android.util.Log;

public class LogUtil {

    public static void e(String message) {
        Log.e("spring", message + "");
    }

    public static void v(String message) {
        Log.v("spring", message + "");
    }

    public static void w(String message) {
        Log.w("spring", message + "");
    }
}
