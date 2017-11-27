package com.liu.apidemo.ui.fragment.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liu.apidemo.R;
import com.liu.apidemo.adapter.recyclerview.BlueViewBinder;
import com.liu.apidemo.adapter.recyclerview.GreenViewBinder;
import com.liu.apidemo.adapter.recyclerview.HorizontalItemViewBinder;
import com.liu.apidemo.adapter.recyclerview.RedViewBinder;
import com.liu.apidemo.entity.recyclerview.Blue;
import com.liu.apidemo.entity.recyclerview.Green;
import com.liu.apidemo.entity.recyclerview.PostList;
import com.liu.apidemo.entity.recyclerview.Red;
import com.liu.apidemo.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 复杂的RecyclerView演示
 */
public class ComplexRecyclerViewFragment extends BaseFragment
{
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;
    /* Items 等同于 ArrayList<Object> */
    private Items items;

    public ComplexRecyclerViewFragment()
    {
    }

    @Override
    public int getContentViewId()
    {
        return R.layout.fragment_complex_recycler_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initUI(mRootView);
    }

    private void initUI(View root)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MultiTypeAdapter();

        /* 注册类型和 View 的对应关系 */
        mAdapter.register(Red.class, new RedViewBinder());
        mAdapter.register(Blue.class, new BlueViewBinder());
        mAdapter.register(Green.class, new GreenViewBinder());
        mAdapter.register(PostList.class, new HorizontalItemViewBinder());
        mRecyclerView.setAdapter(mAdapter);

        /* 模拟加载数据，也可以稍后再加载，然后使用
         * adapter.notifyDataSetChanged() 刷新列表 */
        items = new Items();
        for (int i = 0; i < 20; i++)
        {
            items.add(new Red("Red " + i));
            items.add(new Green("Green " + i));
            items.add(new Blue("Blue " + i));

            Items horizontalItems = new Items();
            horizontalItems.add(new Red("Red " + i));
            horizontalItems.add(new Green("Green " + i));
            horizontalItems.add(new Blue("Blue " + i));
            horizontalItems.add(new Red("Red " + i));
            horizontalItems.add(new Green("Green " + i));
            horizontalItems.add(new Blue("Blue " + i));
            items.add(new PostList(horizontalItems));
        }
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btn_add, R.id.btn_del})
    void onViewClick(View v)
    {
        switch (v.getId())
        {
        case R.id.btn_add:
            //mAdapter.addItem("addItem", 3);
            break;
        case R.id.btn_del:
            //mAdapter.removeItem(0);
            break;
        }
    }
}
