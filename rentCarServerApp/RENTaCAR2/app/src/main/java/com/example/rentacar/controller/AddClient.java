package com.example.rentacar.controller;

        import  android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.rentacar.Model.backend.BackEnd;
        import com.example.rentacar.Model.backend.FactoryBE;
        import com.example.rentacar.Model.backend.FactoryMySQL_DBManager;
        import com.example.rentacar.Model.entities.Branch;
        import com.example.rentacar.Model.entities.Car;
        import com.example.rentacar.Model.entities.Client;
        import com.example.rentacar.R;

        import java.util.concurrent.ExecutionException;
        import java.util.regex.Pattern;

public class AddClient extends AppCompatActivity implements View.OnClickListener
{
    private ImageView imageView8;
    private LinearLayout linearLayout8;
    private EditText AddClientLastName;
    private EditText AddClientFirstName;
    private EditText AddClientId;
    private EditText AddClientPhoneNumber;
    private EditText AddClientEmail;
    private EditText AddClientcredit;
    private TextView textView32;
    private Button AddClientBatton;
    private ImageButton home;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-25 21:57:25 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        imageView8 = (ImageView)findViewById( R.id.imageView8 );
        linearLayout8 = (LinearLayout)findViewById( R.id.linearLayout8 );
        AddClientLastName = (EditText)findViewById( R.id.AddClientLastName );
        AddClientFirstName = (EditText)findViewById( R.id.AddClientFirstName );
        AddClientId = (EditText)findViewById( R.id.AddClientId );
        AddClientPhoneNumber = (EditText)findViewById( R.id.AddClientPhoneNumber );
        AddClientEmail = (EditText)findViewById( R.id.AddClientEmail );
        AddClientcredit = (EditText)findViewById( R.id.AddClientcredit );
        textView32 = (TextView)findViewById( R.id.textView32 );
        AddClientBatton = (Button)findViewById( R.id.AddClientBatton );
        home = (ImageButton)findViewById( R.id.imageButton2 );

        AddClientBatton.setOnClickListener( this );
        home.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-04-25 21:57:25 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == AddClientBatton )
        {
            // Handle clicks for AddClientBatton
            AddClientBatton();
        } else if ( v == home ) {
            // Handle clicks for imageButton2
            home();
        }

    }

    /**
     * define the home button
     */
    private void home() {
        Intent intent = new Intent(AddClient.this, OptionsForUser.class);
        startActivity(intent);
    }

    /**
     * define the add Client button
     */
    private void AddClientBatton() {
        BackEnd DS= FactoryMySQL_DBManager.getBackEnd();
        String AddClientLastName1,AddClientFirstName1,AddClientPhoneNumber1,AddClientEmail1,AddClientId1,AddClientcredit1;
        AddClientLastName1=AddClientLastName.getText().toString();
        AddClientFirstName1=AddClientFirstName.getText().toString();
        AddClientPhoneNumber1=AddClientPhoneNumber.getText().toString();
        AddClientEmail1=AddClientEmail.getText().toString();
        AddClientId1=AddClientId.getText().toString();
        AddClientcredit1=AddClientcredit.getText().toString();

        if(!(AddClientLastName1.matches("")||AddClientFirstName1.matches("")||AddClientPhoneNumber1.matches("")||AddClientEmail1.matches("")||AddClientId1.matches("")||AddClientcredit1.matches("")))//if all editText are completed
        {

            Client c=new Client(AddClientLastName1,AddClientFirstName1,AddClientId1,AddClientPhoneNumber1,AddClientEmail1,AddClientcredit1);
            DS.addClient(c);
            Toast.makeText(AddClient.this, "Succeeded", Toast.LENGTH_SHORT).show();
            try {
                if(DS.checkIfClientExistInSystem(c))//checking if the branch exist in system
                    Toast.makeText(AddClient.this, "Client allready exist in system", Toast.LENGTH_SHORT).show();
                else
                {
                    AddClientLastName.getText().clear();
                    AddClientFirstName.getText().clear();
                    AddClientPhoneNumber.getText().clear();
                    AddClientEmail.getText().clear();
                    AddClientId.getText().clear();
                    AddClientcredit.getText().clear();
                    DS.addClient(c);
                    Toast.makeText(AddClient.this, "Succeeded", Toast.LENGTH_SHORT).show();

                }

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        findViews();
        AddClientEmail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (!EMAIL_ADDRESS_PATTERN.matcher(AddClientEmail.getText()).matches()) {//check if the email address is valid
                    AddClientEmail.setError("Invalid email address");
                    AddClientBatton.setEnabled(false);
                }
                else {
                    AddClientBatton.setEnabled(true);
                }
            }

        });


    }
}
