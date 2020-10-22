package com.pvobrien.github.taskmaster;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "taskName";
    private static final String ARG_PARAM2 = "taskBodyDetails";
    private static final String ARG_PARAM3 = "taskState";

    // TODO: Rename and change types of parameters
    private String mTaskName;
    private String mTaskBodyDetails;
    private String mTaskState;

    public TaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param theActualThing Parameter 1.
     * @param howMany Parameter 2.
     * @param taskState Parameter 3.
     * @return A new instance of fragment TaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskFragment newInstance(String theActualThing, String howMany, String taskState) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, theActualThing);
        args.putString(ARG_PARAM2, howMany);
        args.putString(ARG_PARAM3, taskState);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTaskName = getArguments().getString(ARG_PARAM1);
            mTaskBodyDetails = getArguments().getString(ARG_PARAM2);
            mTaskState = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    // TODO: two more lifecycle methods of a fragment in RV...
    // onAttach - to set listeners
    // onDetach = good for tracking user details.
}