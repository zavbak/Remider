package com.anit.remider.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anit.remider.R;
import com.anit.remider.Utils;
import com.anit.remider.fragment.CurrentTaskFragment;
import com.anit.remider.fragment.TaskFragment;
import com.anit.remider.model.Item;
import com.anit.remider.model.ModelTask;

/**
 * Created by 79900 on 08.07.2016.
 */
public class CurrentTaskAdapter extends TaskAdapter {


    private static final int TYPE_TASK = 0;
    private static final int TYPE_SEPARATOR = 1;

    public CurrentTaskAdapter(CurrentTaskFragment taskFragment) {
        super(taskFragment);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {

            case TYPE_TASK:

                View v = LayoutInflater.from(viewGroup.getContext()).
                        inflate(R.layout.model_task, viewGroup, false);

                TextView title = (TextView) v.findViewById(R.id.tvTaskTitle);
                TextView date = (TextView) v.findViewById(R.id.tvTaskData);

                return new TaskViewHolder(v, title, date);

            case TYPE_SEPARATOR:
                break;
            default:
                return null;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        Item item = items.get(position);

        if (item.isTask()) {
            viewHolder.itemView.setEnabled(true);
            ModelTask task = (ModelTask) item;
            TaskViewHolder taskViewHolder = (TaskViewHolder) viewHolder;
            taskViewHolder.title.setText(task.getTitle());

            if (task.getDate() != 0) {
                taskViewHolder.date.setText(Utils.getFullDate(task.getDate()));
            }

        }

    }


    @Override
    public int getItemViewType(int position) {

        // Возможно надо -1
        if (items.get(position).isTask()) {
            return TYPE_TASK;
        } else {
            return TYPE_SEPARATOR;
        }
    }


}
