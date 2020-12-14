package br.com.coderealm.pigeon.helps;

import android.telephony.SmsManager;

import timber.log.Timber;

public class SendSMS {

    private String number;
    private String message;

    public SendSMS(String number, String message) {
        this.number = number;
        this.message = message;
    }

    public boolean send() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            Timber.d("SMS sended!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Timber.e("SMS send falid! " + e.getMessage());
            return false;
        }
    }

}
