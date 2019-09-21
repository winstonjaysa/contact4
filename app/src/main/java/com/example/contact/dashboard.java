package com.example.contact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {

    private LinearLayout crd_room;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //customize action bar
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_action_bar));
        }
        //end

        crd_room=(LinearLayout)findViewById(R.id.crd_room);

        crd_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txt_icon_room.setTextColor(Color.parseColor("#fe435b"));
                Intent intent=new Intent(dashboard.this,RoomReservation.class);
                startActivity(intent);
            }
        });
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
        Toast.makeText(dashboard.this,"f*ck 0ff",Toast.LENGTH_SHORT).show();
    }


}
