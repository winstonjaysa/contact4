package com.example.contact;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class OrderAdd extends AppCompatActivity {
    EditText etxt1,etxt2,etxt3,etxt4,date;
    TextView display_text;
    Button btnsave;
    Spinner spinner_meal,spinner_time;
    OrderDetails orderDetails;
    DatabaseReference myRef;
    String meal_selected,time_selected;
    DatePickerDialog datePickerDialog;
    long maxId=0;

    DatabaseReference databaseReference;

    FirebaseUser user;
    String uid,uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_add);

        //customize action bar
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_action_bar));
        }
        //end
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add order");

        etxt2=findViewById(R.id.noofitems);
        etxt4=findViewById(R.id.etxt4);
        display_text=(TextView)findViewById(R.id.display_text);

        date = (EditText) findViewById(R.id.date);

        spinner_meal=(Spinner) findViewById(R.id.meal_list);
        spinner_time=(Spinner) findViewById(R.id.time_list);
        btnsave=findViewById(R.id.btnCalc);

        orderDetails=new OrderDetails();


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


        //meal spinner list
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.meal,R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_meal.setAdapter(adapter);
        spinner_meal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                meal_selected=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //end of meal spinner list

        //time spinner list
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.time,R.layout.custom_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_time.setAdapter(adapter2);
        spinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time_selected=adapterView.getItemAtPosition(i).toString();
               // ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#b911bc"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //end of time spinner list

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog

                datePickerDialog = new DatePickerDialog(OrderAdd.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        etxt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etxt2.getText().toString().equals("")) {
                    display_text.setText(String.valueOf(Integer.valueOf(etxt2.getText().toString())  + 5 ));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //editText2.addTextChangedListener(autoAddTextWatcher);









        //uid number

        myRef= FirebaseDatabase.getInstance().getReference().child("Order");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                    maxId=(dataSnapshot.getChildrenCount());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        //end

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int g=Integer.parseInt(etxt2.getText().toString());

                    if((TextUtils.isEmpty(etxt2.getText().toString()))|| (g==0))
                        Toast.makeText(OrderAdd.this,"please enter amount more than 0",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(meal_selected))
                        Toast.makeText(OrderAdd.this,"please select a meal",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(date.getText().toString()))
                        Toast.makeText(OrderAdd.this,"please enter date",Toast.LENGTH_LONG).show();
                    else {
                        orderDetails.setMeal(meal_selected);
                        orderDetails.setAmount(etxt2.getText().toString().trim());
                        orderDetails.setTime(time_selected);
                        orderDetails.setDate(date.getText().toString().trim());
                        orderDetails.setStatus("0");
                        orderDetails.setUsername(uname);

                        //myRef.child(String.valueOf(maxId+1)).setValue(orderDetails);
                        myRef.push().setValue(orderDetails);

                        Toast.makeText(getApplicationContext(),"Order added successfully.",Toast.LENGTH_SHORT).show();
                        clearControl();
                        finish();
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"invalid control number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearControl(){

        etxt2.setText("");
        date.setText("");
    }
}
