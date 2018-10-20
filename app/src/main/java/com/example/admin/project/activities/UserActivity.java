package com.example.admin.project.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.project.R;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
   // private CardView bpcard, tempcard, pulsecard, helpcard;
  //  private Toolbar toolbar;
   ImageView bg_state;
    TextView txt_network;
     private Button BloodPressure,Help;
      //private Button Temperature,PulseRate,
     private Toolbar toolbar;
    String url = "http://192.168.4.1/"; //Define your NodeMCU IP Address here Ex: http://192.168.1.4/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Basic Vital Signs");
        String display = getIntent().getStringExtra("EMAIL");
        Toast.makeText(this, "Welcome \t" + display, Toast.LENGTH_LONG).show();
        bg_state = (ImageView)findViewById(R.id.bg_status);
        txt_network = (TextView)findViewById(R.id.txt_network);
        BloodPressure = (Button) findViewById(R.id.bp);
        //Temperature = (Button) findViewById(R.id.temperature);
       // PulseRate = (Button) findViewById(R.id.pulserate);
        Help = (Button) findViewById(R.id.help);
        // Adding click listeners to all the cards
        BloodPressure.setOnClickListener(this);
      //  Temperature.setOnClickListener(this);
       // PulseRate.setOnClickListener(this);
        Help.setOnClickListener(this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isNetworkAvailable()){
                    bg_state.setImageResource(R.drawable.background_on);
                    txt_network.setText("");
                }else{
                    bg_state.setImageResource(R.drawable.background);
                    txt_network.setText("Cound not connect to the server");
                }
                  //updateStatus();
                handler.postDelayed(this, 2000);
            }
        }, 5000);  //the time is in miliseconds

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
          //  case R.id.Profile:
            //    Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show();
              // Intent a=new Intent(this,Myprofileactivity.class);
                //startActivity(a);
                //return  true;
          //  case R.id.search:
            //    Toast.makeText(this, "Enter date", Toast.LENGTH_SHORT).show();
              //   return  true;
            case R.id.Remainders:
                Toast.makeText(this, "My Remainders", Toast.LENGTH_SHORT).show();
             Intent r=new Intent(this,MainActivity2.class);
               startActivity(r);
                return true;
            case R.id.Logout:
                //to cmpletely log out from the app
                finishAffinity();
                System.exit(0);
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bp:
                Intent i = new Intent(this,BpActivity.class);
                startActivity(i);
                break;
          /*  case R.id.temperature:
                  Intent i1=new Intent(this,TempActivity.class);
                startActivity(i1);
                break;
            case R.id.pulserate:
                 Intent i2=new Intent(this,PulseActivity.class);
                startActivity(i2);
                break;*/
            case R.id.help:
                 Intent i3=new Intent(this,HelpActivity.class);
                startActivity(i3);
                break;

        }
    }
}
