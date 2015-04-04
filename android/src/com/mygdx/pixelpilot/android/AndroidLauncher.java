package com.mygdx.pixelpilot.android;

import android.app.ActivityManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.pixelpilot.PixelPilot;

public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        config.hideStatusBar = true;
        setOverviewCardColor();
        initialize(new PixelPilot(), config);
    }

    /**
     * Thanks to: https://www.bignerdranch.com/blog/polishing-your-Android-overview-screen-entry/
     */
    private void setOverviewCardColor(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(android.R.attr.colorPrimary, typedValue, true);
            int color = typedValue.data;


            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            ActivityManager.TaskDescription td = new ActivityManager.TaskDescription(null, bm, color);

            this.setTaskDescription(td);
            bm.recycle();
        }
    }
}
