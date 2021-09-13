package amd.example.mvp.demo;


import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import amd.example.commonlibrary.util.CommonLog;
import amd.example.mvp.util.BaseUtils;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class XiaomiMarketingDemo {


    static String customer_id = "262522";
    static String appId = "464254";
    static String sign_key = "HvHYgPtFXvbieLAZ";
    static String encrypt_key = "lRBTsNjyaCUKsWax";
    static String conv_type = "APP_REGISTER";

    public static void initSign() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        String convTime = String.valueOf(System.currentTimeMillis());
        String oaid = BaseUtils.getOAID();
        map.put("oaid", oaid);
        map.put("conv_time", convTime);
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
        String propertyResult = sign_key + "&" + decodeResult;
        String md5Result = EncryptUtils.encryptMD5ToString(propertyResult).toLowerCase();
        encrypt(md5Result, queryResult);
    }

    //加密
    private static void encrypt(String md5Result, String queryResult) {
        String BaseData = queryResult + "&sign=" + md5Result;
        //变量 encrypt_key：kqkYAKhbqNNbMzTc
        String encryptKey = BaseUtils.encrypt(BaseData, encrypt_key);
//        CommonLog.e("encryptKey:" + encryptKey);
        finallyRequest(encryptKey);
    }

    //生成最终的url
    private static void finallyRequest(String encryptKey) {
        StringBuilder sb = new StringBuilder();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("appId", appId);
        map.put("info", encryptKey);
        map.put("customer_id", customer_id);
        map.put("conv_type", conv_type);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key);
            sb.append("=");
            String result = Pattern.compile("[\t\r\n]").matcher(value).replaceAll("");
            sb.append(EncodeUtils.urlEncode(result, "utf-8"));
            sb.append("&");
        }
        CommonLog.e("Url:" + "http://trail.e.mi.com/global/log?" + sb.substring(0, sb.toString().length() - 1));
    }
}
