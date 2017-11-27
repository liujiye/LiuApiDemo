package com.liu.apidemo.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.liu.apidemo.entity.Man;
import com.liu.apidemo.ui.activity.recyclerView.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * 使用autovalue的简单的RecyclerView Adapter例子
 */
public class AutoValueRecyclerAdapter extends Adapter<AutoValueRecyclerAdapter.ViewHolder>
        implements View.OnClickListener
{
    private List<Man> mDataset;
    private OnRecyclerViewItemClickListener mItemClickListener;

    /**
     * @Description: TODO
     */
    public AutoValueRecyclerAdapter(List<Man> dataset)
    {
        mDataset = dataset;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener)
    {
        this.mItemClickListener = listener;
    }

    public void addItem(Man content, int position)
    {
        mDataset.add(position, content);
        notifyItemInserted(position); //Attention!
    }

    public void removeItem(int position)
    {
        mDataset.remove(position);
        notifyItemRemoved(position);//Attention!
    }

    @Override
    public void onClick(View v)
    {
        if (mItemClickListener != null)
        {
            //注意这里使用getTag方法获取数据
            mItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView mTextView;

        /**
         * @param itemView
         * @Description: TODO
         */
        public ViewHolder(View itemView)
        {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public AutoValueRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = View.inflate(parent.getContext(),
                android.R.layout.simple_list_item_1, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(AutoValueRecyclerAdapter.ViewHolder holder, int position)
    {
        Man user = mDataset.get(position);
        holder.mTextView.setText(user.toJsonString());
        holder.mTextView.setTag(position);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount()
    {
        return mDataset.size();
    }
}
