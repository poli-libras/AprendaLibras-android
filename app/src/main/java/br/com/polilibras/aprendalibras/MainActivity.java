package br.com.polilibras.aprendalibras;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.facebook.FacebookSdk;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.main_activity);

        View playBtn = findViewById(R.id.iniciar_btn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, QuestionActivity.class);
                startActivity(intent);
            }
        });

    }
    public void config_button(View view) {
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
    }
}
