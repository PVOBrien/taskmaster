package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class recyclerViewGeneric extends AppCompatActivity implements AnAbleThingAdapter.OnInteractWithAbleThingsListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_generic);

        ArrayList<AnAbleThing> thingables = new ArrayList<>();

        AnAbleThing thingOne = new AnAbleThing("a thing, one", 1, 1.00f);
        AnAbleThing thingTwo = new AnAbleThing("Second thing, one", 2, 2.20f);
        AnAbleThing thingThree = new AnAbleThing("That Third", 3, 3.30f);

        thingables.add(thingOne);
        thingables.add(thingTwo);
        thingables.add(thingThree);

        RecyclerView recyclerView = findViewById(R.id.thingableRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AnAbleThingAdapter(thingables));
    }

    @Override
    public void ableThingListener(AnAbleThing anAbleThing) {

    }
}