package com.liu.apidemo.adapter.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.apidemo.R;
import com.liu.apidemo.entity.recyclerview.Green;

import me.drakeet.multitype.ItemViewBinder;

/**
 * recycleView多类型数据绑定
 */
public class GreenViewBinder extends ItemViewBinder<Green, GreenViewBinder.ViewHolder>
{
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent)
    {
        View root = inflater.inflate(R.layout.recycler_view_item_green, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Green green)
    {
        holder.green = green;
        holder.color.setText(green.color);
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        @NonNull
        private final TextView color;
        private Green green;

        ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.color = (TextView) itemView.findViewById(R.id.tv_green);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(v.getContext(), green.color, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
