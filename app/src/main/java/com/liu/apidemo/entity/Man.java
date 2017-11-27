package com.liu.apidemo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 测试Gson的注解
 */
public class Man extends GsonObj<Man>
{
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("score")
    public Score score;

    public static class Score
    {
        @SerializedName("yuwen")
        public int yuwen;

        @SerializedName("shuxue")
        public int shuxue;
    }
}
