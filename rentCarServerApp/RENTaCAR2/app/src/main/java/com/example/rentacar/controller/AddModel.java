package com.example.rentacar.controller;

import  android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentacar.Model.backend.BackEnd;
import com.example.rentacar.Model.backend.FactoryBE;
import com.example.rentacar.Model.backend.FactoryMySQL_DBManager;
import com.example.rentacar.Model.entities.Branch;
import com.example.rentacar.Model.entities.Car;
import com.example.rentacar.Model.entities.Client;
import com.example.rentacar.Model.entities.Gearbox;
import com.example.rentacar.Model.entities.Model;
import com.example.rentacar.R;

import java.util.concurrent.ExecutionException;

public class AddModel extends AppCompatActivity implements  View.OnClickListener{
    private LinearLayout linearLayout9;
    private TextView textView21;
    private EditText AddModelCode;
    private TextView textView20;
    private EditText AddModelFactoryName;
    private TextView textView19;
    private EditText AddModelName;
    private TextView textView18;
    private EditText AddModelEngineCapacity;
    private TextView textView17;
    private Spinner addModelSpinner;
    private TextView textView16;
    private EditText AddModelSeats;
    private ImageView imageView5;
    private TextView textView22;
    private Button AddModleButton;
    private ImageButton home;
    Transition t;
    private String spinnerArray[];

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-25 22:58:02 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        linearLayout9 = (LinearLayout)findViewById( R.id.linearLayout9 );
        textView21 = (TextView)findViewById( R.id.textView21 );
        AddModelCode = (EditText)findViewById( R.id.AddModelCode );
        textView20 = (TextView)findViewById( R.id.textView20 );
        AddModelFactoryName = (EditText)findViewById( R.id.AddModelFactoryName );
        textView19 = (TextView)findViewById( R.id.textView19 );
        AddModelName = (EditText)findViewById( R.id.AddModelName );
        textView18 = (TextView)findViewById( R.id.textView18 );
        AddModelEngineCapacity = (EditText)findViewById( R.id.AddModelEngineCapacity );
        textView17 = (TextView)findViewById( R.id.textView17 );
        addModelSpinner = (Spinner)findViewById( R.id.addModelSpinner );
        textView16 = (TextView)findViewById( R.id.textView16 );
        AddModelSeats = (EditText)findViewById( R.id.AddModelSeats );
        imageView5 = (ImageView)findViewById( R.id.imageView5 );
        textView22 = (TextView)findViewById( R.id.textView22 );
        AddModleButton = (Button)findViewById( R.id.AddModleButton );
        home = (ImageButton)findViewById( R.id.addModleIB );

        AddModleButton.setOnClickListener( this );
        home.setOnClickListener( this );
    }
    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-04-25 22:58:02 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == AddModleButton ) {
            // Handle clicks for AddModleButton
            AddModleButton();
        } else if ( v == home ) {
            // Handle clicks for addModleIB
            home();
        }
    }
    /**
     * define the home button
     */
    private void home() {
        Intent intent = new Intent(AddModel.this, OptionsForUser.class);
        startActivity(intent);
    }
    /**
     * define the add Client button
     */
    private void AddModleButton() {
        BackEnd DB= FactoryMySQL_DBManager.getBackEnd();
        String AddModelCode1,AddModelFactoryName1,AddModelName1,AddModelEngineCapacity1,AddModelSeats1,addModelSpinner1;
        AddModelCode1=AddModelCode.getText().toString();
        AddModelFactoryName1=AddModelFactoryName.getText().toString();
        AddModelName1=AddModelName.getText().toString();
        AddModelEngineCapacity1=AddModelEngineCapacity.getText().toString();
        AddModelSeats1=AddModelSeats.getText().toString();
        addModelSpinner1=addModelSpinner.getSelectedItem().toString();

        if(!(AddModelCode1.matches("")||AddModelFactoryName1.matches("")||AddModelName1.matches("")||AddModelEngineCapacity1.matches("")||AddModelSeats1.matches("")||addModelSpinner1.matches("")))//if all editText are completed
        {
            Model m=new Model(AddModelCode1,AddModelFactoryName1,AddModelName1,AddModelEngineCapacity1, Gearbox.valueOf(addModelSpinner1),AddModelSeats1);
            try {
                if(DB.checkIfModelExistInSystem(m))//checking if the branch exist in system
                    Toast.makeText(AddModel.this, "Model allready exist in system", Toast.LENGTH_SHORT).show();
                else
                {
                    AddModelCode.getText().clear();
                    AddModelFactoryName.getText().clear();
                    AddModelName.getText().clear();
                    AddModelEngineCapacity.getText().clear();
                    AddModelSeats.getText().clear();
                    Toast.makeText(AddModel.this, "Succeeded", Toast.LENGTH_SHORT).show();
                    DB.addModel(m);

                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_model);
        findViews();
        t=((Transition)addModelSpinner.getSelectedItem());//definitions for the spinner
        spinnerArray=new String[2];//definitions for the spinner
        spinnerArray[0]="AUTOMATON";//definitions for the spinner
        spinnerArray[1]="MANUAL";//definitions for the spinner
        final Spinner s=(Spinner)findViewById(R.id.addModelSpinner);//definitions for the spinner
        ArrayAdapter adap=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,spinnerArray);//definitions for the spinner
        s.setAdapter(adap);//definitions for the spinner

    }
}
