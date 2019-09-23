package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class locationlist extends AppCompatActivity {

    private RecyclerView mRecycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationlist);
        mRecycleview = (RecyclerView) findViewById(R.id.rv);
        new controller().readlocation(new controller.datastatus() {
            @Override
            public void Dataisloaded(List<location> locations, List<String> keys) {
                new Recyclerview_context().setConfig(mRecycleview,locationlist.this,locations,keys);
            }

            @Override
            public void Dataisinserted() {

            }

            @Override
            public void Dataisupdated() {

            }

            @Override
            public void Dataisdeleted() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.locationlist_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_location:
            startActivity(new Intent(this,aluthlocation.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
