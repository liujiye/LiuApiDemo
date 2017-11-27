package com.liu.apidemo.entity.recyclerview;

import android.support.annotation.NonNull;

import me.drakeet.multitype.Items;

/**
 * recycleView 多类型数据
 */
public class PostList
{
    public Items items;
    public int currentPosition;

    public PostList(@NonNull Items posts)
    {
        this.items = posts;
    }
}
