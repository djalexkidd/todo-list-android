package org.djalexkidd.tartempion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.welcomeMessageTextView = findViewById(R.id.welcome_message);
        this.welcomeMessageTextView.setText(
                getResources().getText(R.string.hello_world) + " Solacroup"
        );
    }

}