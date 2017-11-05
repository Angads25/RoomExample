package com.github.angads25.roomexample.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.github.angads25.roomexample.dao.AppDao;
import com.github.angads25.roomexample.model.Todo;

/**
 * <p>
 * Created by Angad Singh on 5/11/17.
 * </p>
 */

@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();
}
