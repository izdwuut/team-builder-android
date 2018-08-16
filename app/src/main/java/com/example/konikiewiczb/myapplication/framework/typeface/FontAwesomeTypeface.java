package com.example.konikiewiczb.myapplication.framework.typeface;

import android.content.Context;

import com.example.konikiewiczb.myapplication.Config;

public class FontAwesomeTypeface {
    static android.graphics.Typeface font;

    public static android.graphics.Typeface get(Context context) {
        if(font == null) {
            font = android.graphics.Typeface.createFromAsset(context.getAssets(), Config.ICONS_FONT);
        }
        return font;
    }
}
