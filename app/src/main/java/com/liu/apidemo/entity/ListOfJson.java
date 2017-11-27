package com.liu.apidemo.entity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * class can store the exactly type of list
 */

public class ListOfJson<T> implements ParameterizedType
{
    private Class<?> mType;

    public ListOfJson(Class<T> pType)
    {
        this.mType = pType;
    }

    @Override
    public Type[] getActualTypeArguments()
    {
//        return new Type[0];
        return new Type[] {mType};
    }

    @Override
    public Type getRawType()
    {
        //return null;
        return List.class;
    }

    @Override
    public Type getOwnerType()
    {
        return null;
    }
}
