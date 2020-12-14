package br.com.coderealm.pigeon.api.services;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.coderealm.pigeon.helps.SessionManager;
import cn.pedant.SweetAlert.SweetAlertDialog;
import timber.log.Timber;

public class TestEndpoint extends AsyncTask<Integer, Integer, String> {

    private Context context;
    private SessionManager sessionManager;

    public TestEndpoint(Context context) {
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... integers) {
        String ENDPOINT = sessionManager.getKeyEndpoint();
        Timber.d("Request: " + ENDPOINT);

        StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Timber.d("Response: " + response);

                try {
                    JSONObject jObj = new JSONObject(response);
                    String title = jObj.getString("title");
                    String description = jObj.getString("description");
                    String docs = jObj.getString("docs");
                    String redoc = jObj.getString("redoc");
                    String version = jObj.getString("version");

                    if (!title.isEmpty()) {
                        new SweetAlertDialog(context)
                                .setTitleText("Endpoint Verificado!")
                                .setContentText(title + " " + version)
                                .show();
                    } else {
                        String message = jObj.getString("message");
                        new SweetAlertDialog(context)
                                .setTitleText("Endpoint não encontrado! Verifique se o mesmo está ativado.")
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Timber.e("Error: " + e.getMessage());
                    new SweetAlertDialog(context)
                            .setTitleText(e.getMessage())
                            .setContentText("Endpoint não encontrado! Verifique se o mesmo está ativado.")
                            .show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Timber.e("Error: " + error.getMessage());
                new SweetAlertDialog(context)
                        .setTitleText(error.getMessage())
                        .setContentText("Endpoint não encontrado! Verifique se o mesmo está ativado.")
                        .show();
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
