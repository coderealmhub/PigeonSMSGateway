package br.com.coderealm.pigeon.helps;

import android.telephony.SmsManager;
import android.widget.Toast;

import br.com.coderealm.pigeon.R;

public class SendSMS {

    private String number;
    private String message;

    public SendSMS(String number, String message){
        this.number = number;
        this.message = message;
    }

    public boolean send(){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
