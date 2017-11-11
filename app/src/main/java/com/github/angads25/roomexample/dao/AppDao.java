package com.github.angads25.roomexample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.angads25.roomexample.model.Todo;
import com.github.angads25.roomexample.utils.AppConstants;

import java.util.List;

/**
 * <p>
 * Created by Angad Singh on 5/11/17.
 * </p>
 */

@Dao
public interface AppDao {

    @Query("Select * from " + AppConstants.DB_NAME + " order by time desc")
    List<Todo> getAllTodos();

    @Query("Select * from " + AppConstants.DB_NAME + " where _id = :id order by time desc")
    Todo getTodos(String id);

    @Insert
    void insertTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

}
