package com.example.konikiewiczb.myapplication.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.framework.typeface.FontAwesomeTypeface;

public class IconTextView extends TextView {

    private Context context;

    public IconTextView(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }

  private void createView(){
       setGravity(Gravity.CENTER);
       setTypeface(FontAwesomeTypeface.get(getContext()));
  }
}