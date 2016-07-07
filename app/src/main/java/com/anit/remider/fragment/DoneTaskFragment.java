package com.anit.remider.fragment;


import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anit.remider.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoneTaskFragment extends Fragment {

    RecyclerView rvDoneTasks;
    RecyclerView.LayoutManager layoutManager;


    public DoneTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_done_task, container, false);

        rvDoneTasks = (RecyclerView) rootView.findViewById(R.id.rvDoneTask);
        layoutManager = new LinearLayoutManager(getActivity());

        rvDoneTasks.setLayoutManager(layoutManager);


        return rootView;

    }

}
