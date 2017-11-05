package com.github.angads25.roomexample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * <p>
 * Created by Angad Singh on 5/11/17.
 * </p>
 */

@Entity(tableName = "todo")
public class Todo {

    @PrimaryKey
    public long _id;

    @ColumnInfo(name = "title")
    public String todoTitle;

    @ColumnInfo(name = "text")
    public String todoText;

    @ColumnInfo(name = "time")
    public long timeStamp;
}
