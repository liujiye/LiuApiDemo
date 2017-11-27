package com.liu.apidemo.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liu.apidemo.R;
import com.liu.apidemo.entity.recyclerview.Person;

import java.util.List;

/**
 * Created by liujiye-pc on 2017/11/1.
 */
public class PersonAdapter extends RecyclerView.Adapter
{
    public static interface OnRecyclerViewListener
    {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener)
    {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private List<Person> list;

    public PersonAdapter(List<Person> list)
    {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_test_item_person, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i)
    {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        holder.position = i;
        Person person = list.get(i);
        holder.nameTv.setText(person.getName());
        holder.ageTv.setText(person.getAge() + "岁");
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public View rootView;
        public TextView nameTv;
        public TextView ageTv;
        public int position;

        public PersonViewHolder(View itemView)
        {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);
            ageTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_age_tv);
            rootView = itemView.findViewById(R.id.recycler_view_test_item_person_view);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if (null != onRecyclerViewListener)
            {
                onRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v)
        {
            if (null != onRecyclerViewListener)
            {
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}
