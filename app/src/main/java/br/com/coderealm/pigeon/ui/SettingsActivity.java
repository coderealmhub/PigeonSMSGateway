package br.com.coderealm.pigeon.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import br.com.coderealm.pigeon.R;
import br.com.coderealm.pigeon.helps.SessionManager;

public class SettingsActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private Switch btn_switch;
    private EditText send_url, receive_url, status_url, interval;
    private TextView device_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Configurações");

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Settings Save!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });*/

        send_url = findViewById(R.id.send_url);
        receive_url = findViewById(R.id.receive_url);
        status_url = findViewById(R.id.status_url);
        interval = findViewById(R.id.interval);
        device_id = findViewById(R.id.device_id);

        sessionManager = new SessionManager(SettingsActivity.this);

        send_url.setText(sessionManager.getKeySendUrl());
        receive_url.setText(sessionManager.getKeyReceiveUrl());
        status_url.setText(sessionManager.getKeyStatusUrl());
        interval.setText(sessionManager.getKeyInterval());
        device_id.setText(sessionManager.getKeyDeviceKey());

        device_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", sessionManager.getKeyDeviceKey());
                clipboard.setPrimaryClip(clip);

                Snackbar.make(v, "ID copiado!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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