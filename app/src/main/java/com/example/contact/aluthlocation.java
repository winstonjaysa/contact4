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

public class aluthlocation extends AppCompatActivity {


    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private Button button;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluthlocation);


        editText1 = (EditText) findViewById(R.id.et);
        editText2 = (EditText) findViewById(R.id.et1);
        editText3 = (EditText) findViewById(R.id.et3);
        button = (Button) findViewById(R.id.btn);





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
                new controller().addlocation(location, new controller.datastatus() {
                    @Override
                    public void Dataisloaded(List<com.example.contact.location> locations, List<String> keys) {

                    }

                    @Override
                    public void Dataisinserted() {


                        Toast.makeText(aluthlocation.this,"Location saved",Toast.LENGTH_LONG).show();


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
