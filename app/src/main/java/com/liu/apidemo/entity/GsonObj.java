package com.liu.apidemo.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class GsonObj<E> implements Serializable
{
    //public static final Gson gson = new Gson();
    public static final Gson gson = new GsonBuilder()
            //.setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    public static <T> T parse(String strJson, Class<T> cls)
    {
        try
        {
            return gson.fromJson(strJson, cls);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static <T> T parse(JSONObject jsonObject, Class<T> cls)
    {
        try
        {
            return gson.fromJson(jsonObject.toString(), cls);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 从JSON字符串中反序列化List<T>集合对象(使用google官方推荐的方法)
     *
     * @param strJson   JSON字符串
     * @param typeToken 需要解析的对象类型
     * @param <T>       对象类型
     * @return
     */
    public static <T> T parseList(String strJson, TypeToken<T> typeToken)
    {
        return gson.fromJson(strJson, typeToken.getType());
    }

    /**
     * 从JSON字符串中反序列化List<T>集合对象（使用反射）
     *
     * @param strJson JSON字符串
     * @param pClass  T对象的Class
     * @param <T>     对象类型
     * @return List<T>集合对象
     */
    public static <T> List<T> parseList(String strJson, Class<T> pClass)
    {
        return gson.fromJson(strJson, new ListOfJson<T>(pClass));
    }

    public String toJsonString()
    {
        return gson.toJson(this);
    }

    public JSONObject toJson()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(toJsonString());
            return jsonObject;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
