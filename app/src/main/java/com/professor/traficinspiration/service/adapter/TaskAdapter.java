package com.professor.traficinspiration.service.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.activity.OrderDetails;
import com.professor.traficinspiration.model.tasks.Task;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context context;
    private List<Task> values;

    public TaskAdapter(@NonNull Context context, List<Task> values) {
        super(context, R.layout.task_list_item_pagination, values);

        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final Task item = values.get(position);

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.task_list_item_pagination, parent, false);
        }

        Button taskButton = (Button) row.findViewById(R.id.task_item_button);
        TextView taskDescriptionTextView = (TextView) row.findViewById(R.id.task_item_description);
        TextView taskTitleTextView = (TextView) row.findViewById(R.id.task_item_title);
        final ImageView taskCompleteCheckBox = (ImageView) row.findViewById(R.id.task_item_check_box_image);


        taskButton.setText(item.buttonString);
        taskDescriptionTextView.setText(item.description);
        taskTitleTextView.setText(item.titleString);

        if (item.isCompleted()) {
            taskCompleteCheckBox.setImageResource(R.drawable.checked);
//            taskButton.setVisibility(View.GONE);
        } else {
            taskCompleteCheckBox.setImageResource(R.drawable.unchecked);
            taskButton.setVisibility(View.VISIBLE);
        }

//        row.setTag(item.taskName);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = item.executeTask(ApplicationContext.getContext());
                if (success) {
                    taskCompleteCheckBox.setImageResource(R.drawable.checked);
                }
            }
        });

        return row;
    }
}
