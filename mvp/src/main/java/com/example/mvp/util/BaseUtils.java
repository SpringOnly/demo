package com.example.mvp.util;

import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.Base64;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.UUID;


public class BaseUtils {
    /**
     * 返回真实设备标识
     */
    public static String getOAID() {
//        String imei = getPhoneImei();
//        if (!IMEI_UNKNOWN.equals(imei)) {
//            return imei;
//        }

        String id = DeviceUtils.getAndroidID();
        if (!TextUtils.isEmpty(id)) {
            return id;
        }

        return UUID.randomUUID().toString();
    }

    public static final String IMEI_UNKNOWN = "unknown";

    @Nullable
    private static String getPhoneImei() {
        try {
            return PhoneUtils.getIMEI();
        } catch (SecurityException e) {
            return IMEI_UNKNOWN;
        }
    }

    /**
     * 异或运算
     */
    public static String encrypt(String info, String key) {
        try {
            if (StringUtils.isEmpty(info) || StringUtils.isEmpty(key)) {
                return null;
            }
            char[] infoChar = info.toCharArray();
            char[] keyChar = key.toCharArray();
            byte[] resultChar = new byte[infoChar.length];
            for (int i = 0; i < infoChar.length; i++) {
                resultChar[i] = (byte) ((infoChar[i] ^ keyChar[i % keyChar.length]) & 0xFF);
            }
            return Base64.encodeToString(resultChar, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }

}