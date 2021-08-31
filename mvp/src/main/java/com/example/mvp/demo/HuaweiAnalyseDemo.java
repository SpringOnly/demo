package com.example.mvp.demo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.example.commonlibrary.util.CommonLog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 华为归因智能分包
 */
public class HuaweiAnalyseDemo {

    private static final String url = "content://com.huawei.appmarket.commondata/item/5";
    private static final String packName = "com.example.mvc";
    //在广告位点击安装按钮的时间（毫秒）
    private static final int INDEX_ENTER_AG_TIME = 1;
    //应用安装完成的时间（毫秒）
    private static final int INDEX_INSTALLED_FINISH_TIME = 2;
    //归因信息
    private static final int INDEX_TRACKID = 4;

    private static String trackId;

    public static void queryChannel(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = Uri.parse(url);
        String[] packageName = new String[]{packName};
        Cursor cursor = null;
//        if (INDEX_ENTER_AG_TIME == 0) {
//            return "600441660";
//        }
        try {
            cursor = contentResolver.query(uri, null, null, packageName, null);
            if (cursor != null) {
                cursor.moveToNext();
                if (cursor.getColumnCount() > INDEX_TRACKID) {
                    //华为商店10.5.0.300 及之后版本
                    CommonLog.e("enter appgallery time=" + cursor.getString(INDEX_ENTER_AG_TIME));
                    CommonLog.e("install time=" + cursor.getString(INDEX_INSTALLED_FINISH_TIME));
                    CommonLog.e("track id=" + cursor.getString(INDEX_TRACKID));
                    trackId = cursor.getString(INDEX_TRACKID);
                } else {
                    //不支持归因信息查询
                    CommonLog.e("appgallery not support");
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        CommonLog.e("trackId:" + trackId);
        if (!TextUtils.isEmpty(trackId)) {
            try {
                JSONObject jsonObject = new JSONObject(trackId);
                String channel = jsonObject.getString("channel");
                String callback = jsonObject.getString("callback");
                String taskId = jsonObject.getString("taskid");
                CommonLog.e("channel:" + channel);
                CommonLog.e("callback:" + callback);
                CommonLog.e("taskid:" + taskId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
