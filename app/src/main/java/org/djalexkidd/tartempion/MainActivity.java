package org.djalexkidd.tartempion;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tasks = new ArrayList<>();

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void addTask(String task) {
        this.tasks.add(task);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}