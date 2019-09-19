package com.example.contact;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class OrderMain extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    Button customAddButton;
    OrderDetails orderDetails;
    FloatingActionButton addorder;
    DatabaseReference myreff;
    LinearLayout l1;
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    private RecyclerView mRecyclerView;

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode== KeyEvent.KEYCODE_BACK)
//            Toast.makeText(getApplicationContext(), "back press",
//                    Toast.LENGTH_LONG).show();
//
//        return false;
//        // Disable back button..............
//    }

    //custom back button activity ,i used this for get icon default color.

//    @Override
//    public void onBackPressed()
//    {
//       // startActivity(new Intent(this, AccActivity.class));
//        //finish();
//    }
    //end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Orders");


        findViewById(R.id.customAddButton).setVisibility(View.INVISIBLE);

        Typeface font=Typeface.createFromAsset(getAssets(),"fa-solid-900.ttf");

        customAddButton=(Button)findViewById(R.id.customAddButton);
        customAddButton.setTypeface(font);
        customAddButton.setText("\uf067");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview_order);
        new FirebaseDatabaseHelper().readOrders(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<OrderDetails> orderDetails, List<String> keys) {
                findViewById(R.id.customAddButton).setVisibility(View.VISIBLE);
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView,OrderMain.this,orderDetails,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
//



      //

        customAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderMain.this,OrderAdd.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.edit_del_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.edit:
                Intent intent=new Intent(this,ViewActivity.class);
                startActivity(intent);
                return true;
            case R.id.del:
                Toast.makeText(this,"Delete",Toast.LENGTH_LONG).show();
                return true;
             default:return super.onContextItemSelected(item);
        }
    }
}
