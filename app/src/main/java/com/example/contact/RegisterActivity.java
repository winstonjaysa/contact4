package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailField;
    private EditText passField;
    private CheckBox checkBox;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        passField = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.cb);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        ActionBar actionBar = getSupportActionBar();
    }

    @Override
    public void onBackPressed()
    {

    }


    public void  Registerbtnclicked (View view){

        final String name = nameField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String password = passField.getText().toString().trim();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && checkBox.isChecked()){



            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                   if(task.isSuccessful()){

                        String user_id = mAuth.getCurrentUser().getUid();
                       DatabaseReference current_user_db = mDatabase.child(user_id);
                       current_user_db.child("Name").setValue(name);
                       Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                       Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                       mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       startActivity(mainIntent);

                   }


                }
            });
        }
        else{
            Toast.makeText(this,"Cant have empty fields",Toast.LENGTH_LONG).show();
        }

    }



}
