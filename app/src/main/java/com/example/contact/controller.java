package com.example.contact;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class controller {


    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceLocation;
    private List<location> locations = new ArrayList<>();

    public interface datastatus{
        void Dataisloaded(List<location> locations,List<String> keys);
        void Dataisinserted();
        void Dataisupdated();
        void Dataisdeleted();

    }

    public controller(){

        mDatabase = FirebaseDatabase.getInstance();
        mReferenceLocation = mDatabase.getReference("location");


    }
    public void readlocation(final datastatus datastatus){

        mReferenceLocation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                locations.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keynode : dataSnapshot.getChildren()){

                    keys.add(keynode.getKey());
                    location location = keynode.getValue(location.class);
                    locations.add(location);
                }

             datastatus.Dataisloaded(locations,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }





}
