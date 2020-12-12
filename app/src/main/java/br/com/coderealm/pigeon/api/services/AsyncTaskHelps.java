package br.com.coderealm.pigeon.api.services;

import android.os.AsyncTask;

public class AsyncTaskHelps extends AsyncTask<String, String, String> {

    public AsyncTaskHelps(){}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    protected void onPostExecute(String string) {
        super.onPostExecute(string);
    }


}
