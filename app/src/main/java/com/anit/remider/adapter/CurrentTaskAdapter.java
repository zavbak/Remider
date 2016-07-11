package com.anit.remider.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
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

import de.hdodenhof.circleimageview.CircleImageView;

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
                CircleImageView priority = (CircleImageView) v.findViewById(R.id.ovTaskPriority);


                return new TaskViewHolder(v, title, date, priority);

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
            final ModelTask task = (ModelTask) item;
            final TaskViewHolder taskViewHolder = (TaskViewHolder) viewHolder;


            final View itemView = taskViewHolder.itemView;
            final Resources resourses = itemView.getResources();

            taskViewHolder.title.setText(task.getTitle());
            if (task.getDate() != 0) {
                taskViewHolder.date.setText(Utils.getFullDate(task.getDate()));
            }else {
                taskViewHolder.date.setText(null);
            }

            itemView.setVisibility(View.VISIBLE);
            itemView.setBackgroundColor(resourses.getColor(R.color.gray_50));

            taskViewHolder.title.setTextColor(resourses.getColor(R.color.primary_text_default_material_light));
            taskViewHolder.date.setTextColor(resourses.getColor(R.color.secondary_text_default_material_light));
            taskViewHolder.priority.setColorFilter(resourses.getColor(task.getPriorityColor()));
            taskViewHolder.priority.setImageResource(R.drawable.ic_checkbox_blank_circle_white_48dp);

            taskViewHolder.priority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.setStatus(ModelTask.STATUS_DONE);

                    itemView.setBackgroundColor(resourses.getColor(R.color.gray_200));

                    taskViewHolder.title.setTextColor(resourses.getColor(R.color.primary_text_disabled_material_light));
                    taskViewHolder.date.setTextColor(resourses.getColor(R.color.secondary_text_disabled_material_light));
                    taskViewHolder.priority.setColorFilter(resourses.getColor(task.getPriorityColor()));

                    ObjectAnimator flipIn = ObjectAnimator.ofFloat(taskViewHolder.priority,"rotationY",-180f,0f);
                    flipIn.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });

                }
            });
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
