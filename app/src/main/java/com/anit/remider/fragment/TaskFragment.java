package com.anit.remider.fragment;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.anit.remider.adapter.CurrentTaskAdapter;
import com.anit.remider.model.ModelTask;

/**
 * Created by 79900 on 08.07.2016.
 */
public abstract class TaskFragment extends Fragment{

    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;

    protected CurrentTaskAdapter adapter;



    public void addTask(ModelTask newTask) {

        int position = -1;
        for (int i = 0; i < adapter.getItemCount(); i++) {
            ModelTask task = (ModelTask) adapter.getItem(i);
            if (newTask.getDate() < task.getDate()) {
                position = i;
                break;
            }


        }


        if (position != -1) {
            adapter.addItem(position, newTask);
        } else {

            adapter.addItem(newTask);
        }
    }




}
