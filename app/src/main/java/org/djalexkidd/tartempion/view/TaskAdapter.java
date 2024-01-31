package org.djalexkidd.tartempion.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.djalexkidd.tartempion.R;
import org.djalexkidd.tartempion.domain.Task;

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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task task = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(this.resourceId, parent, false);
        }
        TextView taskTitle = convertView.findViewById(R.id.task_title_text_view);
        taskTitle.setText(task.getTitle());
        TextView taskDate = convertView.findViewById(R.id.task_date_text_view);
        taskDate.setText(task.getDateAsString());
        return convertView;
    }
}
