package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private EditText emailText;
    private EditText passText;
    private Button btnOpen;
    Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = (EditText) findViewById(R.id.email);
        passText = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        //customize action bar
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_action_bar));
        }
        //end


    }


    public void loginbtnclicked (View view){

        String email = emailText.getText().toString().trim();
        String password = passText.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                       checkUserExists();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Incorrect Email or Password",Toast.LENGTH_LONG).show();
                    }

                }
            });

        }
        else{

            Toast.makeText(this,"Cant have empty fields",Toast.LENGTH_LONG).show();
        }
    }

    public void checkUserExists(){

    final String user_id = mAuth.getCurrentUser().getUid();
    mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.hasChild(user_id)){


                Intent dashIntent = new Intent(MainActivity.this,dashboard.class);
                startActivity(dashIntent);
//                message.setUsername(dataSnapshot.child("Users").child(user_id).getValue().toString());
                //dashIntent.putExtra("uid",user_id);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    }
    @Override
    public void onBackPressed()
    {
//       // startActivity(new Intent(this, AccActivity.class));
        //finish();
       // moveTaskToBack(true);
    }


    public void forgotpass(View view){

        Intent forgotIntent = new Intent(MainActivity.this,forgotpass.class);
        startActivity(forgotIntent);



    }

    public void register(View view){

        Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(registerIntent);



    }


}
