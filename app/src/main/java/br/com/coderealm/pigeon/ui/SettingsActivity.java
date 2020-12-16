package br.com.coderealm.pigeon.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import br.com.coderealm.pigeon.R;
import br.com.coderealm.pigeon.api.services.TestEndpoint;
import br.com.coderealm.pigeon.helps.SessionManager;

public class SettingsActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private EditText endpoint, interval;
    private TextView device_id;
    private Button save_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        endpoint = findViewById(R.id.endpoint);
        interval = findViewById(R.id.interval);
        device_id = findViewById(R.id.device_id);
        save_settings = findViewById(R.id.btn_save);

        sessionManager = new SessionManager(SettingsActivity.this);

        endpoint.setText(sessionManager.getKeyEndpoint());
        interval.setText(sessionManager.getKeyInterval());
        device_id.setText(sessionManager.getKeyDeviceKey());

        device_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", sessionManager.getKeyDeviceKey());
                clipboard.setPrimaryClip(clip);

                Snackbar.make(v, R.string.copied, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        save_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestEndpoint(SettingsActivity.this).execute();
                sessionManager.setKeyEndpoint(endpoint.getText().toString().trim());
                sessionManager.setKeyInterval(interval.getText().toString().trim());
                Snackbar.make(v, R.string.saved_settings, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }

}