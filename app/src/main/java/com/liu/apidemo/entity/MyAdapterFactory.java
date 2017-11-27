package com.liu.apidemo.entity;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;


/**
 * Created by liujiye-pc on 2017/11/24.
 */
@GsonTypeAdapterFactory
public class MyAdapterFactory implements TypeAdapterFactory
{
    // Static factory method to access the package
    // private generated implementation
//    public static TypeAdapterFactory create()
//    {
//        return new AutoValueGson_MyAdapterFactory();
//    }

    /**
     * Returns a type adapter for {@code type}, or null if this factory doesn't
     * support {@code type}.
     *
     * @param gson
     * @param type
     */
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type)
    {
        return null;
    }
}
