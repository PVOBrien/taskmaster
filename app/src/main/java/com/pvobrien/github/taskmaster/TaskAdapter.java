package com.pvobrien.github.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amplifyframework.datastore.generated.model.Task;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TasksToDoViewHolder> {

    public ArrayList<Task> tasksToDo;
    public OnInteractWithTasksToDoListener listener;

    public TaskAdapter(ArrayList<Task> tasksToDo, OnInteractWithTasksToDoListener listener) {
        this.tasksToDo = tasksToDo;
        this.listener = listener;
    }

    public static class TasksToDoViewHolder extends RecyclerView.ViewHolder {

        public Task tasks;
        public View itemView;

        public TasksToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override // This gets called when a fragment (List item) pops into existence
    public TasksToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tasks, parent, false); // inflate == render

        TasksToDoViewHolder viewHolder = new TasksToDoViewHolder(view); // instantiation.

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println(viewHolder.tasks.getTaskTitle());
                listener.tasksToDoListener(viewHolder.tasks);
            }
        });
        return viewHolder;
    }

    public static interface OnInteractWithTasksToDoListener {
        public void tasksToDoListener(Task task);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksToDoViewHolder holder, int position) { // Actually Attaches data! int position is position in/of arrayList.
        holder.tasks = tasksToDo.get(position);

        TextView taskNameTextView = holder.itemView.findViewById(R.id.taskNameTextView);
        TextView taskDetailsTextView = holder.itemView.findViewById(R.id.taskDetailTextView);
        TextView taskState = holder.itemView.findViewById(R.id.taskStateTextView);
        taskNameTextView.setText(holder.tasks.getTaskTitle());
        taskDetailsTextView.setText(holder.tasks.getTaskDetails());
        taskState.setText(holder.tasks.getTaskStateOfDoing());
    }

    @Override
    public int getItemCount() {
        return tasksToDo.size();
    }
}