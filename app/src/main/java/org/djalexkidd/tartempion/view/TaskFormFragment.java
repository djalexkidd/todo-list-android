package org.djalexkidd.tartempion.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.djalexkidd.tartempion.MainActivity;
import org.djalexkidd.tartempion.R;
import org.djalexkidd.tartempion.domain.Task;

public class TaskFormFragment extends Fragment {

    // Arguments
    private static final String TASK_POSITION = "taskPosition";
    Integer taskPosition;
    // Datas
    Task task;
    // Views
    EditText taskTitleEditText;
    Button submitButton;

    public TaskFormFragment() {
        // Required empty public constructor
    }

    public static TaskFormFragment newInstance(@Nullable Integer taskPosition) {
        TaskFormFragment fragment = new TaskFormFragment();
        Bundle args = new Bundle();
        if (taskPosition != null) {
            args.putInt(TASK_POSITION, taskPosition);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            taskPosition = getArguments().getInt(TASK_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_form, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTask();
        initViews(view);
        loadDataViews();
        setListenerOnViews();
    }

    private void initTask() {
        Log.d("initTask", taskPosition + "");
        if (taskPosition != null) {
            task = ((MainActivity) requireActivity()).getTasks().get(taskPosition);
        } else {
            task = new Task();
        }
    }
    private void initViews(View view) {
        submitButton = view.findViewById(R.id.task_add_button);
        taskTitleEditText = view.findViewById(R.id.task_title_edit_text);
    }
    private void loadDataViews() {
        Log.d("loadDataViews", task.toString());
        taskTitleEditText.setText(task.getTitle());
        if (taskPosition != null) {
            submitButton.setText("Modifier");
        }
    }
    private void setListenerOnViews() {
        taskTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                task.setTitle(s.toString());
                submitButton.setEnabled(!s.toString().isEmpty());
            }
        });
        submitButton.setOnClickListener(v -> {
            if (taskPosition != null) {
                ((MainActivity) requireActivity()).updateTask(taskPosition, task);
            } else {
                ((MainActivity) requireActivity()).addTask(task.getTitle());
            }
            navigateToTaskList();
        });
    }

    private void navigateToTaskList() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TasksFragment tasksFragment = TasksFragment.newInstance();
        fragmentTransaction.replace(R.id.fragment_container_view, tasksFragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}