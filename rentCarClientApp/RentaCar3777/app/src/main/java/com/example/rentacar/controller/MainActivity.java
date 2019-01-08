package com.example.rentacar.controller;

import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rentacar.R;
import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.entities.Branch;
import com.example.rentacar.model.entities.Car;
import com.example.rentacar.model.entities.Client;
import com.example.rentacar.model.entities.Order;
import com.example.rentacar.service.MyIntentService;
import com.example.rentacar.service.MyPerfectReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    static SharedPreferences sharedpreferences;
    private LinearLayout outer;
    private LinearLayout ceneral;
    private EditText passwordLogIn;
    private EditText emailLogIn;
    private CheckBox rememberMe;
    private Button logIn;
    private Button newClient;
    private BackEnd backEnd=FactoryMy_Sql.getBackEnd();
    public static final String mypreference = "mypref";

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-05-21 19:19:46 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        outer = (LinearLayout)findViewById( R.id.outer );
        ceneral = (LinearLayout)findViewById( R.id.ceneral );
        passwordLogIn = (EditText)findViewById( R.id.passwordLogIn );
        rememberMe = (CheckBox)findViewById( R.id.rememberMe );
        logIn = (Button)findViewById( R.id.logIn );
        newClient = (Button)findViewById( R.id.newUser );
        emailLogIn=(EditText)findViewById( R.id.emailLogIn );
        logIn.setOnClickListener( this );
        newClient.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-05-21 19:19:46 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == logIn ) {
            logIn();
            // Handle clicks for logIn
        } else if ( v == newClient ) {
            newClient();
            // Handle clicks for newClient

        }
    }

    private void newClient()
    {
        Intent intent = new Intent(MainActivity.this, NewClient.class);
        startActivity(intent);


    }

    /***
     * what to do when pressing on log in button
     */
    private void logIn() {
        String clientPassword=passwordLogIn.getText().toString();
        String email=emailLogIn.getText().toString();
        if(!clientPassword.matches("")&&!email.matches("")) {
            try {
                if(backEnd.checkIfClientExistInSystemByPassword(clientPassword,email)) {
                    if (rememberMe.isChecked()) {//if client whants to save his details on phone
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("email", emailLogIn.getText().toString());//save email in sharedpreferences
                        editor.commit();
                        editor.putString("password", passwordLogIn.getText().toString());//save password in sharedpreferences
                        editor.commit();
                        for (Client client : backEnd.ClientsList())//saving in share prefances client's id for future's use
                            if (client.getClientEmail().equals(sharedpreferences.getString("email", "")))
                            {
                                editor.putString("id", client.getClientId());
                                editor.commit();

                            }

                    }
                    Intent intent = new Intent(MainActivity.this, MyMenu.class);
                    startActivity(intent);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            passwordLogIn.setError("Required field");
            emailLogIn.setError("Required field");
        }

    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
       Date d=new Date();
       d.setDate(22/5/2018);
        if(!sharedpreferences.contains("counter")) {//counter for saving the wish list
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt("counter", 0);
        }

        registerReceiver(
                new MyPerfectReceiver(),
                new IntentFilter("change"));


            if (sharedpreferences.contains("password")) {//chacking if client exit in sharedpreferences
                passwordLogIn.setText(sharedpreferences.getString("password",null));
                emailLogIn.setText(sharedpreferences.getString("email",null));
                Intent intent=new Intent(MainActivity.this,MyMenu.class);
                startActivity(intent);

            }

        BackEnd backEnd= FactoryMy_Sql.getBackEnd();
        startService(new Intent(this, MyIntentService.class));
    }
}
