package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class NewActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText editMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        editMessage = (EditText) findViewById(R.id.editmessagee);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");
        mMessageList = (RecyclerView) findViewById(R.id.messageRec);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(linearLayoutManager);
        mAuth = FirebaseAuth.getInstance();




        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(NewActivity.this, RegisterActivity.class));
                }
            }
        };
    }

    @Override
    public void onBackPressed()
    {
//       // startActivity(new Intent(this, AccActivity.class));
        //finish();
        // moveTaskToBack(true);
    }





    public void sendbtnclicked(View view) {

        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());
        final String messagevalue = editMessage.getText().toString().trim();
        if (!TextUtils.isEmpty(messagevalue)) {
            final DatabaseReference newPost = mDatabase.push();
            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    newPost.child("content").setValue(messagevalue);
                    newPost.child("username").setValue(dataSnapshot.child("Name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                        }
                    });
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            mMessageList.smoothScrollToPosition(mMessageList.getAdapter().getItemCount());
            //      Toast.makeText(NewActivity.this,"SENT",Toast.LENGTH_LONG).show();


        }
    }

    public void signOut(View view) {
      //  mAuth = FirebaseAuth.getInstance();

        Intent intent1 = new Intent(this, frontpage.class);
        //intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);
      //  mAuth.signOut();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseRecyclerAdapter<Message, MessageViewHolder> FBRA = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.singlemessagelayout,
                MessageViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(MessageViewHolder messageViewHolder, Message message, int i) {

                messageViewHolder.setContent(message.getContent());
                messageViewHolder.setUsername(message.getUsername());
                messageViewHolder.setTime();
            }

        };

        mMessageList.setAdapter(FBRA);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public MessageViewHolder(View itemView) {

            super(itemView);
            mView = itemView;
        }

        public void setContent(String content) {

            TextView message_content = (TextView) mView.findViewById(R.id.messagetext);
            message_content.setText(content);

        }

        public void setUsername(String username) {

            TextView username_content = (TextView) mView.findViewById(R.id.usernametext);
            username_content.setText(username);

        }

        public void setTime(){

            int hourOfDay,minute,state;
            String rstate;
            Calendar rightNow = Calendar.getInstance();
            hourOfDay = rightNow.get(Calendar.HOUR);
            minute = rightNow.get(Calendar.MINUTE);
            state =rightNow.get(Calendar.AM_PM);
            if(state==1){
                rstate = "PM";
            }
            else{
                rstate = "AM";
            }
            String currenttime = hourOfDay + ":" + minute + " " + rstate;
            TextView username_content = (TextView) mView.findViewById(R.id.time);
            username_content.setText(currenttime);




        }
    }

}
