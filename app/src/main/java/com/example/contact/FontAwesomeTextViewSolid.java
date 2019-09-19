package com.example.contact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class FontAwesomeTextViewSolid extends TextView {
    public FontAwesomeTextViewSolid(Context context){
        super(context);
        init();
    }
    public FontAwesomeTextViewSolid(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }
    public FontAwesomeTextViewSolid(Context context, AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init();
    }
    private void init(){
        Typeface typeface=Typeface.createFromAsset(getContext().getAssets(),"FontAwesome.ttf");
        setTypeface(typeface);
    }
}
