package com.example.admin.project.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.admin.project.R;

public class Emergency_Calls extends AppCompatActivity {
    Button bub,bub2,bub3,bub4;
    private android.support.v7.widget.Toolbar t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency__calls);
        t4=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(t4);
        getSupportActionBar().setTitle("Appointments !");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        bub= (Button) findViewById(R.id.call);
        bub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:9901823934"));
                startActivity(callIntent);
            }
        });

      bub2= (Button) findViewById(R.id.call2);
        bub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:8867722623"));
                startActivity(callIntent);
            }
        });

        bub3= (Button) findViewById(R.id.call3);
        bub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:9916707367"));
                startActivity(callIntent);
            }
        });
        bub4= (Button) findViewById(R.id.call4);
        bub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:8660198719"));
                startActivity(callIntent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, BpActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

