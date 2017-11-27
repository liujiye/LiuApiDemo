package com.liu.apidemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.liu.apidemo.R;
import com.liu.apidemo.event.TestEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by liujiye-pc on 2017/7/19.
 *
 * RecyclerView Demo
 */
public class EventBusSecondActivity extends Activity
{
    private TextView mTvEvent;
    private static int mEventCount = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_second);

        mTvEvent = (TextView) findViewById(R.id.tv_receive_event);

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                case R.id.btn_post:
                    EventBus.getDefault().post(new TestEvent(String.valueOf(mEventCount++)));
                    break;
                case R.id.btn_post_sticky:
                    EventBus.getDefault().postSticky(new TestEvent(String.valueOf(mEventCount++)));
                    break;
                }
            }
        };

        findViewById(R.id.btn_post).setOnClickListener(clickListener);
        findViewById(R.id.btn_post_sticky).setOnClickListener(clickListener);

        boolean sticky = getIntent().getBooleanExtra("sticky", false);
        if (sticky)
        {
            EventBus.getDefault().registerSticky(this);
        }
        else
        {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(TestEvent event)
    {
        String str = mTvEvent.getText().toString();
        str += "\n" + event.text;
        mTvEvent.setText(str);
    }
}
