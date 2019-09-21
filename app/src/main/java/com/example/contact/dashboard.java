package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }


        public void profile(View view) {
            Intent intent = new Intent(dashboard.this, AccActivity.class);
            startActivity(intent);
        }



        public void chat(View view) {
        Intent intent = new Intent(dashboard.this, NewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
//       // startActivity(new Intent(this, AccActivity.class));
        //finish();
        // moveTaskToBack(true);
    }


}
