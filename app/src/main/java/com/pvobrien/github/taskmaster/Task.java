package com.pvobrien.github.taskmaster;

public class Task {
        public String taskTitle;
        public String taskDetails;
        public String taskStateOfDoing;

    public Task(String taskTitle, String taskDetails, String taskStateOfDoing) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskStateOfDoing = taskStateOfDoing;
    }
}

// DONE: Create a Task class.
// DONE: A Task should have a title, a body, and... TODO: include a state.
// TODO: The state should be one of “new”, “assigned”, “in progress”, or “complete”.