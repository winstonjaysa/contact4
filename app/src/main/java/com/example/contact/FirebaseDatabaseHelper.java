package com.example.contact;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReffOrder;
    private List<OrderDetails> orderDetails=new ArrayList<>();


    public  interface DataStatus{
        void DataIsLoaded(List<OrderDetails> orderDetails, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper() {
        mDatabase= FirebaseDatabase.getInstance();
        mReffOrder=mDatabase.getReference("Order");

    }

    public void readOrders(final DataStatus dataStatus){

        mReffOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderDetails.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    OrderDetails orderDetail1=keyNode.getValue(OrderDetails.class);
                    orderDetails.add(orderDetail1);
                }
                dataStatus.DataIsLoaded(orderDetails,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateOrder(String key,OrderDetails orderDetails,final DataStatus dataStatus){
        mReffOrder.child(key).setValue(orderDetails).addOnSuccessListener(
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                }
        );
    }

    public void deleteOrder(String key,final DataStatus dataStatus){

        mReffOrder.child(key).setValue(null).addOnSuccessListener(
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                }
        );
    }

}
