package com.example.gaominyu.slease.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gaominyu.slease.Main.BrowseActivity;
import com.example.gaominyu.slease.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private LoginButton mFacebookLoginBtn;
    private Button mLoginBtn;

    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;
    private Bundle userData;

    private static final String TAG;

    static {
        TAG = "FACEBOOK_LOG";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Declare layout element and Auth instances
        mFacebookLoginBtn = findViewById(R.id.btn_facebookLogin) ;
        mLoginBtn = findViewById(R.id.loginBtn);
        mAuth = FirebaseAuth.getInstance();

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();

        // Initialize Normal Login (fake)
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, BrowseActivity.class));
            }
        });

        mFacebookLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFacebookLoginBtn.setEnabled(false);
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                        userData = intent.getExtras();
                        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.d("response", response.toString());
                                setData(object, userData);
                            }
                        });
                        request.executeAsync();
                        startActivity(intent, userData);
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });

            }
        });

        // If already logged in, go to BrowseActivity
        if(AccessToken.getCurrentAccessToken() != null) {
            Toast.makeText(this, "Already logged in" + AccessToken.getCurrentAccessToken().getUserId(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, BrowseActivity.class));
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private  void setData (JSONObject object, Bundle userData) {
        try{
            URL profile__picture = new URL("https://graph.facebook.com/" + object.getString("id") + "/picture?width=250&height=250");

            // Load user's Facebook Data into the app here
            Log.d("data", object.getString("id"));
            //userData.putString("userId", object.getString("id"));
            Log.d("data", object.getString("email"));
            userData.putString("email", object.getString("email"));
            Log.d("data", object.getString("name"));
            userData.putString("name", object.getString("name"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
