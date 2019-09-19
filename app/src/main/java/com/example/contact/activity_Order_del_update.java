package com.example.contact;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class activity_Order_del_update extends AppCompatActivity {

    private EditText mAmount, mUname;
    private TextView extStatus;
    private Button mUpdate, mDelete;
    private Spinner spinner_meal, spinner_time;
    private String meal_selected, time_selected;

    private String key, name, uname, time, amount, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__order_del_update);

        //action bar back button active
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change order");



        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        uname = getIntent().getStringExtra("uname");
        time = getIntent().getStringExtra("time");
        amount = getIntent().getStringExtra("amount");
        status = getIntent().getStringExtra("status");

        mUname = findViewById(R.id.etxt4);
        mUname.setText(uname);

        mAmount = findViewById(R.id.etxt2);
        mAmount.setText(amount);

        extStatus = (TextView) findViewById(R.id.extStatus);
        extStatus.setText(status);

        spinner_meal = (Spinner) findViewById(R.id.meal_list);
        spinner_meal.setSelection(getIndex_SpinnerItem(spinner_meal, name));

        spinner_time = (Spinner) findViewById(R.id.time_list);
        spinner_time.setSelection(getIndex_SpinnerItem(spinner_time, time));

        mUpdate = findViewById(R.id.btnupdate);
        mDelete = findViewById(R.id.btndelete);


        //meal spinner list
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.meal, android.R.layout.simple_spinner_item);
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
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
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


        if (extStatus.getText().toString().equals("1")) {
            Toast.makeText(getApplicationContext(), "You can't update or delete this order.", Toast.LENGTH_SHORT).show();
        }


        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetails orderDetails = new OrderDetails();


                orderDetails.setUname(mUname.getText().toString());
                orderDetails.setAmount(mAmount.getText().toString());
                orderDetails.setTime(spinner_time.getSelectedItem().toString());
                orderDetails.setName(spinner_meal.getSelectedItem().toString());
                orderDetails.setStatus(extStatus.getText().toString());

                if (extStatus.getText().toString().equals("1")) {
                    //mUpdate.setClickable(false);
                    Toast.makeText(getApplicationContext(), "You can't update this order.", Toast.LENGTH_SHORT).show();
                } else if (extStatus.getText().toString().equals("0")) {
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
