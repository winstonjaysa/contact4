package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LaundryAdd extends AppCompatActivity {

    private Button btnAdd,btnView;
    long maxId = 0;
    private EditText itemcategory,noofitems;


    DatabaseReference myRef;
    LaundryDetails laundryDetails;

    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid,uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_add);

        laundryDetails=new LaundryDetails();

        btnAdd=(Button) findViewById(R.id.btnupdate);
        btnView=(Button)findViewById(R.id.btndelete);

        itemcategory=(EditText)findViewById(R.id.itemcategory);
        noofitems=(EditText)findViewById(R.id.noofitems);

        //customize action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_action_bar));
        }
        //end
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Laundry");

        myRef = FirebaseDatabase.getInstance().getReference().child("Laundry");

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


        myRef = FirebaseDatabase.getInstance().getReference().child("Laundry");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxId = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists())
//                    maxId = (dataSnapshot.getChildrenCount());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxId == 0) {
                    try {

                            laundryDetails.setCategory(itemcategory.getText().toString().trim());
                            laundryDetails.setNoOfItems(Integer.parseInt(noofitems.getText().toString().trim()));

                            int x=Integer.parseInt(noofitems.getText().toString().trim());
                            int tot=x*10;

                            laundryDetails.setPrice(tot);
                            laundryDetails.setUserName(uname);


                            myRef.child(String.valueOf(maxId + 1)).setValue(laundryDetails);

                            Toast.makeText(getApplicationContext(), "Laundry item added successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LaundryAdd.this, dashboard.class);
                            startActivity(intent);


                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "invalid control number", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LaundryAdd.this, "You can't request more than one time.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LaundryAdd.this,Laundry_u_d.class);
                startActivity(intent);
            }
        });
    }


}
