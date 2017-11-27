package com.liu.apidemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * 添加ButterKnife调用的BaseActivity
 */
public abstract class BaseActivity extends Activity
{
    public abstract int getContentViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
    }
}
