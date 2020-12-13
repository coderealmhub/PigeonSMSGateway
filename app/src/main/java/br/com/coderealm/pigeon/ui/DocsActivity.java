package br.com.coderealm.pigeon.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.coderealm.pigeon.R;

public class DocsActivity extends AppCompatActivity {

    private Button btn_docs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docs);

        TextView text_url = (TextView) findViewById(R.id.url_coderealm);
        text_url.setMovementMethod(LinkMovementMethod.getInstance());

        btn_docs = (Button) findViewById(R.id.btn_docs);
        btn_docs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getIntent().ACTION_VIEW, Uri.parse("https://github.com/coderealmhub/PigeonSMSGateway")));
            }
        });
    }
}