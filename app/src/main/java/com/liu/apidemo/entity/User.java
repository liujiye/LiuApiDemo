package com.liu.apidemo.entity;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

/**
 * 测试AutoValue
 */
@AutoValue
public abstract class User extends GsonObj<User>
{
    @SerializedName("id")
    public abstract int id();

    @SerializedName("name")
    public abstract String name();

    public static User create(int id, String name)
    {
        return new AutoValue_User(id, name);
    }

//    public static TypeAdapter<User> typeAdapter(Gson gson)
//    {
//        return new AutoValue_User.GsonTypeAdapter(gson);
//    }

//    public static TypeAdapter<User> typeAdapter(Gson gson)
//    {
//        return new AutoValue_User.GsonTypeAdapter(gson);
//    }
}
