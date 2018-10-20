package com.example.admin.project.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.project.R;

public class Medical_centers extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_centers);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hospitals Info !");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
    public  void submit(View view)
    {

        String urlAsString="http://www.mallyahospital.net/direction-map.html";
        openwebpage(urlAsString);
    }

    public  void submit1(View view)
    {

        String urlAsString="http://www.apollohospitals.com";
        openwebpage(urlAsString);
    }
    public  void submit2(View view)
    {

        String urlAsString="http://www.manipalhospitals.com/our-specialities/accident-emergency-care";
        openwebpage(urlAsString);
    }
    public  void submit3(View view)
    {

        String urlAsString="http://www.asterbangalore.com/doctors";
        openwebpage(urlAsString);
    }
    public  void submit4(View view)
    {

        String urlAsString="http://www.hosmathospitals.in";
        openwebpage(urlAsString);
    }
    public  void submit5(View view)
    {

        String urlAsString="http://www.fortishealthcare.com/india/doctors";
        openwebpage(urlAsString);
    }
    private void openwebpage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public  void  submitmap(View view)
    {
        String addressString=" ";
        Uri.Builder builder=new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")
                .query(addressString);
        Uri addressUri=builder.build();
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
