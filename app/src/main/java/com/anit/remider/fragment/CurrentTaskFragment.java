package com.anit.remider.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anit.remider.R;
import com.anit.remider.adapter.CurrentTaskAdapter;
import com.anit.remider.model.ModelTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentTaskFragment extends Fragment {

    RecyclerView rvCurrentTasks;
    RecyclerView.LayoutManager layoutManager;

    private CurrentTaskAdapter adapter;


    public CurrentTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        rvCurrentTasks = (RecyclerView) rootView.findViewById(R.id.rvCurrentTask);
        layoutManager = new LinearLayoutManager(getActivity());

        rvCurrentTasks.setLayoutManager(layoutManager);

        adapter = new CurrentTaskAdapter();
        rvCurrentTasks.setAdapter(adapter);


        // Inflate the layout for this fragment
        return rootView;
    }


    public void addTask(ModelTask newTask) {

        int position = -1;
        for (int i = 0; i < adapter.getItemCount(); i++) {
            ModelTask task = (ModelTask) adapter.getItem(position);
            if (newTask.getDate() < task.getDate()) {
                position = i;
                break;
            }
            if (position != -1) {
                adapter.addItem(position, newTask);
            } else {

                adapter.addItem(newTask);
            }

        }
    }



}
