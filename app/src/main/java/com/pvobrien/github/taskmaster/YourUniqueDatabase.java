package com.pvobrien.github.taskmaster;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Task.class}, version = 1)
public abstract class YourUniqueDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
