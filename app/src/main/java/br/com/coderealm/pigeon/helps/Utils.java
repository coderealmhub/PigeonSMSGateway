package br.com.coderealm.pigeon.helps;

import android.content.Context;
import android.provider.Settings;

public class Utils {

    private Context context;

    public Utils(Context context){
        this.context = context;
    }

    public String getAndroidID(){
        String androidId = Settings.Secure.getString(this.context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return androidId;
    }

}
