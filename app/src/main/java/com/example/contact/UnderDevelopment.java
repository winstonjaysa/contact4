package com.example.contact;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UnderDevelopment extends AppCompatActivity {

    TextView txt_icon_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_development);

        txt_icon_order=(TextView) findViewById(R.id.txt_icon_order);

        //action bar back button active
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Under development");
    }

    //custom back button activity ,i used this for get icon default color.
     @Override
     public void onBackPressed()
     {
//       // startActivity(new Intent(this, AccActivity.class));
//       // finish();
//       // moveTaskToBack(true);
////        txt_icon_order.setTextColor(Color.parseColor("#fe435b"));

    }
    //end
}
