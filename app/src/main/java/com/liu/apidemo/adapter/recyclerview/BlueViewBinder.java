package com.liu.apidemo.adapter.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.apidemo.R;
import com.liu.apidemo.entity.recyclerview.Blue;

import me.drakeet.multitype.ItemViewBinder;

/**
 * recycleView多类型数据绑定
 */
public class BlueViewBinder extends ItemViewBinder<Blue, BlueViewBinder.ViewHolder>
{
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent)
    {
        View root = inflater.inflate(R.layout.recycler_view_item_blue, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Blue blue)
    {
        holder.blue = blue;
        holder.color.setText(blue.color);
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @NonNull
        private final TextView color;
        private Blue blue;

        ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.color = (TextView) itemView.findViewById(R.id.tv_blue);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(v.getContext(), blue.color, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
