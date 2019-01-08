package com.example.rentacar.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentacar.R;
import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.entities.Client;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class NewClient extends AppCompatActivity implements View.OnClickListener{

    private Button NewClientAddMeButton;
    private LinearLayout linearLayout3;
    private TextView textView22;
    private EditText newClientFirstName;
    private TextView textView21;
    private EditText newClientLastName;
    private TextView textView20;
    private EditText NewClientId;
    private TextView textView19;
    private EditText newClientPhoneNumber;
    private TextView textView17;
    private EditText newClientEmail;
    private TextView textView15;
    private EditText newClientCreditCard;
    private TextView textView14;
    private EditText NewClientPassword;
    private TextView textView6;
    BackEnd backEnd= FactoryMy_Sql.getBackEnd();

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-06-12 18:28:44 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        NewClientAddMeButton = (Button)findViewById( R.id.NewClientAddMeButton );
        linearLayout3 = (LinearLayout)findViewById( R.id.linearLayout3 );
        textView22 = (TextView)findViewById( R.id.textView22 );
        newClientFirstName = (EditText)findViewById( R.id.newClientFirstName );
        textView21 = (TextView)findViewById( R.id.textView21 );
        newClientLastName = (EditText)findViewById( R.id.newClientLastName );
        textView20 = (TextView)findViewById( R.id.textView20 );
        NewClientId = (EditText)findViewById( R.id.NewClientId );
        textView19 = (TextView)findViewById( R.id.textView19 );
        newClientPhoneNumber = (EditText)findViewById( R.id.newClientPhoneNumber );
        textView17 = (TextView)findViewById( R.id.textView17 );
        newClientEmail = (EditText)findViewById( R.id.newClientEmail );
        textView15 = (TextView)findViewById( R.id.textView15 );
        newClientCreditCard = (EditText)findViewById( R.id.newClientCreditCard );
        textView14 = (TextView)findViewById( R.id.textView14 );
        NewClientPassword = (EditText)findViewById( R.id.NewClientPassword );
        textView6 = (TextView)findViewById( R.id.textView6 );

        NewClientAddMeButton.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-06-12 18:28:44 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == NewClientAddMeButton ) {
            addMe();
            // Handle clicks for NewClientAddMeButton
        }
    }

    /***
     * what to do when click on addMe button
     */
    private void addMe() {
        String fName=newClientFirstName.getText().toString();
        String lName=newClientLastName.getText().toString();
        String id=NewClientId.getText().toString();
        String email=newClientEmail.getText().toString();
        String phone=newClientPhoneNumber.getText().toString();
        String password=NewClientPassword.getText().toString();
        String creditcard=newClientCreditCard.getText().toString();

        try {
            if(backEnd.checkIfClientExistInSystemById(NewClientId.getText().toString())||fName.matches("")||lName.matches("")||id.matches("")||email.matches("")||phone.matches("")||password.matches("")||creditcard.matches(""))
                            Toast.makeText(NewClient.this,"check the details",Toast.LENGTH_SHORT).show();
             else {
                backEnd.addClient(new Client(lName,fName,id,phone,email,creditcard,password));
                Intent intent = new Intent(NewClient.this, MyMenu.class);
                startActivity(intent);
                Toast.makeText(NewClient.this,"Welcome",Toast.LENGTH_SHORT).show();
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * define a correct email format
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        findViews();
        newClientEmail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (!EMAIL_ADDRESS_PATTERN.matcher(newClientEmail.getText()).matches()) {//chack if the email format is correct
                    newClientEmail.setError("Invalid email address");
                    NewClientAddMeButton.setEnabled(false);
                }
                else {
                    NewClientAddMeButton.setEnabled(true);
                }
            }

        });
        NewClientId.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String sa=NewClientId.getText().toString();
                    if(backEnd.checkIfClientExistInSystemById(sa)) {//chack if the client allready exist in system
                        NewClientId.setError("you allready exist in system");
                        NewClientAddMeButton.setEnabled(false);
                    }
                    else {
                        NewClientId.setError(null);
                        NewClientAddMeButton.setEnabled(true);

                    }


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });


    }
}
