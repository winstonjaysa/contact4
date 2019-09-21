package com.example.contact;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class RoomReservation extends AppCompatActivity {
    EditText etxt1,etxt2,etxt3,etxt4,etxt5;
    TextView txtTotal;
    Long dt1;
    Button btnCalc,btnview,btnSave;
    DatePickerDialog datePickerDialog;
    Calendar d1;
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
        txtTotal=findViewById(R.id.txtTotal);

        btnCalc=findViewById(R.id.btnCalc);
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

        etxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog

                datePickerDialog = new DatePickerDialog(RoomReservation.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                etxt2.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        etxt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog

                datePickerDialog = new DatePickerDialog(RoomReservation.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                etxt3.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });


        d1=Calendar.getInstance();


        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d1=etxt2.getText().toString();
                String d2=etxt3.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
                try {
                    Date dtte1 = sdf.parse( d1 );
                    Date dtte2 = sdf.parse( d2 );

                    Long gg=compareTo(dtte1,dtte2);
                    int h=1;
                    //Math.toIntExact(gg);

//                    if ( compareTo( dtte1, dtte2 ) < 0 )
//                    {
//                        Toast.makeText(RoomReservation.this,"daate 2 ",Toast.LENGTH_LONG).show();
//                    }
//                    else if ( compareTo( dtte1, dtte2 ) > 0 )
//                    {
//                        Toast.makeText(RoomReservation.this,"daate 1 ",Toast.LENGTH_LONG).show();
//                    }
//                    else
//                    {
//                        Toast.makeText(RoomReservation.this,"eq ",Toast.LENGTH_LONG).show();
//                    }
                }catch (ParseException e){

                }

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(TextUtils.isEmpty(etxt1.getText().toString())) {
                        Toast.makeText(RoomReservation.this, "please enter room type", Toast.LENGTH_LONG).show();
                    }else if(TextUtils.isEmpty(etxt2.getText().toString())) {
                        Toast.makeText(RoomReservation.this, "please enter check-in date", Toast.LENGTH_LONG).show();
                    }else if(TextUtils.isEmpty(etxt3.getText().toString())) {
                        Toast.makeText(RoomReservation.this, "please enter check-out date", Toast.LENGTH_LONG).show();
                    }else if(TextUtils.isEmpty(etxt4.getText().toString())) {
                        Toast.makeText(RoomReservation.this, "please enter amount of adults", Toast.LENGTH_LONG).show();
                    }else if(TextUtils.isEmpty(etxt5.getText().toString())) {
                        Toast.makeText(RoomReservation.this, "please enter amount of children", Toast.LENGTH_LONG).show();
                    }else{
                        roomDetails.setRType(etxt1.getText().toString().trim());
                        roomDetails.setInDate(etxt2.getText().toString().trim());
                        roomDetails.setOutDate(etxt3.getText().toString().trim());
                        roomDetails.setAmountAdults(Integer.parseInt(etxt4.getText().toString().trim()));
                        roomDetails.setAmountChildren(Integer.parseInt(etxt5.getText().toString().trim()));
                        roomDetails.setTotal(Float.parseFloat("54"));

                        //myRef.push().setValue(roomDetails);
                        myRef.child(String.valueOf(maxId+1)).setValue(roomDetails);

                        Toast.makeText(getApplicationContext(),"Reservation added successfully",Toast.LENGTH_SHORT).show();
                        clearControl();

                        Intent intent=new Intent(RoomReservation.this,dashboard.class);
                        startActivity(intent);

                    }
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

    public static long compareTo( Date date1,Date date2 )
    {
//returns negative value if date1 is before date2
//returns 0 if dates are even
//returns positive value if date1 is after date2
        Long tot=date1.getTime() - date2.getTime();
        return tot;
    }
}

