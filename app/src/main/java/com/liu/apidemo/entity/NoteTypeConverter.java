package com.liu.apidemo.entity;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by liujiye-pc on 2017/7/14.
 */

public class NoteTypeConverter implements PropertyConverter<NoteType, String>
{
    @Override
    public NoteType convertToEntityProperty(String databaseValue) {
        return NoteType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(NoteType entityProperty) {
        return entityProperty.name();
    }
}
