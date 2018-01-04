package com.universe.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.universe.android.R;

/**
 * Created by gaurav.pandey on 02-01-2018.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    //declare the views here
    private TextView textViewHeader;
    private ImageView imageViewDrawer;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViews();
        setUpElements();

    }

    private void setUpElements() {
        imageViewDrawer.setImageResource(R.drawable.ic_navigation);
        textViewHeader.setText(R.string.app_name);
    }

    private void setUpViews() {
        textViewHeader = findViewById(R.id.textViewHeader);
        imageViewDrawer = findViewById(R.id.imageViewDrawer);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
