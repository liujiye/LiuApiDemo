package com.liu.apidemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.liu.apidemo.R;

public class MainFragmentActivity extends FragmentActivity
{
    public static <T extends Fragment> void show(Context context, Class<T> cls)
    {
        Intent intent = new Intent(context, MainFragmentActivity.class);
        intent.putExtra("fragment_class", cls);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        showFragment();
    }

    private void showFragment()
    {
        try
        {
            Class cls = (Class) getIntent().getExtras().get("fragment_class");
            Fragment fragment = (Fragment) cls.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // setCustomAnimations (int enter, int exit, int popEnter, int popExit)
//            transaction.setCustomAnimations(R.anim.fragment_slide_left_in, R.anim.fragment_slide_right_out, R.anim.fragment_slide_left_in,
//                    R.anim.fragment_slide_right_out);
            transaction.replace(R.id.lay_fragment, fragment);
            transaction.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
