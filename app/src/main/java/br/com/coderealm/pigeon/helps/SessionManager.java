package br.com.coderealm.pigeon.helps;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREFERENCES_NAME = "pingeon_sms_gateway";
    private static final String KEY_STATUS_GATEWAY = "status_gateway";
    private static final String KEY_ENDPOINT = "endpoint";
    private static final String KEY_INTERVAL = "interval";
    private static final String KEY_DEVICE_KEY = "device_key";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_TOKEN_TYPE = "token_type";

    private Context context;
    private int PRIVATE_MODE = 0;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Utils utils;


    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
        utils = new Utils(context);

        setKeyDeviceKey(utils.getAndroidID());
    }

    public boolean getKeyStatusGateway() {
        return sharedPreferences.getBoolean(KEY_STATUS_GATEWAY, false);
    }

    public void setKeyStatusGateway(boolean status_gateway) {
        editor.putBoolean(KEY_STATUS_GATEWAY, status_gateway);
        editor.commit();
    }

    public String getKeyEndpoint() {
        return sharedPreferences.getString(KEY_ENDPOINT, "http://127.0.0.1:1404");
    }

    public void setKeyEndpoint(String url) {
        editor.putString(KEY_ENDPOINT, url);
        editor.commit();
    }

    public String getKeyInterval() {
        return sharedPreferences.getString(KEY_INTERVAL, "60000");
    }

    public void setKeyInterval(String interval) {
        editor.putString(KEY_INTERVAL, interval);
        editor.commit();
    }

    public String getKeyDeviceKey() {
        return sharedPreferences.getString(KEY_DEVICE_KEY, utils.getAndroidID());
    }

    public void setKeyDeviceKey(String deviceKey) {
        editor.putString(KEY_DEVICE_KEY, deviceKey);
        editor.commit();
    }

    public String getKeyAccessToken() {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, "");
    }

    public void setKeyAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getKeyTokenType() {
        return sharedPreferences.getString(KEY_TOKEN_TYPE, "");
    }

    public void setKeyTokenType(String tokenType) {
        editor.putString(KEY_TOKEN_TYPE, tokenType);
        editor.commit();
    }


}
