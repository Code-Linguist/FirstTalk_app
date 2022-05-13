package com.deaf.firsttalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course_Activity extends AppCompatActivity implements Course_week_Adapter.ItemClickListener ,RecycleItemClickListener{

    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerViewpart;
    private List<Course_part_Pojo> artistList1;
    Course_part_Adapter adapterpart;
    private DatabaseReference mFirebaseDatabase;
    TextView txtuniversity,txtcname,txtweeknm,txtweekinfo;
    RecyclerView recyclerVieweek;
    Course_week_Adapter adapterweek;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        txtcname = findViewById(R.id.textcname);
        txtuniversity = findViewById(R.id.textuniversity);
        txtweeknm = findViewById(R.id.textweekname);
        txtweekinfo = findViewById(R.id.textweekinfo);

        Bundle extras = getIntent().getExtras();
        String coursenm_key = extras.getString("coursenm_key");
        String courseuni_key = extras.getString("courseuniversity_key");
        String course_id = extras.getString("courseid_key");
        ArrayList<String> weekList = (ArrayList<String>) getIntent().getSerializableExtra("courseweek_key");
        // Log.e("course11", String.valueOf(weekList));

        txtcname.setText(coursenm_key);
        txtuniversity.setText(courseuni_key);

        // set up the RecyclerView on week
        recyclerVieweek = findViewById(R.id.recycleweekview);
        Collections.reverse(weekList);
        recyclerVieweek.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        adapterweek = new Course_week_Adapter(this, weekList);
        adapterweek.setClickListener(this);
        recyclerVieweek.setAdapter(adapterweek);

        //Recyclerview for part
        firebaseAuth = FirebaseAuth.getInstance();

        recyclerViewpart = findViewById(R.id.recyclepartview);
        recyclerViewpart.setHasFixedSize(true);
        recyclerViewpart.setLayoutManager(new GridLayoutManager(this, 1));

        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        artistList1 = new ArrayList<>();
        adapterpart = new Course_part_Adapter(this, artistList1);
        recyclerViewpart.setAdapter(adapterpart);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = FirebaseDatabase.getInstance().getReference("Part")
                .orderByChild("cwid").equalTo("c1w1");

        query.addListenerForSingleValueEvent(valueEventListener);

        adapterpart.setClickListener(this);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList1.clear();
            if (dataSnapshot.exists()) {
                    final Course_part_Pojo artist = dataSnapshot.getValue(Course_part_Pojo.class);
                    artistList1.add(artist);
                }
                adapterpart.notifyDataSetChanged();
            }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onItemClick(View view, int position) {
        for (int i = 0; i < recyclerVieweek.getChildCount(); i++) {
            if(position == i ){
                recyclerVieweek.getChildAt(i).setBackgroundColor(Color.parseColor("#9CEF1761"));
            }else{
                recyclerVieweek.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    @Override
    public void onClick(View view, int position) {

    }
};
