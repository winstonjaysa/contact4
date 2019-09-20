package com.example.contact;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jgabrielfreitas.core.BlurImageView;

public class AccActivity extends AppCompatActivity {
    TextView tv,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,navi_txt_back,navi_txt_menu,txt_icon_order,txt_icon_room,txt_icon_travel,txt_icon_laundry,txt_icon_bill;
    LinearLayout li_order,li_room,li_bill,li_travel,li_laundry;
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        findViewById(R.id.progressBar).setVisibility(View.GONE);

        navi_txt_back=(TextView) findViewById(R.id.navi_txt_back);
        navi_txt_menu=(TextView) findViewById(R.id.navi_txt_menu);
        tv=(TextView) findViewById(R.id.txt_icon_order);
        tv2=(TextView) findViewById(R.id.tx2);
        tv3=(TextView) findViewById(R.id.txt_icon_room);
        tv4=(TextView) findViewById(R.id.tx4);
        tv5=(TextView) findViewById(R.id.txt_icon_travel);
        tv6=(TextView) findViewById(R.id.tx6);
        tv7=(TextView) findViewById(R.id.txt_icon_bill);
        tv8=(TextView) findViewById(R.id.tx8);
        tv9=(TextView) findViewById(R.id.txt_icon_laundry);
        tv10=(TextView) findViewById(R.id.tx10);
        txt_icon_order=(TextView) findViewById(R.id.txt_icon_order);
        txt_icon_room=(TextView) findViewById(R.id.txt_icon_room);
        txt_icon_travel=(TextView) findViewById(R.id.txt_icon_travel);
        txt_icon_laundry=(TextView) findViewById(R.id.txt_icon_laundry);
        txt_icon_bill=(TextView) findViewById(R.id.txt_icon_bill);

        li_order=(LinearLayout) findViewById(R.id.li_order);
        li_room=(LinearLayout) findViewById(R.id.li_room);
        li_travel=(LinearLayout) findViewById(R.id.li_travel);
        li_bill=(LinearLayout) findViewById(R.id.li_bill);
        li_laundry=(LinearLayout) findViewById(R.id.li_laundry);

        fragmentContainer =(FrameLayout) findViewById(R.id.fragmentContainer);

        ImageView imgview=(ImageView) findViewById(R.id.img1);



        navi_txt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment();
            }
        });


        li_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txt_icon_order.setTextColor(Color.parseColor("#fe435b"));
                Intent intent=new Intent(AccActivity.this,OrderMain.class);
                startActivity(intent);
            }
        });
        li_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txt_icon_room.setTextColor(Color.parseColor("#fe435b"));
                Intent intent=new Intent(AccActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        li_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txt_icon_travel.setTextColor(Color.parseColor("#fe435b"));
                Intent intent=new Intent(AccActivity.this, UnderDevelopment.class);
                startActivity(intent);

//                pro_pic_view_fragment fragment23=new pro_pic_view_fragment();
//                getSupportFragmentManager().beginTransaction().add(R.id.acc_activity,fragment23).commit();

            }
        });
        li_laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txt_icon_laundry.setTextColor(Color.parseColor("#fe435b"));
                Intent intent=new Intent(AccActivity.this, UnderDevelopment.class);
                startActivity(intent);
            }
        });
        li_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txt_icon_bill.setTextColor(Color.parseColor("#fe435b"));
                Intent intent=new Intent(AccActivity.this, UnderDevelopment.class);
                startActivity(intent);
            }
        });

        //image blur
        BlurImageView blurImageView=(BlurImageView) findViewById(R.id.propicblur);
        blurImageView.setBlur(2);
        //end

        //image round
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.valerie_elash123);
        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        imgview.setImageDrawable(roundedBitmapDrawable);
        //


        Typeface font=Typeface.createFromAsset(getAssets(),"fa-solid-900.ttf");
        //icon from fontawesome site

        navi_txt_back.setTypeface(font);
        navi_txt_back.setText("\uf060");

        navi_txt_menu.setTypeface(font);
        navi_txt_menu.setText("\uf0c9");

        tv.setTypeface(font);
        tv.setText("\uf805");

        tv2.setTypeface(font);
        tv2.setText("\uf054");

        tv3.setTypeface(font);
        tv3.setText("\uf236");

        tv4.setTypeface(font);
        tv4.setText("\uf054");

        tv5.setTypeface(font);
        tv5.setText("\uf5e4");

        tv6.setTypeface(font);
        tv6.setText("\uf054");

        tv7.setTypeface(font);
        tv7.setText("\uf571");

        tv8.setTypeface(font);
        tv8.setText("\uf054");

        tv9.setTypeface(font);
        tv9.setText("\uf553");

        tv10.setTypeface(font);
        tv10.setText("\uf054");

    }

    public void openFragment(){
        pro_pic_view_fragment fragment=pro_pic_view_fragment.newInstance();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragmentContainer,fragment,"BLANCK_FRAGMENT").commit();

    }
}

