package com.example.admin.project.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.project.R;

public class Myprofileactivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofileactivity);

        String display=getIntent().getStringExtra(" EMAIL");
         textView=(TextView) findViewById(R.id.myproid);
         textView.setText(display);
    }
}
