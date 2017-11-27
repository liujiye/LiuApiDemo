package com.liu.apidemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.liu.apidemo.R;
import com.liu.apidemo.ui.fragment.EventBusFirstFragment;
import com.liu.apidemo.ui.fragment.GreenDaoFragment;
import com.liu.apidemo.ui.fragment.GreenDaoOtherFragment;
import com.liu.apidemo.ui.fragment.recyclerview.AutoValueRecyclerViewFragment;
import com.liu.apidemo.ui.fragment.recyclerview.ComplexRecyclerViewFragment;
import com.liu.apidemo.ui.fragment.recyclerview.PersonRecyclerViewFragment;
import com.liu.apidemo.ui.fragment.recyclerview.SimpleRecyclerViewFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_start_green_dao, R.id.btn_start_green_dao_other,
              R.id.btn_start_lite_orm, R.id.btn_start_lite_orm_other,
              R.id.btn_event_bus, R.id.btn_simple_recycler_view,
              R.id.btn_person_recycler_view, R.id.btn_complex_recycler_view,
              R.id.btn_auto_value_recycler_view})
    public void onViewClick(View view)
    {
        switch (view.getId())
        {
        case R.id.btn_start_green_dao:
//            Intent intentGreenDao = new Intent(MainActivity.this, GreenDaoActivity.class);
//            startActivity(intentGreenDao);
            MainFragmentActivity.show(this, GreenDaoFragment.class);
            break;
        case R.id.btn_start_green_dao_other:
//            Intent intentGreenDaoOther = new Intent(MainActivity.this, GreenDaoOtherActivity.class);
//            startActivity(intentGreenDaoOther);
            MainFragmentActivity.show(this, GreenDaoOtherFragment.class);
            break;
        case R.id.btn_start_lite_orm:
            Intent intentLiteOrm = new Intent(MainActivity.this, LiteOrmActivity.class);
            startActivity(intentLiteOrm);
            break;
        case R.id.btn_start_lite_orm_other:
            Intent intentLiteOrmOther = new Intent(MainActivity.this, LiteOrmOtherActivity.class);
            startActivity(intentLiteOrmOther);
            break;
        case R.id.btn_event_bus:
//            Intent intentEventBus = new Intent(MainActivity.this, EventBusFirstActivity.class);
//            startActivity(intentEventBus);
            MainFragmentActivity.show(this, EventBusFirstFragment.class);
            break;
        case R.id.btn_simple_recycler_view:
            MainFragmentActivity.show(this, SimpleRecyclerViewFragment.class);
            break;
        case R.id.btn_person_recycler_view:
            MainFragmentActivity.show(this, PersonRecyclerViewFragment.class);
            break;
        case R.id.btn_complex_recycler_view:
            MainFragmentActivity.show(this, ComplexRecyclerViewFragment.class);
            break;
        case R.id.btn_auto_value_recycler_view:
            MainFragmentActivity.show(this, AutoValueRecyclerViewFragment.class);
            break;
        }
    }
}
