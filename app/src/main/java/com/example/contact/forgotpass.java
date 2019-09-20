package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpass extends AppCompatActivity {


    private Button resetpasswordsendemailbutton;
    private EditText resetemailinput;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        mAuth = FirebaseAuth.getInstance();

        resetpasswordsendemailbutton = (Button) findViewById(R.id.reset_password_email_button);
        resetemailinput = (EditText) findViewById(R.id.reset_password_EMAIL);

        resetpasswordsendemailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String useremail = resetemailinput.getText().toString();

                if(TextUtils.isEmpty(useremail)){

                    Toast.makeText(forgotpass.this,"Please enter the email",Toast.LENGTH_LONG).show();
                }
                else{

                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(forgotpass.this,"Email sent",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(forgotpass.this,MainActivity.class));
                            }
                            else{

                                String message = task.getException().getMessage();
                                Toast.makeText(forgotpass.this,"Sorry Error occurred "+message,Toast.LENGTH_LONG).show();

                            }


                        }
                    });
                }

            }
        });

    }
}
