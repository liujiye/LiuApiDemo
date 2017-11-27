package com.liu.apidemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 基类
 */
public abstract class BaseFragment extends Fragment
{
    public abstract int getContentViewId();
    // protected abstract void initAllMembersView(Bundle savedInstanceState);

    protected Context context;
    protected View mRootView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        // 绑定framgent
        unbinder = ButterKnife.bind(this, mRootView);
        this.context = getActivity();
        //initAllMembersView(savedInstanceState);
        return mRootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        // 解绑
        unbinder.unbind();
    }
}
