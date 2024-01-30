package org.djalexkidd.tartempion.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import org.djalexkidd.tartempion.MainActivity;
import org.djalexkidd.tartempion.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksFragment extends Fragment {

    public TasksFragment() {
        // Required empty public constructor
    }

    public static TasksFragment newInstance() {
        TasksFragment fragment = new TasksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(
                R.layout.fragment_tasks, container, false
        );
    }

    private TextView textView;
    private FloatingActionButton floatingActionButton;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Init variables
        textView = view.findViewById(R.id.tasks_text_view);
        floatingActionButton = view.findViewById(R.id.add_task_button);
        // Alter
        notifyAndUpdateTask("Cours Android");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                textView.setVisibility(
//                        textView.getVisibility() == View.GONE ?
//                                View.VISIBLE :
//                                View.GONE
//                );
                openDialogAddTask();
            }
        });
    }

    private void openDialogAddTask() {
        EditText editText = new EditText(getContext());
        AlertDialog dialog = new AlertDialog
                .Builder(getContext())
                .setTitle("Ajouter une tâche")
                .setMessage("Que souhaitez-vous faire ?")
                .setView(editText)
                .setPositiveButton("Ajouter", (dialogInterface, i) -> {
                    notifyAndUpdateTask(editText.getText().toString());
                })
                .setNegativeButton("Annuler", null)
                .create();
        dialog.show();
    }

    private void notifyAndUpdateTask(String task) {
        ((MainActivity) getActivity()).addTask(task);
        textView.setText(((MainActivity) getActivity()).getTasks().toString());
    }
}