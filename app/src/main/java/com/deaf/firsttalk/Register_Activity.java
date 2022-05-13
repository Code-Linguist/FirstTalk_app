package com.deaf.firsttalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Activity extends AppCompatActivity {

    EditText edemail, edpassword,edname;
    Button btnregister;
    TextView movetologintxt;
    FirebaseAuth auth;
    private ProgressDialog progress;
    private DatabaseReference reff;
    RegisteruserPojo registeruserPojo;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        auth = FirebaseAuth.getInstance();

        edname = findViewById(R.id.regisname);
        edemail = findViewById(R.id.regisEmailid);
        edpassword = findViewById(R.id.regisPassword);
        btnregister = findViewById(R.id.regisbtnregister);
        movetologintxt = findViewById(R.id.movetologintxt);
        ImageView back = findViewById(R.id.btnregistertologinback);
        progress = new ProgressDialog(this);
        sharedpreferences = getSharedPreferences("Password-pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register_Activity.this, Home_Activity.class);
                startActivity(i);
            }
        });

        movetologintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inttoregister=new Intent(Register_Activity.this, Login_Activity.class);
                startActivity(inttoregister);
            }
        });

        registeruserPojo =new RegisteruserPojo();
        reff= FirebaseDatabase.getInstance().getReference().child("Registeruser");

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edemail.getText().toString().trim();
                String password= edpassword.getText().toString().trim();
                if(email.isEmpty())
                {
                    edemail.setError("Email is empty");
                    edemail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    edemail.setError("Enter the valid email address");
                    edemail.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    edpassword.setError("Enter the password");
                    edpassword.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    edpassword.setError("Length of the password should be more than 6");
                    edpassword.requestFocus();
                    return;
                }

                progress.setTitle("Registering User");
                progress.setMessage("Please wait while we create your account !");
                progress.setCanceledOnTouchOutside(false);
                progress.show();
                //Log.e("hello", String.valueOf(edname));
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            progress.dismiss();
                            registeruserPojo.setName(edname.getText().toString().trim());
                            //currentFirebaseUser = authResult.getUser();
                            FirebaseUser user = auth.getCurrentUser();
                            reff.child(user.getUid()).setValue(registeruserPojo);

                            editor.putString("pass-key", password);
                            editor.commit();
                            Intent intent = new Intent(Register_Activity.this, Home_Activity.class);
                            startActivity(intent);
                            finish();

                        }
                        else
                        {
                            progress.hide();
                            Toast.makeText(Register_Activity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}