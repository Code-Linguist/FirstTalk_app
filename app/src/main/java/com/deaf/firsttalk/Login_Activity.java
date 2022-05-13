package com.deaf.firsttalk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    EditText edemail, edpassword;
    Button btnlogin;
    TextView movetoregistertxt;
    private FirebaseAuth auth;
    private ProgressDialog progress;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "SignInActivity";
    private SignInButton signInButton;
    private GoogleApiClient mgoogleApiClient;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login_Activity.this, Home_Activity.class));
            finish();
        }

        movetoregistertxt = findViewById(R.id.movetoregistertxt);
        edemail = findViewById(R.id.loginEmailid);
        edpassword = findViewById(R.id.loginPassword);
        btnlogin =findViewById(R.id.loginbtnlogin);
        ImageView back =findViewById(R.id.btnlogintohomeback);
        progress = new ProgressDialog(this);
        sharedpreferences = getSharedPreferences("Password-pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_Activity.this,Home_Activity.class);
                startActivity(i);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edemail.getText().toString();
                final String password = edpassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progress.setTitle("Logging In");
                progress.setMessage("Please wait while we check your credentials.");
                progress.setCanceledOnTouchOutside(false);
                progress.show();

                //authenticate User_black_icon
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    if (password.length() < 6) {
                                        progress.hide();
                                        edpassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        progress.hide();
                                        Toast.makeText(Login_Activity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    progress.dismiss();
                                    editor.putString("pass-key", password);
                                    editor.commit();

                                    Intent intent = new Intent(Login_Activity.this, Home_Activity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });


        movetoregistertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inttoregister=new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(inttoregister);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mgoogleApiClient = new GoogleApiClient.Builder(this)  // 2
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        signIn();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mgoogleApiClient);  // 3
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);  // 4
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            Toast.makeText(Login_Activity.this, "sin in", Toast.LENGTH_LONG).show();
            signInButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(Login_Activity.this, "Signed in unsuccessfully", Toast.LENGTH_LONG).show();
        }
    }
}