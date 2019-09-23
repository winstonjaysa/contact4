package com.example.contact;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

public class activity_Order_del_update extends AppCompatActivity {

    private EditText mAmount, mUname, date;
    private TextView extStatus;
    private Button mUpdate, mDelete;
    private Spinner spinner_meal, spinner_time;
    private String meal_selected, time_selected;
    private DatePickerDialog datePickerDialog;

    private String key, name, uname, time, needDate, amount, status;

    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_del_update);

        //action bar back button active
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change order");


        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username = dataSnapshot.child("Users").child(uid).child("Name").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        //uname = getIntent().getStringExtra("uname");
        time = getIntent().getStringExtra("time");
        amount = getIntent().getStringExtra("amount");
        status = getIntent().getStringExtra("status");
        needDate = getIntent().getStringExtra("needDate");

//        mUname = findViewById(R.id.date);
//        mUname.setText(name);

        mAmount = findViewById(R.id.etxt2);
        mAmount.setText(amount);

        date = (EditText) findViewById(R.id.date);
        date.setText(needDate);

        extStatus = (TextView) findViewById(R.id.extStatus);
        extStatus.setText(status);

        spinner_meal = (Spinner) findViewById(R.id.meal_list);
        spinner_meal.setSelection(getIndex_SpinnerItem(spinner_meal, name));

        spinner_time = (Spinner) findViewById(R.id.time_list);
        spinner_time.setSelection(getIndex_SpinnerItem(spinner_time, time));


        mUpdate = findViewById(R.id.btnUpdate);
        mDelete = findViewById(R.id.btndelete);


        //meal spinner list
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.meal, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_meal.setAdapter(adapter);
        spinner_meal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                meal_selected = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //end of meal spinner list

        //time spinner list
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.time, R.layout.custom_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_time.setAdapter(adapter2);
        spinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time_selected = adapterView.getItemAtPosition(i).toString();
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

                datePickerDialog = new DatePickerDialog(activity_Order_del_update.this,
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


        if (extStatus.getText().toString().equals("1")) {
            Toast.makeText(getApplicationContext(), "You can't update or delete this order.", Toast.LENGTH_SHORT).show();
        }


        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetails orderDetails = new OrderDetails();


                orderDetails.setDate(date.getText().toString());
                orderDetails.setAmount(mAmount.getText().toString());
                orderDetails.setTime(spinner_time.getSelectedItem().toString());
                orderDetails.setMeal(spinner_meal.getSelectedItem().toString());
                orderDetails.setStatus(extStatus.getText().toString());
                orderDetails.setUsername(username);

                if (extStatus.getText().toString().equals("1")) {
                    mUpdate.setClickable(false);
                    Toast.makeText(getApplicationContext(), "You can't update this order.", Toast.LENGTH_SHORT).show();
                } else if (extStatus.getText().toString().equals("0")) {

                    if (TextUtils.isEmpty(meal_selected)) {
                        Toast.makeText(activity_Order_del_update.this, "please select a meal", Toast.LENGTH_LONG).show();
                    }else if (TextUtils.isEmpty(time_selected)) {
                        Toast.makeText(activity_Order_del_update.this, "please select time", Toast.LENGTH_LONG).show();
                    }else if(TextUtils.isEmpty(mAmount.getText().toString())|| mAmount.getText().toString().equals(0)) {
                        Toast.makeText(activity_Order_del_update.this, "please enter amount more than 0", Toast.LENGTH_LONG).show();
                    }else if(TextUtils.isEmpty(date.getText().toString()))
                        Toast.makeText(activity_Order_del_update.this,"please select date",Toast.LENGTH_LONG).show();
                    else {

                        new FirebaseDatabaseHelper().updateOrder(key, orderDetails, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void DataIsLoaded(List<OrderDetails> orderDetails, List<String> keys) {

                            }

                            @Override
                            public void DataIsInserted() {

                            }

                            @Override
                            public void DataIsUpdated() {

                                Toast.makeText(activity_Order_del_update.this, "Order updated successfully", Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            public void DataIsDeleted() {

                            }
                        });
                    }
                }
            }
        });


        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extStatus.getText().toString().equals("1")) {
                    mUpdate.setClickable(false);
                    Toast.makeText(getApplicationContext(), "You can't Delete this order.", Toast.LENGTH_SHORT).show();
                } else if (extStatus.getText().toString().equals("0")) {
                    new FirebaseDatabaseHelper().deleteOrder(key, new FirebaseDatabaseHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<OrderDetails> orderDetails, List<String> keys) {

                        }

                        @Override
                        public void DataIsInserted() {

                        }

                        @Override
                        public void DataIsUpdated() {

                        }

                        @Override
                        public void DataIsDeleted() {
                            Toast.makeText(activity_Order_del_update.this, "Order Delete successfully", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                    });
                }
            }
        });

    }

    private int getIndex_SpinnerItem(Spinner spinner, String item) {
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

}
