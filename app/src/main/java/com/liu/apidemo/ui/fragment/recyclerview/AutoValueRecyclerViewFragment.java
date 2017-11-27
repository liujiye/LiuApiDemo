package com.liu.apidemo.ui.fragment.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.liu.apidemo.R;
import com.liu.apidemo.adapter.recyclerview.AutoValueRecyclerAdapter;
import com.liu.apidemo.entity.Man;
import com.liu.apidemo.entity.User;
import com.liu.apidemo.ui.activity.recyclerView.DividerLine;
import com.liu.apidemo.ui.activity.recyclerView.OnRecyclerViewItemClickListener;
import com.liu.apidemo.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 简单的RecyclerView演示
 */
public class AutoValueRecyclerViewFragment extends BaseFragment
{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private AutoValueRecyclerAdapter mAdapter;

    public AutoValueRecyclerViewFragment()
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
//        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);

        try
        {
            String strJson = "{\"id\":0,\"name\":\"name0\", \"score\":{\"yuwen\":100, \"shuxue\":98}}";
            Man man = Man.parse(strJson, Man.class);
            User user = User.parse(strJson, User.class);
            String strJsonArray = "[{\"id\":0,\"name\":\"name0\", \"score\":{\"yuwen\":100, \"shuxue\":98}},{\"id\":1,\"name\":\"name1\", \"score\":{\"yuwen\":99, \"shuxue\":98}},{\"id\":2,\"name\":\"name2\", \"score\":{\"yuwen\":95, \"shuxue\":99}},{\"id\":3,\"name\":\"name3\", \"score\":{\"yuwen\":100, \"shuxue\":100}}]";
            // 从json中解析出list的方法
//            Type collectionType = new TypeToken<Collection<Man>>(){}.getType();
//            men = Man.gson.fromJson(strJsonArray, collectionType);
            //men = Man.parseArray(new JSONArray(strJsonArray), Man.class);
            List<Man> mens = Man.parseList(strJsonArray, new TypeToken<List<Man>>(){});
            List<Man> mens2 = Man.parseList(strJsonArray, Man.class);
            Man[] mans = Man.parse(strJsonArray, Man[].class);
            List<Man> men = Arrays.asList(mans);
            int size = (men != null ? men.size() : 0);

            final List<Man> dataset = new ArrayList<>();
            for (int i = 0; i < 100; i++)
            {
//            dataset.add(User.create(i, "name " + i));
                dataset.add(men.get(i % size));
            }
            mAdapter = new AutoValueRecyclerAdapter(dataset);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener()
            {
                @Override
                public void onItemClick(View view, int position)
                {
                    Man user = dataset.get(position);
                    Toast.makeText(getActivity(), user.toJsonString(), Toast.LENGTH_SHORT).show();
                }
            });

            DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
            dividerLine.setSize(1);
            dividerLine.setColor(0xFFDDDDDD);
            mRecyclerView.addItemDecoration(dividerLine);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_add, R.id.btn_del})
    void onViewClick(View v)
    {
        switch (v.getId())
        {
        case R.id.btn_add:
            Man man = new Man();
            man.id = 1000;
            man.name = "add";
            mAdapter.addItem(man, 3);
            break;
        case R.id.btn_del:
            mAdapter.removeItem(0);
            break;
        }
    }
}
