package com.pvobrien.github.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AnAbleThingAdapter extends RecyclerView.Adapter<AnAbleThingAdapter.AnAbleThingViewHolder> {

    public ArrayList<AnAbleThing> anAbleThings;

    public AnAbleThingAdapter(ArrayList<AnAbleThing> anAbleThings, OnInteractWithAbleThingsListener listener) {
        this.anAbleThings = anAbleThings;

    // TODO find the error
    }

    public static class AnAbleThingViewHolder extends RecyclerView.ViewHolder {

        public AnAbleThingViewHolder(@NonNull View itemView) {
            super(itemView);

            holder.anAbleThings = anAbleThings.get(position) // Todo: All in this code block needs to move.

            TextView thingableNameTextView = itemView.findViewById(R.id.thingableName);
            TextView thingableQuantityTextView = itemView.findViewById(R.id.thingableQuantityTV);
            TextView thingableHowMuchTextView = itemView.findViewById(R.id.howMuchThingableTV);
            thingableNameTextView.setText(holder.);
            thingableQuantityTextView.setText(holder.);
            thingableHowMuchTextView.setText(holder.);
        }
    }

    @NonNull
    @Override
    public AnAbleThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_an_able_thing, parent, false); // inflate == render

        AnAbleThingViewHolder viewHolder = new AnAbleThingViewHolder(view); // instantiation.

        // Make it clickable! on below...
        // gets attached to view.

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(viewHolder.anAbleThing.NAME); // TODO needs to be corrected

                listener.ableThingListener(viewHolder.anAbleThing) // TODO: figure a smarter name. also, listener needs to be real
            }
        });


        return viewHolder;
    }

    public static interface OnInteractWithAbleThingsListener(){
        public void ableThingListener(AnAbleThing anAbleThing);
    }

    @Override
    public void onBindViewHolder(@NonNull AnAbleThingViewHolder holder, int position) { // Actually Attaches data! int position is position in/of arrayList.
        holder. // TODO missing something here.

    }

    @Override
    public int getItemCount() {
        return 20;
    }


}