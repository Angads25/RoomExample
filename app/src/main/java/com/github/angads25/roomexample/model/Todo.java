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

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoText() {
        return todoText;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
