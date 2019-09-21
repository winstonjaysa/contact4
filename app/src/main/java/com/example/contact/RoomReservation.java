package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RoomReservation extends AppCompatActivity {
    EditText etxt1,etxt2,etxt3,etxt4,etxt5;
    TextView txt1;
    Button btnsave2,btnview,btnSave;
    RoomDetails roomDetails;
    DatabaseReference myRef;
    long maxId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation_main);


        //customize action bar
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_action_bar));
        }
        //end
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Room Reservation");

        etxt1=findViewById(R.id.etxt1);
        etxt2=findViewById(R.id.etxt2);
        etxt3=findViewById(R.id.etxt3);
        etxt4=findViewById(R.id.etxt4);
        etxt5=findViewById(R.id.etxt5);
        txt1=findViewById(R.id.txt1);

        btnsave2=findViewById(R.id.btnupdate);
        btnview=findViewById(R.id.btnView);
        btnSave=findViewById(R.id.btnSave);

        roomDetails =new RoomDetails();

        myRef= FirebaseDatabase.getInstance().getReference().child("RoomDetails");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxId=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //if(TextUtils.isEmpty(etxt1.getText().toString()))
                        Toast.makeText(RoomReservation.this,"please enter a name",Toast.LENGTH_LONG).show();
                    //else {
                        roomDetails.setRType("g1");
                        roomDetails.setInDate("g2");
                        roomDetails.setOutDate("g3");
                        roomDetails.setAmountAdults(1);
                        roomDetails.setAmountChildren(2);
                        roomDetails.setTotal(Float.parseFloat("54"));

                        //myRef.push().setValue(roomDetails);
                        myRef.child(String.valueOf(maxId+1)).setValue(roomDetails);

                        //Toast.makeText(getApplicationContext(), roomDetails.getR()+" "+ roomDetails.getAge()+" "+ roomDetails.getNumber()+" "+ roomDetails.getHeight(),Toast.LENGTH_SHORT).show();
                        clearControl();
                    //}
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"invalid control number",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void clearControl(){
        etxt1.setText("");
        etxt2.setText("");
        etxt3.setText("");
        etxt4.setText("");
    }

    public void viewdetails(View v){
        Intent intent=new Intent(this,ViewActivity.class);
        startActivity(intent);
    }
    public void accgo(View v){
        Intent intent=new Intent(this,AccActivity.class);
        startActivity(intent);
    }
}
