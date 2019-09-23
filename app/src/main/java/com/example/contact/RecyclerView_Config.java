package com.example.contact;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private OrderAdapter mOrderAdapter;
    DatePickerDialog datePickerDialog;



    public void setConfig(RecyclerView recyclerView, Context context, List<OrderDetails> orders, List<String> keys){
        mContext = context;
        mOrderAdapter=new OrderAdapter(orders,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mOrderAdapter);
    }

    class OrderItemView extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mAmount;
        private TextView mTime;
        private TextView mDate;
        private TextView mStatus;
        private TextView txtStatus;
        private String key;

        ConstraintLayout orderItemLay;
        ImageView meal_pic;

        DatabaseReference databaseReference;

        FirebaseUser user;
        String uid;


        public OrderItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.order_list,parent,false));

            mName=(TextView)itemView.findViewById(R.id.txtview_name);
            mAmount=(TextView)itemView.findViewById(R.id.txtview_amount);
            mTime=(TextView)itemView.findViewById(R.id.txtview_time);
            mDate=(TextView)itemView.findViewById(R.id.date);
            mStatus=(TextView)itemView.findViewById(R.id.pastdate);
            txtStatus=(TextView)itemView.findViewById(R.id.txtStatus);

            orderItemLay=(ConstraintLayout) itemView.findViewById(R.id.orderItemLay);
            meal_pic=(ImageView) itemView.findViewById(R.id.meal_pic);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(mContext, activity_Order_del_update.class);
                        intent.putExtra("key", key);
                        intent.putExtra("name", mName.getText().toString());
                        intent.putExtra("amount", mAmount.getText().toString());
                        intent.putExtra("time", mTime.getText().toString());
                        intent.putExtra("needDate", mDate.getText().toString());
                        intent.putExtra("status", txtStatus.getText()).toString();


                        mContext.startActivity(intent);


                    }
                });


        }
        public void bind(OrderDetails orderDetails,String key){

             mName.setText(orderDetails.getMeal());
             mAmount.setText(orderDetails.getAmount());
             mTime.setText(orderDetails.getTime());
             mDate.setText(orderDetails.getDate());
             txtStatus.setText(orderDetails.getStatus());

             if (orderDetails.getStatus().equals("1")) {
                 mStatus.setVisibility(View.INVISIBLE);
             }

             this.key = key;

//             if (orderDetails.getTime().equals("Breakfast")) {
//                 //mTime.setTextColor(Color.parseColor("#fe435b"));
//                 //orderItemLay.setBackgroundColor(Color.parseColor("#fe7b45"));
//             } else if (orderDetails.getTime().equals("Launch")) {
//                 //orderItemLay.setBackgroundColor(Color.parseColor("#289cda"));
//             } else if (orderDetails.getTime().equals("Tea")) {
//                 //orderItemLay.setBackgroundColor(Color.parseColor("#01d5b1"));
//             } else if (orderDetails.getTime().equals("Dinner")) {
//                 //orderItemLay.setBackgroundColor(Color.parseColor("#f3b163"));
//             }

             if (orderDetails.getMeal().equals("Pizza")) {
                 meal_pic.setBackgroundResource(R.drawable.grilled_pizza);
             } else if (orderDetails.getMeal().equals("Cup Cake")) {
                 meal_pic.setBackgroundResource(R.drawable.buttercream_and_cupcakes);
             } else if (orderDetails.getMeal().equals("BBQ")) {
                 meal_pic.setBackgroundResource(R.drawable.baked_bbq_chicken_1);
             } else if (orderDetails.getMeal().equals("Tacos")) {
                 meal_pic.setBackgroundResource(R.drawable.vegan_tacos);
             }else if (orderDetails.getMeal().equals("Soup with sandwich")) {
                 meal_pic.setBackgroundResource(R.drawable.order_soup);
             }else if (orderDetails.getMeal().equals("Vegetarian pasta")) {
                 meal_pic.setBackgroundResource(R.drawable.order_vegetarian_pasta);
             }else if (orderDetails.getMeal().equals("Grilled cheese sandwich")) {
                 meal_pic.setBackgroundResource(R.drawable.order_grilled_cheese);
             }else if (orderDetails.getMeal().equals("Thai chicken noodles")) {
                 meal_pic.setBackgroundResource(R.drawable.order_thai_chicken_noodles);
             }
             //juice
             else if (orderDetails.getMeal().equals("Watermelon juice")) {
                meal_pic.setBackgroundResource(R.drawable.order_watermelon_juice);
             }else if (orderDetails.getMeal().equals("Coffee")) {
                 meal_pic.setBackgroundResource(R.drawable.order_coffee);
             }
        }
    }
    class OrderAdapter extends RecyclerView.Adapter<OrderItemView>{
        private List<OrderDetails> mOderList;
        private List<String> mKeys;

        public OrderAdapter(List<OrderDetails> mOderList, List<String> mKeys) {
            this.mOderList = mOderList;
            this.mKeys = mKeys;
        }

        @Override
        public OrderItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new OrderItemView(parent);
        }

        @Override
        public void onBindViewHolder(OrderItemView holder, int position) {
            holder.bind(mOderList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mOderList.size();
        }
    }
}
