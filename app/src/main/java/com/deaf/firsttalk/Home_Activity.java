package com.deaf.firsttalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Home_Activity extends AppCompatActivity implements RecycleItemClickListener{

    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    private RecyclerView recyclerView1;
    private List<CourseImgPojo> artistList1;
    private CourseAdapter adapter1;
    private DatabaseReference mFirebaseDatabase;
    String course_key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        //NAvigation to User_black_icon
        ImageView user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                }
                else {
                    Intent intent = new Intent(Home_Activity.this, Account_Activity.class);
                    startActivity(intent);
                }
            }
        });
        recyclerView1 = findViewById(R.id.productrecyclerView);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));

        artistList1 = new ArrayList<>();
        adapter1 = new CourseAdapter(this, artistList1);
        recyclerView1.setAdapter(adapter1);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = FirebaseDatabase.getInstance().getReference("Course");
        query.addListenerForSingleValueEvent(valueEventListener);

        adapter1.setClickListener(this);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList1.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final CourseImgPojo artist = snapshot.getValue(CourseImgPojo.class);
                    course_key=snapshot.getKey();
                   // Log.e("hhhh",key);
                    artistList1.add(artist);
                }
                adapter1.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };

    @Override
    public void onClick(View view, int position) {
        final CourseImgPojo pojo = artistList1.get(position);
        Intent i = new Intent(this, Course_Activity.class);
        i.putExtra("coursenm_key",pojo.getCname());
        i.putExtra("courseuniversity_key",pojo.getCuniversity());
        i.putExtra("courseweek_key",pojo.week);
        //Log.e("course", String.valueOf(pojo.week));
        i.putExtra("courseid_key",course_key);
        //Log.e("hhhh",course_key);
        startActivity(i);

    }
}