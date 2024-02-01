package org.djalexkidd.tartempion.view;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.djalexkidd.tartempion.R;
import org.djalexkidd.tartempion.domain.Task;

import java.util.Arrays;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private int resourceId;

    public TaskAdapter(
            @NonNull Context context,
            int resource,
            @NonNull List<Task> objects
    ) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    private TaskAdapterOnClickListener taskAdapterOnClickListener;
    public void setTaskAdapterOnClickListener(TaskAdapterOnClickListener taskAdapterOnClickListener)
    {
        this.taskAdapterOnClickListener=taskAdapterOnClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task task = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(this.resourceId, parent, false);
        }
        // Init views
        RelativeLayout container = convertView.findViewById(R.id.task_container);
        TextView taskTitle = convertView.findViewById(R.id.task_title_text_view);
        TextView taskDate = convertView.findViewById(R.id.task_date_text_view);
        CheckBox taskDone = convertView.findViewById(R.id.task_done_checkbox);
        // Declare listener on views
        taskDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setDone(isChecked);
                applyStrike(task.getDone(), Arrays.asList(taskTitle, taskDate)
                );
            }
        });
        container.setOnClickListener(v -> {
            taskAdapterOnClickListener.onClick(position);
        });
        // Mutate views
        container.setBackgroundColor(
                ContextCompat.getColor(getContext(), com.google.android.material.R.color.design_default_color_secondary)
        );
        taskDate.setText(task.getDate().toString());
        taskTitle.setText(task.getTitle());
        taskDone.setChecked(task.getDone());
        return convertView;
    }

    private void applyStrike(boolean isChecked, List<TextView> textViews) {
        int paintFlags = isChecked ?
                Paint.STRIKE_THRU_TEXT_FLAG  :
                Paint.LINEAR_TEXT_FLAG;
        for (TextView tv : textViews) {
            tv.setPaintFlags(paintFlags);
        }
    }

    public interface TaskAdapterOnClickListener {
        void onClick(int taskPosition);
    }
}
