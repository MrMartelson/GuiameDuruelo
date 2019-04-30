package com.example.android.guiameduruelo.database;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DatabaseExecutor {
    private static final Object LOCK = new Object();
    private static DatabaseExecutor sInstance;
    private final Executor diskIO;

    private DatabaseExecutor(Executor diskIO) {
        this.diskIO = diskIO;
    }

    public static DatabaseExecutor getInstance(){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = new DatabaseExecutor(Executors.newSingleThreadExecutor());
            }
        }
        return sInstance;
    }
    public Executor diskIO() {
        return diskIO;
    }

}
