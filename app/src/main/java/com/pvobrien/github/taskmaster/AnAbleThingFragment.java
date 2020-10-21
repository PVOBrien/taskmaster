package com.pvobrien.github.taskmaster;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnAbleThingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnAbleThingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "theActualThing";
    private static final String ARG_PARAM2 = "howMany";
    private static final String ARG_PARAM3 = "someNumbersOfThing";

    // TODO: Rename and change types of parameters
    private String mtheActualThing;
    private String mhowMany;
    private String msomeNumbersOfThing;

    public AnAbleThingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param theActualThing Parameter 1.
     * @param howMany Parameter 2.
     * @param someNumbersOfThing Parameter 3.
     * @return A new instance of fragment AnAbleThingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnAbleThingFragment newInstance(String theActualThing, String howMany, String someNumbersofThing) {
        AnAbleThingFragment fragment = new AnAbleThingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, theActualThing);
        args.putString(ARG_PARAM2, howMany);
        args.putString(ARG_PARAM2, someNumbersofThing);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mtheActualThing = getArguments().getString(ARG_PARAM1);
            mhowMany = getArguments().getString(ARG_PARAM2);
            msomeNumbersofThing = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_an_able_thing, container, false);
    }

    // TODO: two more lifecycle methods of a fragment in RV...
    // onAttach - to set listeners
    // onDetach = good for tracking user details.
}