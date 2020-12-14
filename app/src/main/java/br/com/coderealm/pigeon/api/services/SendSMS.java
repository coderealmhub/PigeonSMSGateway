package br.com.coderealm.pigeon.api.services;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.com.coderealm.pigeon.helps.SessionManager;
import timber.log.Timber;

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
        String SEND_URL = sessionManager.getKeyEndpoint() + "/send/" + sessionManager.getKeyDeviceKey();
        Timber.d("Request: " + SEND_URL);

        StringRequest request = new StringRequest(Request.Method.GET, SEND_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Timber.d("Response: " + response);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        final JSONArray jsonArray = jObj.getJSONArray("response");

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String uuid = jsonObject.getString("uuid");
                            String number = jsonObject.getString("number");
                            String message = jsonObject.getString("message");

                            if(new br.com.coderealm.pigeon.helps.SendSMS(number, message).send()){

                            }else{

                            }
                        }
                    } else {
                        String message = jObj.getString("message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Timber.e("Error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Timber.e("Error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

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
