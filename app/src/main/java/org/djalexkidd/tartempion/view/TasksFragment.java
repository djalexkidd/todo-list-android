package org.djalexkidd.tartempion.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    private ListView listView;
    //    private ArrayAdapter<String> tasksAdapter;
    private TaskAdapter taskAdapter;
    private FloatingActionButton floatingActionButton;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Init variables
        listView = view.findViewById(R.id.tasks_list_view);
        floatingActionButton = view.findViewById(R.id.add_task_button);
        // Alter
        floatingActionButton.setOnClickListener(view1 -> openDialogAddTask());
        taskAdapter = new TaskAdapter(
                requireActivity(),
                R.layout.task_item,
                ((MainActivity) requireActivity()).getTasks()
        );
        listView.setAdapter(taskAdapter);
        taskAdapter.setTaskAdapterOnClickListener((taskPosition) -> {
            navigateToTaskForm(taskPosition);
        });
    }

    private void openDialogAddTask() {
        EditText editText = new EditText(getContext());
        AlertDialog dialog = new AlertDialog
                .Builder(requireActivity())
                .setTitle("Ajouter une tâche")
                .setMessage("Que souhaitez-vous faire ?")
                .setView(editText)
                .setPositiveButton("Ajouter",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        tasksAdapter.add(editText.getText().toString());
                        ((MainActivity) requireActivity()).addTask(editText.getText().toString());
                        taskAdapter.notifyDataSetChanged();
                    }
                })
                .setNeutralButton("Personnalisé", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToTaskForm(null);
                    }
                })
                .setNegativeButton("Annuler", null)
                .create();
        dialog.show();
    }
    private void navigateToTaskForm(Integer taskPosition) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TaskFormFragment taskDetailFragment = TaskFormFragment.newInstance(taskPosition);
        fragmentTransaction.replace(R.id.fragment_container_view, taskDetailFragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}