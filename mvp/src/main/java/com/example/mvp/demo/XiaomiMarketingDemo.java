package com.example.mvp.demo;


import android.text.TextUtils;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.example.mvp.util.BaseUtils;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class XiaomiMarketingDemo {

    public static void initSign() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        String ipAddress = !TextUtils.isEmpty(NetworkUtils.getIPAddress(true)) ? NetworkUtils.getIPAddress(true) : "";
        String convTime = String.valueOf(System.currentTimeMillis());
        String imei = BaseUtils.getOAID();

//        map.put("oaid", imei);
//        map.put("conv_time", convTime);
//        map.put("client_ip",ipAddress);
        map.put("imei", "91b9185dba1772851dd02b276a6c969e");
        map.put("conv_time", "1504687208890");
        map.put("client_ip", "127.0.0.1");
        joint(map);

    }

    //参数拼接签名
    private static void joint(@NotNull LinkedHashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key);
            sb.append("=");
            sb.append(value);
            sb.append("&");
        }
        String queryResult = sb.substring(0, sb.toString().length() - 1);
        String decodeResult;
        decodeResult = EncodeUtils.urlEncode(queryResult);
        //变量 sign_key:UyXPckwPOraTlyxZ
        String propertyResult = "UyXPckwPOraTlyxZ" + "&" + decodeResult;
        String md5Result = EncryptUtils.encryptMD5ToString(propertyResult).toLowerCase();
        encrypt(md5Result, queryResult);
    }

    //加密
    private static void encrypt(String md5Result, String queryResult) {
        String BaseData = queryResult + "&sign=" + md5Result;
        //变量 encrypt_key：kqkYAKhbqNNbMzTc
        String encryptKey = BaseUtils.encrypt(BaseData, "kqkYAKhbqNNbMzTc");
//        LogUtil.e("encryptKey:" + encryptKey);
        finallyRequest(encryptKey);
    }

    //生成最终的请求
    private static void finallyRequest(String encryptKey) {
        StringBuilder sb = new StringBuilder();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("appId", "136");
        map.put("info", encryptKey);
        map.put("customer_id", "47522");
        map.put("conv_type", "APP_ACTIVE");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key);
            sb.append("=");
            String result = Pattern.compile("[\t\r\n]").matcher(value).replaceAll("");
            sb.append(EncodeUtils.urlEncode(result, "utf-8"));
            sb.append("&");
        }
//        LogUtil.e("aaaa:" + sb.substring(0, sb.toString().length() - 1));
    }
}
