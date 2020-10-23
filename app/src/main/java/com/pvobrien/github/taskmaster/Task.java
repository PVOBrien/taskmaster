package com.pvobrien.github.taskmaster;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    long id;

    public String taskTitle;
    public String taskDetails;
    public String taskStateOfDoing;

    public Task(String taskTitle, String taskDetails, String taskStateOfDoing) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskStateOfDoing = taskStateOfDoing;
    }
}