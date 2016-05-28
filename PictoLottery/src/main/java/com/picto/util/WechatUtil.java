package com.picto.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by wujigang on 2016/5/22.
 */
public class WechatUtil {
    private static final String APP_ID = "wx8f4239ff75ca1770";
    private static final String APP_SECRET = "43980f0ded91775f6e505c97657e7789";
    public static String getOpenIdByCode(String code) throws JSONException, IOException {
        String charset = "UTF-8";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret="
                + APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
        String message = HttpsUtil.get(url, charset);
        JSONObject accessTokenJson = new JSONObject(message);
        return getStringFromJson(accessTokenJson, "openid");
    }

    public static String getStringFromJson(JSONObject json, String key) throws JSONException {
        if (json.has(key)) {
            return json.getString(key);
        }
        return null;
    }

    public static void main(String[] args) throws IOException, JSONException {
        String code = "041gA3aR056F4e2iKqdR0KnX9R0gA3ao";
        System.out.println(getOpenIdByCode(code));
    }
}
