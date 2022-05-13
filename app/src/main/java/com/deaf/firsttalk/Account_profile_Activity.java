package com.deaf.firsttalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class Account_profile_Activity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    EditText edname;
    TextView txtemail,txtpass;
    Button btnsave;
    ImageView  btnback;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Registeruser");

        edname = findViewById(R.id.editname);
        txtemail = findViewById(R.id.txtemail);
        txtpass = findViewById(R.id.txtpass);
        btnsave =findViewById(R.id.btnsave);
        btnback = findViewById(R.id.account_profile_back);
        sharedpreferences = getSharedPreferences("Password-pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(getApplicationContext(), Login_Activity.class));
        }


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RegisteruserPojo registeruserPojo = dataSnapshot.child(user.getUid()).getValue(RegisteruserPojo.class);
                edname.setText(registeruserPojo.getName());
                txtemail.setText(user.getEmail());
              //  txtpass.setText(sharedpreferences.getString("pass-key", null));

                // String image = dataSnapshot.child(User_black_icon.getUid()).child("image").getValue().toString();
                // Picasso.get().load(image).placeholder(R.drawable.defavatar).into(profileImageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


        //back button
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iback =new Intent(Account_profile_Activity.this,Account_Activity.class);
                startActivity(iback);
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cname = edname.getText().toString();

                if (TextUtils.isEmpty(cname)) {
                    Toast.makeText(getApplicationContext(), "Enter Name !", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    databaseReference.child(user.getUid()).child("name").setValue(cname);


                }
                Toast.makeText(getApplicationContext(), "Hello " +cname+ " ,nice to meet you!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Account_profile_Activity.this,Account_Activity.class);
                startActivity(i);
            }
        });
    }

}