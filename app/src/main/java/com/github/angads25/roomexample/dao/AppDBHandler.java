package com.github.angads25.roomexample.dao;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.github.angads25.roomexample.utils.AppConstants;

/**
 * <p>
 * Created by Angad Singh on 5/11/17.
 * </p>
 */

public class AppDBHandler {
    private AppDao appdao;
    private static AppDBHandler ourInstance;

    public static AppDBHandler getInstance(Context context) {
        if(ourInstance == null) {
            ourInstance = new AppDBHandler(context);
        }
        return ourInstance;
    }

    private AppDBHandler(Context context) {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppConstants.DB_NAME).build();
        appdao = db.appDao();
    }

    public AppDao getAppDao() {
        return appdao;
    }
}
