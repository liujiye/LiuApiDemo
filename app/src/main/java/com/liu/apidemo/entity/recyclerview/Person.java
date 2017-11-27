package com.liu.apidemo.entity.recyclerview;

import java.io.Serializable;

/**
 * Author: jiye
 * Email:
 * Date: 2017年11月1日11:51:45
 */
public class Person implements Serializable
{
    private int id;
    private String name;
    private int age;
    private int type;

    public Person(int id, String name, int age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(int id, String name, int age, int type)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
