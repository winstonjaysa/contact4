package com.example.contact;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class RoomReservation_Edit extends AppCompatActivity {

    TextView rType,out_date,in_date,adults,children;
    Button btndelete,btnupdate;
    DatabaseReference myreff,dreff;
    RoomDetails roomDetails;
    DatePickerDialog datePickerDialog;

    private void clearControl(){
        rType.setText("");
        out_date.setText("");
        in_date.setText("");
        adults.setText("");
        children.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Room Reservation");


        rType=findViewById(R.id.rType);
        out_date=findViewById(R.id.out_date);
        in_date=findViewById(R.id.in_date);
        adults=findViewById(R.id.adults);
        children=findViewById(R.id.children);

        //btnview=findViewById(R.id.btnview);
        btnupdate=findViewById(R.id.btnUpdate);
        btndelete=findViewById(R.id.btndelete);

        roomDetails =new RoomDetails();

        in_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog

                datePickerDialog = new DatePickerDialog(RoomReservation_Edit.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                in_date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        out_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog

                datePickerDialog = new DatePickerDialog(RoomReservation_Edit.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                out_date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });



        myreff= FirebaseDatabase.getInstance().getReference().child("RoomDetails").child("1");
        myreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    String RoomType = dataSnapshot.child("rtype").getValue().toString();
                    String OutDate = dataSnapshot.child("outDate").getValue().toString();
                    String InDate = dataSnapshot.child("inDate").getValue().toString();
                    String Adults = dataSnapshot.child("amountAdults").getValue().toString();
                    String Children = dataSnapshot.child("amountChildren").getValue().toString();

                    rType.setText(RoomType);
                    out_date.setText(OutDate);
                    in_date.setText(InDate);
                    adults.setText(Adults);
                    children.setText(Children);

                }else {
                    Toast.makeText(getApplicationContext(),"No source to display",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myreff= FirebaseDatabase.getInstance().getReference().child("RoomDetails");
                myreff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){

                            try{
                                roomDetails.setRType(rType.getText().toString().trim());
                                roomDetails.setOutDate(out_date.getText().toString().trim());
                                roomDetails.setInDate(in_date.getText().toString().trim());
                                roomDetails.setAmountAdults(Integer.parseInt(adults.getText().toString().trim()));
                                roomDetails.setAmountChildren(Integer.parseInt(children.getText().toString().trim()));
//                                roomDetails.setAge(Integer.parseInt(.getText().toString().trim()));
//                                roomDetails.setNumber(Long.parseLong(t3.getText().toString().trim()));

                                 dreff= FirebaseDatabase.getInstance().getReference().child("RoomDetails").child("1");
                                 dreff.setValue(roomDetails);
//
//                               clearControl();
                               Toast.makeText(getApplicationContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();
                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid contact number",Toast.LENGTH_SHORT).show();
                            }
                        }else
                            Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myreff= FirebaseDatabase.getInstance().getReference().child("RoomDetails");
                myreff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            myreff= FirebaseDatabase.getInstance().getReference().child("RoomDetails").child("1");
                            myreff.removeValue();
                            clearControl();
                            Toast.makeText(getApplicationContext(),"Data deleted successfully",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "No Source to delete.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
