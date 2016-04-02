package br.com.polilibras.aprendalibras;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ConfigActivity extends AppCompatActivity {

    private TextView textView;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "br.com.polilibras.aprendalibras",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        FacebookSdk.sdkInitialize(getApplicationContext());
        final boolean loggedIn = AccessToken.getCurrentAccessToken() != null;
        callbackManager = CallbackManager.Factory.create();
        textView = (TextView) findViewById(R.id.name);
        loginButton.setReadPermissions(Arrays.asList("public_profile, user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("LBR-FB", "login facebook realizado com sucesso");
                showName(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("LBR-FB", "login facebook cancelado");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("LBR-FB", "login facebook erro");
            }
        });
        if (loggedIn == false) {
            textView.setVisibility(View.INVISIBLE);
        } else {
                showName(AccessToken.getCurrentAccessToken());
        }
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    //write your code here what to do when user logout
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        };

        accessTokenTracker.startTracking();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void showName (AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("LoginActivity", response.toString());
                        try {
                            String name = object.getString("name");
                            textView.setVisibility(View.VISIBLE);
                            textView.setText(getString(R.string.name, name));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
