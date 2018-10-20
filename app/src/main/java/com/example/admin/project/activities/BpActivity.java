package com.example.admin.project.activities;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.admin.project.R;

import org.json.JSONException;
import org.json.JSONObject;

public class BpActivity extends AppCompatActivity {
    String url = "http://192.168.4.1/"; //Define your IP Address of wifi module/
     Button BP,PR;
    TextView BPText;
    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp);


        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Discriptions"
        );
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        BP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_rl = url+"bp";
                SelectTask task = new SelectTask(url_rl);
                task.execute();
               updateStatus();
            }
        });
        PR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_rl = url+"pr";
                SelectTask task = new SelectTask(url_rl);
                task.execute();
                updateStatus();
            }
        });
    }

    public void sendData(String str) {
        updateButtonStatus(str);
    }
    public void updateStatus(){
        String url_rl = url+"status";
        StatusTask task = new StatusTask(url_rl, this);
        task.execute();
    }

    //Function for updating Button Status
    private void updateButtonStatus(String jsonStrings){
        try {
            JSONObject json = new JSONObject(jsonStrings);

            String Bloodpressure = json.getString("BP");
            String mirror_light = json.getString("PR");

            if(Bloodpressure.equals("1")){
                BP.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.power_on);
            }else{
                BP.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.power_off);
            }
        if(mirror_light.equals("1")){
               PR.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.power_on);
            }else{
                PR.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.power_off);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
