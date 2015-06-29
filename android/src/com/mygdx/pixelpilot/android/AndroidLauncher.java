package com.mygdx.pixelpilot.android;

import android.app.ActivityManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import com.amazon.device.gamecontroller.GameController;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.pixelpilot.PixelPilot;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.controller.PlayerController;

public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        config.hideStatusBar = true;
        config.useWakelock = true;
        setOverviewCardColor();
        initialize(new PixelPilot(), config);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        boolean handled = false;

        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_CENTER:
                System.out.println("center!");
                handled = true;
                break;
            case KeyEvent.KEYCODE_BUTTON_A:
                // ... handle selections
                handled = true;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                System.out.println("left!");
                handled = true;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                System.out.println("right!");
                handled = true;
                break;
        }
        return handled || super.onKeyDown(keyCode, event);
    }

    /**
     * Thanks to: https://www.bignerdranch.com/blog/polishing-your-Android-overview-screen-entry/
     */
    private void setOverviewCardColor(){

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//
//            TypedValue typedValue = new TypedValue();
//            Resources.Theme theme = getTheme();
//            theme.resolveAttribute(android.R.attr.colorPrimary, typedValue, true);
//            int color = typedValue.data;
//
//
//            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//            ActivityManager.TaskDescription td = new ActivityManager.TaskDescription(null, bm, color);
//
//            this.setTaskDescription(td);
//            bm.recycle();
//        }
    }
}
