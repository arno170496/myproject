package com.example.admin.project.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.admin.project.R;
import com.example.admin.project.helper.InputValidation;
import com.example.admin.project.model.User;
import com.example.admin.project.sql.DatabaseHelper;


public class Register extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity=Register.this;

    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutName;
    private  TextInputLayout textInputLayoutPassword;
    private  TextInputLayout textInputLayoutConfirmPassword;


    private  TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private  TextInputEditText textInputEditTextPassword;
    private  TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonLogin;

   private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private User user;

    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //getSupportActionBar().hide();


        initViews();
        initListeners();
        initObjects();
    }

    private void initViews()
    {
        nestedScrollView =(NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail=(TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutPassword=(TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword=(TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);


        textInputEditTextEmail=(TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextName=(TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextPassword=(TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword=(TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);


        appCompatButtonLogin=(AppCompatButton) findViewById(R.id.submitbutton);
       textViewLinkRegister=(AppCompatTextView) findViewById(R.id.LinkRegister);

    }

    private void initListeners()
    {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initObjects()
    {
        inputValidation=new InputValidation(activity);
        databaseHelper =new DatabaseHelper(activity);
        user=new User();
    }

    @Override
     public void onClick(View v)
    {
      switch (v.getId())
      {
          case R.id.submitbutton:
               postDataToSql();
              break;
          case R.id.LinkRegister:
             // Intent intentRegister=new Intent(getApplicationContext(),HomeActivity.class);
              //startActivity(intentRegister);
              finish();
              break;
      }
    }

    private void postDataToSql()
    {

        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail,"INVALID"))
        {
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName,"INVALID"))
        {
            return;
        }
        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail,"INVALID"))
        {
            return;
        }

        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword,"INVALID"))
        {
            return;
        }
        // just making a match ,later change it
        if(!inputValidation.isInputEditTextMatches(textInputEditTextPassword,textInputEditTextConfirmPassword, textInputLayoutConfirmPassword,"wrong match"))
        {
            return;
        }

        if(!databaseHelper.CheckUser(textInputEditTextEmail.getText().toString().trim()))
        {
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            databaseHelper.addUser(user);
            Snackbar.make(nestedScrollView,"ACCOUNT LOGGED IN !",Snackbar.LENGTH_LONG).show();
            emptyInputText();
        }
        else
        {
            Snackbar.make(nestedScrollView,"USER ALREADY EXITS !",Snackbar.LENGTH_LONG).show();
        }

    }

private void emptyInputText()
{
    textInputEditTextEmail.setText(null);
    textInputEditTextName.setText(null);
    textInputEditTextPassword.setText(null);
    textInputEditTextConfirmPassword.setText(null);
}
}
