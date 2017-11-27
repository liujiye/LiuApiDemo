package com.liu.apidemo.ui.fragment.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liu.apidemo.R;
import com.liu.apidemo.adapter.recyclerview.PersonAdapter;
import com.liu.apidemo.entity.recyclerview.Person;
import com.liu.apidemo.ui.activity.recyclerView.RecycleViewDivider;
import com.liu.apidemo.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 复杂的RecyclerView演示
 */
public class PersonRecyclerViewFragment extends BaseFragment
{
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private List<Person> personList = new ArrayList<>();

    public PersonRecyclerViewFragment()
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
        // 使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
        mRecyclerView.setHasFixedSize(true);

        // LinearLayoutManager，如果你需要显示的是横向滚动的列表或者竖直滚动的列表，则使用这个LayoutManager
        // 显然，我们要实现的是ListView的效果，所以需要使用它
        // 生成这个LinearLayoutManager之后可以设置他滚动的方向，默认竖直滚动，所以这里没有显式地设置
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        // 设置横向滚动
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        initData();
        PersonAdapter adapter = new PersonAdapter(personList);
        //adapter.setOnRecyclerViewListener(getActivity());
        mRecyclerView.setAdapter(adapter);

        RecycleViewDivider dividerLine = new RecycleViewDivider(LinearLayoutManager.HORIZONTAL, 0xFFDDDDDD);
        mRecyclerView.addItemDecoration(dividerLine);
    }

    private void initData()
    {
        for (int i = 0; i < 30; ++i)
        {
            Person person = new Person(i, "Android_" + i, 10 + i);
            personList.add(person);
        }
    }
}
