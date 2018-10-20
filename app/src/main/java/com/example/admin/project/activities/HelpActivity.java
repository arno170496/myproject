package com.example.admin.project.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.project.R;

import java.util.Random;


public class HelpActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    Handler setDelay;
    Runnable startDelay;
   // TextView resu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        //resu=(TextView) findViewById(R.id.results);
        setDelay= new Handler();
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BASIC VITAL SIGNS!");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu2,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            navigateUpTo(new Intent(this, UserActivity.class));
            return true;
        }
        switch (item.getItemId()) {
            case R.id.Call:
                Toast.makeText(this, "Emergency Calls", Toast.LENGTH_SHORT).show();
                Intent c=new Intent(this,Emergency_Calls.class);
                startActivity(c);
                return  true;
            case R.id.Map:
                Toast.makeText(this, "Medical Centers", Toast.LENGTH_SHORT).show();
                Intent m=new Intent(this,Medical_centers.class);
                startActivity(m);
                return  true;
            case R.id.Remainders2:
                Toast.makeText(this, "Drug Usage", Toast.LENGTH_SHORT).show();
                Intent r=new Intent(this,MainActivity2.class);
                startActivity(r);
                return true;
            case R.id.Notifications:
                Toast.makeText(this, "Remainder Notifications", Toast.LENGTH_SHORT).show();
                Intent n=new Intent(this,MainActivity3.class);
                startActivity(n);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public  void generate(View view)
    {
        Toast.makeText(this, "PUSH THE ON BUTTON OF THE KIT TO CONTINUE !", Toast.LENGTH_LONG).show();
        startDelay=new Runnable() {
            @Override
            public void run() {
                // SharedPreferences shared=getSharedPreferences("values", Context.MODE_PRIVATE);
                Random rand = new Random();
                int number=rand.nextInt(140-105)+105;
                TextView text=(TextView) findViewById(R.id.textview12);
                String mystring= String.valueOf(number);
                text.setText(mystring);

                int number2=rand.nextInt(95-65)+65;
                TextView text2=(TextView) findViewById(R.id.textview13);
                String mystring2= String.valueOf(number2);
                text2.setText(mystring2);

                int pulse=rand.nextInt(99-75)+75;
                TextView text3=(TextView) findViewById(R.id.textview14);
                String mystring3= String.valueOf(pulse);
                text3.setText(mystring3);
              /*  SharedPreferences.Editor editor= shared.edit();
                editor.putString("Diastolic", text.getText().toString());
                editor.putString("Systolic",text2.getText().toString());
                editor.putString("Pulse rate",text3.getText().toString());
                editor.apply();
                String diastolic=shared.getString("Diastolic","");
                String systolic=shared.getString("Systolic","");
                String pulserate=shared.getString("Pulse rate","");
                resu.setText(diastolic + " " + systolic + " " + pulserate);*/
            }
        };
        setDelay.postDelayed(startDelay,50000);// Delay of sixty five seconds

    }

    public void helpme(View view)
    {
        Intent callIntent=new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:108"));
        startActivity(callIntent);
    }

}
