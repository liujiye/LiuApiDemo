package com.liu.apidemo.adapter.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liu.apidemo.R;
import com.liu.apidemo.entity.recyclerview.Blue;
import com.liu.apidemo.entity.recyclerview.Green;
import com.liu.apidemo.entity.recyclerview.PostList;
import com.liu.apidemo.entity.recyclerview.Red;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * recycleView多类型数据绑定
 */
public class HorizontalItemViewBinder extends ItemViewBinder<PostList, HorizontalItemViewBinder.ViewHolder>
{
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent)
    {
        View root = inflater.inflate(R.layout.recycler_view_item_horizontal_list, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull PostList postList)
    {
        holder.setPosts(postList.items);
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        private RecyclerView recyclerView;
        private MultiTypeAdapter adapter;

        ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            /* adapter 只负责灌输、适配数据，布局交给 LayoutManager，可复用 */
            adapter = new MultiTypeAdapter();    // 或者直接使用 MultiTypeAdapter 更加方便
            adapter.register(Red.class, new RedViewBinder());
            adapter.register(Blue.class, new BlueViewBinder());
            adapter.register(Green.class, new GreenViewBinder());
            recyclerView.setAdapter(adapter);
            /* 在此设置横向滑动监听器，用于记录和恢复当前滑动到的位置，略 */
        }

        private void setPosts(Items items)
        {
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    }
}
