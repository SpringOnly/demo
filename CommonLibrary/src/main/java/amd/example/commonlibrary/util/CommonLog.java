package amd.example.commonlibrary.util;

import android.util.Log;

public class CommonLog {

    private static final String TAG = "spring";

    public static void e(String message) {
        Log.e(TAG, message + "");
    }

    public static void v(String message) {
        Log.v(TAG, message + "");
    }

    public static void w(String message) {
        Log.w(TAG, message + "");
    }
}
