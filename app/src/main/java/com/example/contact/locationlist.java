package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
}
