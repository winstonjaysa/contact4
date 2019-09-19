package com.example.contact;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class ViewActivity extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    Button btnview,btnup,btndel;
    DatabaseReference myreff,dreff;
    Member member;

    private void clearControl(){
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        t1=findViewById(R.id.txt1);
        t2=findViewById(R.id.txt2);
        t3=findViewById(R.id.txt3);
        t4=findViewById(R.id.txt4);
        btnview=findViewById(R.id.btnview);
        btnup=findViewById(R.id.btnupdate);
        btndel=findViewById(R.id.btndelete);

        member=new Member();

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myreff= FirebaseDatabase.getInstance().getReference().child("Member").child("4");
                myreff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()) {
                            String name = dataSnapshot.child("name").getValue().toString();
                            String age = dataSnapshot.child("age").getValue().toString();
                            String number = dataSnapshot.child("number").getValue().toString();
                            String height = dataSnapshot.child("height").getValue().toString();

                            t1.setText(name);
                            t2.setText(age);
                            t3.setText(number);
                            t4.setText(height);

                        }else {
                            Toast.makeText(getApplicationContext(),"No source to display",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myreff= FirebaseDatabase.getInstance().getReference().child("Member");
                myreff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("4")){

                            try{
                                member.setName(t1.getText().toString().trim());
                                member.setAge(Integer.parseInt(t2.getText().toString().trim()));
                                member.setNumber(Long.parseLong(t3.getText().toString().trim()));
                                member.setHeight(Float.parseFloat(t4.getText().toString().trim()));

                                 dreff= FirebaseDatabase.getInstance().getReference().child("Member").child("4");
                                 dreff.setValue(member);
//
//                               clearControl();
                               Toast.makeText(getApplicationContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();
                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid contact number",Toast.LENGTH_SHORT).show();
                            }
                        }else
                            Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myreff= FirebaseDatabase.getInstance().getReference().child("Member");
                myreff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("4")){
                            myreff= FirebaseDatabase.getInstance().getReference().child("Member").child("4");
                            myreff.removeValue();
                            clearControl();
                            Toast.makeText(getApplicationContext(),"Data deleted successfully",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "No Source to delete.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
