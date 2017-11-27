package com.liu.apidemo.ui.fragment.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.liu.apidemo.R;
import com.liu.apidemo.adapter.recyclerview.SimpleRecyclerAdapter;
import com.liu.apidemo.ui.activity.recyclerView.DividerLine;
import com.liu.apidemo.ui.activity.recyclerView.OnRecyclerViewItemClickListener;
import com.liu.apidemo.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * 简单的RecyclerView演示
 */
public class SimpleRecyclerViewFragment extends BaseFragment
{
    private RecyclerView mRecyclerView;
    private SimpleRecyclerAdapter mAdapter;

    public SimpleRecyclerViewFragment()
    {
    }

    @Override
    public int getContentViewId()
    {
        return R.layout.fragment_simple_recycler_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initUI(mRootView);
    }

    private void initUI(View root)
    {
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);

        final List<String> dataset = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            dataset.add("item" + i);
        }
        mAdapter = new SimpleRecyclerAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                String str = dataset.get(position);
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            }
        });

        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(1);
        dividerLine.setColor(0xFFDDDDDD);
        mRecyclerView.addItemDecoration(dividerLine);
    }

    @OnClick({R.id.btn_add, R.id.btn_del})
    void onViewClick(View v)
    {
        switch (v.getId())
        {
        case R.id.btn_add:
            mAdapter.addItem("addItem", 3);
            break;
        case R.id.btn_del:
            mAdapter.removeItem(0);
            break;
        }
    }
}
