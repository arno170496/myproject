package com.example.admin.project.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.example.admin.project.R;
import com.example.admin.project.helper.InputValidation;
import com.example.admin.project.sql.DatabaseHelper;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity=HomeActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;


    private TextInputEditText textInputEditTextEmail;
    private  TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        initListeners();
        initObjects();
    }

       private void initViews()
    {
        nestedScrollView =(NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail=(TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword=(TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail=(TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword=(TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin=(AppCompatButton) findViewById(R.id.Button);
        textViewLinkRegister=(AppCompatTextView) findViewById(R.id.LinkRegister);

    }

    private void initListeners()
    {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initObjects()
    {
        databaseHelper =new DatabaseHelper(activity);
        inputValidation=new InputValidation(activity);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.Button:
                 verifyFromSqlLite();
                break;
            case R.id.LinkRegister:
                 Intent intentRegister=new Intent(getApplicationContext(),Register.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSqlLite()
    {
        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail,"Invalid"))
        {
            return;
        }

        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail,textInputLayoutEmail,"Invalid"))
        {
            return;
        }

        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword,textInputLayoutPassword,"Invalid"))
        {
            return;
        }

        if(databaseHelper.CheckUser(textInputEditTextEmail.getText().toString().trim(),textInputEditTextPassword.getText().toString().trim()))
        {
            Intent accountIsIntent=new Intent(getApplicationContext(),UserActivity.class);
            accountIsIntent.putExtra("EMAIL",textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountIsIntent);
        }
        else
        {
            Snackbar.make(nestedScrollView," NO RECORD!",Snackbar.LENGTH_LONG).show();
        }
    }
private void emptyInputEditText()
{
    textInputEditTextEmail.setText(null);
    textInputEditTextPassword.setText(null);
}

}
