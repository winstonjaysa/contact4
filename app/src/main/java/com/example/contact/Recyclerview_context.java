package com.example.contact;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Recyclerview_context {

    private Context mcontext;
    private locationadapter mlocationadapter;
    String userNameGG="";
    public void setConfig(RecyclerView recyclerView, Context context, List<location> locations, List<String> keys,String userName) {

        userNameGG=userName;
        mcontext = context;
        mlocationadapter = new locationadapter(locations,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mlocationadapter);
    }


    class locationitemview extends RecyclerView.ViewHolder {

        private TextView title1;
        private TextView title2;
        private TextView title3;

        private String key;

        public locationitemview(ViewGroup parent) {


            super(LayoutInflater.from(mcontext).
            inflate(R.layout.location_list_item,parent,false));

            title1 = (TextView) itemView.findViewById(R.id.tv1);
            title2 = (TextView) itemView.findViewById(R.id.tv2);
            title3 = (TextView) itemView.findViewById(R.id.tv3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext,location_u_d.class);
                    intent.putExtra("key",key);
                    intent.putExtra("name",title1.getText().toString());
                    intent.putExtra("attraction",title2.getText().toString());
                    intent.putExtra("budget",title3.getText().toString());

                    mcontext.startActivity(intent);

                }
            });

        }

        public void bind(location location, String key) {

            if(location.getUsername().equals(userNameGG)) {

                title1.setText(location.getName());
                title2.setText(location.getAttraction());
                title3.setText(location.getBudget());

                this.key = key;
            }

        }

    }
        class locationadapter extends RecyclerView.Adapter<locationitemview> {

            private List<location> mlocationlist;
            private List<String> key;

            public locationadapter(List<location> mlocationlist, List<String> key) {
                this.mlocationlist = mlocationlist;
                this.key = key;
            }


            @Override
            public locationitemview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new locationitemview(parent);
            }

            @Override
            public void onBindViewHolder(@NonNull locationitemview holder, int position) {

                holder.bind(mlocationlist.get(position), key.get(position));
            }

            @Override
            public int getItemCount() {
                return mlocationlist.size();
            }
        }

    }


