package com.liu.apidemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.liu.apidemo.R;
import com.liu.apidemo.event.TestEvent;
import com.liu.apidemo.theApp;
import com.liu.apidemo.ui.activity.MainFragmentActivity;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * EventBus演示
 */
public class EventBusFirstFragment extends BaseFragment
{
    @BindView(R.id.tv_receive_event) TextView mTvEvent;
    private static int mEventCount = 0;

    public EventBusFirstFragment()
    {
    }

    @Override
    public int getContentViewId()
    {
        return R.layout.activity_event_bus_first;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);

        initUI(mRootView);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private void initUI(View root)
    {
    }

    public void onEventMainThread(TestEvent event)
    {
        String str = mTvEvent.getText().toString();
        str += "\n" + event.text;
        mTvEvent.setText(str);

        theApp.showToast(event.text);
    }


    @OnClick({R.id.btn_post, R.id.btn_post_sticky,
              R.id.btn_register, R.id.btn_register_sticky})
    public void onViewClick(View view)
    {
        switch (view.getId())
        {
        case R.id.btn_post:
            EventBus.getDefault().post(new TestEvent(String.valueOf(mEventCount++)));
            break;
        case R.id.btn_post_sticky:
            EventBus.getDefault().postSticky(new TestEvent(String.valueOf(mEventCount++)));
            break;
        case R.id.btn_register:
//            Intent intent = new Intent(getActivity(), EventBusSecondActivity.class);
//            startActivity(intent);
            MainFragmentActivity.show(getActivity(), EventBusSecondFragment.class);
            break;
        case R.id.btn_register_sticky:
//            Intent intentSticky = new Intent(getActivity(), EventBusSecondActivity.class);
//            intentSticky.putExtra("sticky", true);
//            startActivity(intentSticky);
            MainFragmentActivity.show(getActivity(), EventBusSecondStickyFragment.class);
            break;
        }
    }
}
