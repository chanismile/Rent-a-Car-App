package com.example.rentacar.controller;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.text.Editable;
import android.text.TextWatcher;


import com.example.rentacar.Model.backend.BackEnd;
import com.example.rentacar.Model.backend.FactoryBE;
import com.example.rentacar.Model.backend.FactoryMySQL_DBManager;
import com.example.rentacar.Model.entities.User;
import com.example.rentacar.R;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUser extends AppCompatActivity implements View.OnClickListener
{
    private ImageView imageView2;
    private Button newUserButton;
    private LinearLayout linearLayout2;
    private TextView textView;
    private LinearLayout details;
    private EditText newUserId;
    private EditText newUserName;
    private EditText newUserMail;
    private EditText newUserPassword;
    private ImageView emailWarning;
    private ImageView userNameWarrning;
    private CheckBox checkBox;
    private EditText newUserhint;
    BackEnd be = FactoryMySQL_DBManager.getBackEnd();

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-21 22:24:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        userNameWarrning=(ImageView) findViewById(R.id.userNameWarrning);
      //  imageView2 = (ImageView)findViewById( R.id.imageView2 );
        newUserButton = (Button)findViewById( R.id.newUserButton );
        linearLayout2 = (LinearLayout)findViewById( R.id.linearLayout2 );
        textView = (TextView)findViewById( R.id.textView );
        details = (LinearLayout)findViewById( R.id.details );
        newUserId = (EditText)findViewById( R.id.newUserId );
        newUserName = (EditText)findViewById( R.id.newUserName );
        newUserMail = (EditText)findViewById( R.id.newUserMail );
        newUserPassword = (EditText)findViewById( R.id.newUserPassword );
        emailWarning=(ImageView)findViewById( R.id.emailWarning );
        checkBox=(CheckBox)findViewById( R.id.checkBox );
        newUserhint = (EditText)findViewById( R.id.hint );
        newUserButton.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-04-21 22:24:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == newUserButton ) {
            newUserButton();
        }
    }
    /**
     * adding user to system
     */
    private void newUserButton()
    {
       // try {
            String id,name,email,password,hint;
            id=newUserId.getText().toString();
            name=newUserName.getText().toString();
            email=newUserMail.getText().toString();
            password=newUserPassword.getText().toString();
            hint=newUserhint.getText().toString();
            User u=new User(newUserName.getText().toString(),newUserPassword.getText().toString(),newUserMail.getText().toString(),newUserId.getText().toString(),newUserhint.getText().toString());
           /* if(be.checkIfUserExistInSystem(u))//chacks if the user already exist in system
                Toast.makeText(NewUser.this, "User already exist in system :(", Toast.LENGTH_SHORT).show();
            else */if(!(id.matches("")||name.matches("")||email.matches("")||password.matches("")||hint.matches("")))//if all editText are completed
            {
                be.addUser(u);
                Toast.makeText(NewUser.this, "Succeeded", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(NewUser.this,OptionsForUser.class);
                startActivity(intent);
            }
            else
                Toast.makeText(NewUser.this, "Some details are incomplete:(", Toast.LENGTH_SHORT).show();


    /*    } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * define a valid email adress
     */
    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(

            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
        @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        findViews();
            //chack if the user name exist in system
        newUserName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int flag=0;
                    for (User u:be.UsersList()) {
                        if (u.getUserName().toString().equals(newUserName.getText().toString()))
                        {
                           // userNameWarrning.setVisibility(View.VISIBLE);
                            newUserName.setError("The user allready exist in system");
                            newUserButton.setEnabled(false);
                            flag=1;
                        }
                        else if(flag==0)
                        {
                            userNameWarrning.setVisibility(View.INVISIBLE);
                            newUserButton.setEnabled(true);
                        }
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    newUserPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    newUserPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
             //chack if the email adress is legal
        newUserMail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (!EMAIL_ADDRESS_PATTERN.matcher(newUserMail.getText()).matches()) {
                 /*   emailWarning.setVisibility(View.VISIBLE);*/
                    newUserMail.setError("Invalid email address");
                    newUserButton.setEnabled(false);
                }
                else {
                    emailWarning.setVisibility(View.INVISIBLE);
                    newUserButton.setEnabled(true);
                }
            }

        });



    }
}
