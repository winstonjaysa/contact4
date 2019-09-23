package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class locationlist extends AppCompatActivity {

    private RecyclerView mRecycleview;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid,uname;
    Button customAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationlist);
        mRecycleview = (RecyclerView) findViewById(R.id.rv);


        Typeface font=Typeface.createFromAsset(getAssets(),"fa-solid-900.ttf");

        customAddButton=(Button)findViewById(R.id.customAddButton);
        customAddButton.setTypeface(font);
        customAddButton.setText("\uf067");


        customAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(locationlist.this,aluthlocation.class);
                startActivity(intent);
            }
        });

        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uname=dataSnapshot.child("Users").child(uid).child("Name").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        new controller().readlocation(new controller.datastatus() {
            @Override
            public void Dataisloaded(List<location> locations, List<String> keys) {
                new Recyclerview_context().setConfig(mRecycleview,locationlist.this,locations,keys,uname );
            }

            @Override
            public void Dataisinserted() {

            }

            @Override
            public void Dataisupdated() {

            }

            @Override
            public void Dataisdeleted() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.locationlist_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_location:
            startActivity(new Intent(this,aluthlocation.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
