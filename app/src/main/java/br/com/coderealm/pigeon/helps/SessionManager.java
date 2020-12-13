package br.com.coderealm.pigeon.helps;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREFERENCES_NAME = "PingeonSMSGateway";
    private static final String KEY_SEND_URL = "send_url";
    private static final String KEY_RECEIVE_URL = "receive_url";
    private static final String KEY_STATUS_URL = "status_url";
    private static final String KEY_INTERVAL = "interval";
    private static final String KEY_DEVICE_KEY = "device_KEY";
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

    public String getKeySendUrl() {
        return sharedPreferences.getString(KEY_SEND_URL, "https://localhost/api/send");
    }

    public void setKeySendUrl(String url) {
        editor.putString(KEY_SEND_URL, url);
        editor.commit();
    }

    public String getKeyReceiveUrl() {
        return sharedPreferences.getString(KEY_RECEIVE_URL, "https://localhost/api/receive");
    }

    public void setKeyReceiveUrl(String url) {
        editor.putString(KEY_RECEIVE_URL, url);
        editor.commit();
    }

    public String getKeyStatusUrl() {
        return sharedPreferences.getString(KEY_STATUS_URL, "https://localhost/api/status");
    }

    public void setKeyStatusUrl(String url) {
        editor.putString(KEY_STATUS_URL, url);
        editor.commit();
    }

    public String getKeyInterval() {
        return sharedPreferences.getString(KEY_INTERVAL, "1");
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


}
