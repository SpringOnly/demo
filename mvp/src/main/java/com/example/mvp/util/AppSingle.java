package com.example.mvp.util;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class AppSingle {
    private static AppSingle instance;
    private Context mContext;

    public AppSingle(Context context) {
        mContext = context;
    }

    public static AppSingle getInstance(Context context) {
        if (instance == null) {
            instance = new AppSingle(context);
        }
        return instance;
    }



    private static final String url = "content://com.huawei.appmarket.commondata/item/5";
    private static final String packName = "com.psd.live";
    private static final int INDEX_TRACKID = 4;
    private static String track;
    private static String taskId;

    public static String queryTaskId(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        //测试用的
//        if (TYPE_APP_ACTIVATE == 0) {
//            return "600441660";
//        }
        Uri uri = Uri.parse(url);
        String[] packageName = new String[]{packName};
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(uri, null, null, packageName, null);
            if (cursor != null) {
                cursor.moveToNext();
                if (cursor.getColumnCount() > INDEX_TRACKID) {
                    //华为商店10.5.0.300 及之后版本
                    track = cursor.getString(INDEX_TRACKID);
                } else {
                    return "600441660";
                }
            }
        } catch (Exception e) {
            return "600441660";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        if (!TextUtils.isEmpty(track)) {
            try {
                JSONObject jsonObject = new JSONObject(track);
                //渠道号
                String channel = jsonObject.optString("channel");
                //OCPD对接用的
                String callback = jsonObject.optString("callback");
                taskId = jsonObject.optString("taskid");
            } catch (JSONException e) {
                return "600441660";
            }
        }
        return taskId;
    }


}
