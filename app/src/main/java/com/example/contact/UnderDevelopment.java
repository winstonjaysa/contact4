package com.example.contact;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class UnderDevelopment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_development);

        //action bar back button active
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Under development");
    }

    //custom back button activity ,i used this for get icon default color.
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this, AccActivity.class));
        finish();
    }
    //end
}
