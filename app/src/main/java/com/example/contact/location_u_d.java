package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class location_u_d extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private Button deletebutton;
    private Button updatebutton;

    private String key;
    private String name;
    private String attraction;
    private String budget;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_u_d);

        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        attraction = getIntent().getStringExtra("attraction");
        budget = getIntent().getStringExtra("budget");

        editText1 = (EditText) findViewById(R.id.et);
        editText2 = (EditText) findViewById(R.id.et1);
        editText3 = (EditText) findViewById(R.id.et3);
        deletebutton = (Button) findViewById(R.id.btn);
        updatebutton = (Button) findViewById(R.id.btn2);


        editText1.setText(name);
        editText2.setText(attraction);
        editText3.setText(budget);


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
                new DatePickerDialog(location_u_d.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });





        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location location = new location();
                location.setName(editText1.getText().toString());
                location.setAttraction(editText2.getText().toString());
                location.setBudget(editText3.getText().toString());



                new controller().updatelocation(key, location, new controller.datastatus() {
                    @Override
                    public void Dataisloaded(List<com.example.contact.location> locations, List<String> keys) {
                        
                    }

                    @Override
                    public void Dataisinserted() {

                    }

                    @Override
                    public void Dataisupdated() {

                        Toast.makeText(location_u_d.this, "Update succesful", Toast.LENGTH_SHORT).show();
                        
                        
                    }

                    @Override
                    public void Dataisdeleted() {

                    }
                });

            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new controller().deletelocation(key, new controller.datastatus() {
                    @Override
                    public void Dataisloaded(List<location> locations, List<String> keys) {

                    }

                    @Override
                    public void Dataisinserted() {

                    }

                    @Override
                    public void Dataisupdated() {

                    }

                    @Override
                    public void Dataisdeleted() {

                        Toast.makeText(location_u_d.this,"Data deleted",Toast.LENGTH_LONG).show();
                        finish(); return;

                    }
                });
            }
        });



    }
}
