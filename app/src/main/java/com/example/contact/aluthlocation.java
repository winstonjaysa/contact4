package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class aluthlocation extends AppCompatActivity {


    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private Button button;
    final Calendar myCalendar = Calendar.getInstance();

    DatabaseReference databaseReference;

    FirebaseUser user;
    String uid,uname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluthlocation);


        editText1 = (EditText) findViewById(R.id.et);
        editText2 = (EditText) findViewById(R.id.et1);
        editText3 = (EditText) findViewById(R.id.et3);
        button = (Button) findViewById(R.id.btn);



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


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                editText3.setText(sdf.format(myCalendar.getTime()));
            }

        };

        editText3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(aluthlocation.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location location = new location();
                location.setName(editText1.getText().toString());
                location.setAttraction(editText2.getText().toString());
                location.setBudget(editText3.getText().toString());
                location.setUsername(uname);
                new controller().addlocation(location, new controller.datastatus() {
                    @Override
                    public void Dataisloaded(List<com.example.contact.location> locations, List<String> keys) {

                    }

                    @Override
                    public void Dataisinserted() {


                        Toast.makeText(aluthlocation.this,"Location saved",Toast.LENGTH_LONG).show();
                        finish();
                        return;


                    }

                    @Override
                    public void Dataisupdated() {

                    }

                    @Override
                    public void Dataisdeleted() {

                    }
                });
            }
        });


    }



}
