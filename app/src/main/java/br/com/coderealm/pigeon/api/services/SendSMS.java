package br.com.coderealm.pigeon.api.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import br.com.coderealm.pigeon.helps.SessionManager;

public class SendSMS extends AsyncTask<Integer, Integer, String> {

    private Context context;
    private SessionManager sessionManager;

    public SendSMS(Context context) {
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... integers) {
        String SEND_URL = sessionManager.getKeySendUrl() + "?device_id=" + sessionManager.getKeyDeviceKey();

        Log.i("", "REQUEST: " + SEND_URL);
        int count = 0;
        while (count < 10) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count++;
            Log.i(">>> ", "COUNT: " + count);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
    }

}
