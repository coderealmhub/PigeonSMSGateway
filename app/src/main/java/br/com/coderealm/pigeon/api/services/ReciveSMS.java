package br.com.coderealm.pigeon.api.services;

import android.content.Context;
import android.os.AsyncTask;

public class ReciveSMS extends AsyncTask<Integer, Integer, String> {

    private Context context;

    public ReciveSMS(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... integers) {
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
