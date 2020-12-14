package br.com.coderealm.pigeon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import br.com.coderealm.pigeon.BuildConfig;
import br.com.coderealm.pigeon.R;
import br.com.coderealm.pigeon.helps.Network;
import br.com.coderealm.pigeon.helps.ServiceGateway;
import br.com.coderealm.pigeon.helps.SessionManager;
import cn.pedant.SweetAlert.SweetAlertDialog;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private Switch btn_switch;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        sessionManager = new SessionManager(MainActivity.this);

        ImageView btn_send_sms = findViewById(R.id.send_sms);
        btn_send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SMSActivity.class));
            }
        });

        ImageView btn_settings = findViewById(R.id.settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        ImageView btn_documentation = findViewById(R.id.documentation);
        btn_documentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DocsActivity.class));
            }
        });

        btn_switch = findViewById(R.id.btn_switch);
        btn_switch.setChecked(sessionManager.getKeyStatusGateway());
        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionManager.getKeyStatusGateway()) {
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Desconectar")
                            .setContentText("Tem certeza que você deseja desconectar o Gateway? As SMS deixarão de ser enviadas!")
                            .setConfirmText("Parar")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    stopService(new Intent(MainActivity.this, ServiceGateway.class));
                                    sessionManager.setKeyStatusGateway(false);
                                }
                            }).show();
                    btn_switch.setChecked(sessionManager.getKeyStatusGateway());
                } else {
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Conectar")
                            .setContentText("Ao iniciar o Gateway seus créditos ou bonus seram consumidos para envio de SMS!")
                            .setConfirmText("Iniciar")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();

                                    if (new Network().isNetworkConnected(getApplicationContext())) {
                                        startService(new Intent(MainActivity.this, ServiceGateway.class));
                                        sessionManager.setKeyStatusGateway(true);
                                        btn_switch.setChecked(sessionManager.getKeyStatusGateway());
                                    } else {
                                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("Oops...")
                                                .setContentText("Parace que você está sem conexão com a internet :(")
                                                .show();
                                    }
                                }
                            }).show();
                    btn_switch.setChecked(sessionManager.getKeyStatusGateway());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            // Toast.makeText(MainActivity.this, "Configurações", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}