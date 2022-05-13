package com.deaf.firsttalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account_Activity extends AppCompatActivity {

    ImageView btncancel;
    TextView txtusername;
    CardView orderdetalis, profiledetails, savedaddress;
    Button btnlogout;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        btncancel =findViewById(R.id.account_cancelbtn);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Account_Activity.this,Home_Activity.class);
                startActivity(i);
            }
        });

        txtusername = findViewById(R.id.account_txtusername);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Registeruser");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(getApplicationContext(), Login_Activity.class));
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RegisteruserPojo registeruserPojo = dataSnapshot.child(user.getUid()).getValue(RegisteruserPojo.class);
                // Log.e("username",registeruser.getName());
                txtusername.setText(registeruserPojo.getName());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        profiledetails =findViewById(R.id.account_cardprofiledetails);
        profiledetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Account_Activity.this, Account_profile_Activity.class);
                startActivity(i);
            }
        });

        sharedpreferences = getSharedPreferences("Password-pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        btnlogout=findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                editor.clear();
                editor.commit();
                Intent inent = new Intent(getApplicationContext(), Home_Activity.class);
                startActivity(inent);
            }
        });
    }
}
