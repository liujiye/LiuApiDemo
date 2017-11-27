package com.liu.apidemo.entity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

/**
 * Created by liujiye-pc on 2017/7/14.
 */

@Entity(indexes = {
        @Index(value = "text, date DESC", unique = true)
})
public class Note extends GsonObj<Note>
{
    @Id
    private Long id;

    @NotNull
    private String text;
    private String comment;
    @Convert(converter = NoteTypeConverter.class, columnType = String.class)
    private NoteType type;
    private Date date;

    @Generated(hash = 1057906788)
    public Note(Long id, @NotNull String text, String comment, NoteType type,
            Date date) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.type = type;
        this.date = date;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public NoteType getType()
    {
        return type;
    }

    public void setType(NoteType type)
    {
        this.type = type;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
