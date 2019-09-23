package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Laundry_u_d extends AppCompatActivity {

    DatabaseReference myreff;
    LaundryDetails laundryDetails;

    private Button btnupdate,btndelete;
    private EditText itemcategory,noofitems;
    private TextView txtTotal_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_update_del);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Laundry");


        itemcategory=findViewById(R.id.Category);
        noofitems=findViewById(R.id.noofitemss);

        txtTotal_up=(TextView) findViewById(R.id.txtTotal_up);

        //btnview=findViewById(R.id.btnview);
        btnupdate=(Button) findViewById(R.id.btnUpdate);
        btndelete=(Button) findViewById(R.id.btnDelete);

        laundryDetails =new LaundryDetails();



        myreff= FirebaseDatabase.getInstance().getReference().child("Laundry").child("1");
        myreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    String RoomType = dataSnapshot.child("category").getValue().toString();
                    String OutDate = dataSnapshot.child("noOfItems").getValue().toString();
                    String totp=dataSnapshot.child("price").getValue().toString();

                    txtTotal_up.setText(totp);
                    itemcategory.setText(RoomType);
                    noofitems.setText(OutDate);

                }else {
                    Toast.makeText(getApplicationContext(),"No source to display",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myreff= FirebaseDatabase.getInstance().getReference().child("RoomDetails");
                myreff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){

                            try{
                                laundryDetails.setCategory(itemcategory.getText().toString().trim());
                                laundryDetails.setNoOfItems(Integer.parseInt(noofitems.getText().toString().trim()));

                                int x=Integer.parseInt(noofitems.getText().toString().trim());
                                int tot=x*10;

                                laundryDetails.setPrice(tot);
                                laundryDetails.setUserName("ghj");
//

                                myreff= FirebaseDatabase.getInstance().getReference().child("Laundry").child("1");
                                myreff.setValue(laundryDetails);
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

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myreff= FirebaseDatabase.getInstance().getReference().child("Laundry");
                myreff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            myreff= FirebaseDatabase.getInstance().getReference().child("Laundry").child("1");
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

    private void clearControl(){
        itemcategory.setText("");
        noofitems.setText("");

    }
}
