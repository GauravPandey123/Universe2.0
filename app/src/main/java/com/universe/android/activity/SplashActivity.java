package com.universe.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

import com.universe.android.R;
import com.universe.android.helper.Prefs;

/**
 * Created by gaurav.pandey on 02-01-2018.
 */

public class SplashActivity  extends BaseActivity{

    private Runnable mRunnable;
    private Handler mHandler = new Handler();
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, MainActivity.class));
            }
        };
        mHandler.postDelayed(mRunnable, SPLASH_TIME_OUT);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    public void onBackPressed() {
        mHandler.removeCallbacks(mRunnable);
        super.onBackPressed();
    }
}
