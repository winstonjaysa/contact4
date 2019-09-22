package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboard extends AppCompatActivity {

    private LinearLayout crd_room;
    private TextView txt_uid;
    DatabaseReference databaseReference;
    OrderDetails orderDetails;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txt_uid=(TextView) findViewById(R.id.txt_uid);

        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();


        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("Users").child(uid).child("Name").getValue(String.class);
                txt_uid.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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
