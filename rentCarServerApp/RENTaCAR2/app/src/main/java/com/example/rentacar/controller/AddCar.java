package com.example.rentacar.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.rentacar.Model.entities.User;
import com.example.rentacar.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddCar extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView7;
    private TextView textView25;
    private LinearLayout linearLayout7;
    private TextView textView15;
    private EditText addCarBranchNumber;
    private EditText addCarModel;
    private EditText addCarKilometer;
    private EditText addCarId;
    private Button addCarButton;
    private ImageButton home;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-25 21:40:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews()
    {
        imageView7 = (ImageView)findViewById( R.id.imageView7 );
        textView25 = (TextView)findViewById( R.id.textView25 );
        linearLayout7 = (LinearLayout)findViewById( R.id.linearLayout7 );
        textView15 = (TextView)findViewById( R.id.textView15 );
        addCarBranchNumber = (EditText)findViewById( R.id.addCarBranchNumber );
        addCarModel = (EditText)findViewById( R.id.addCarModel );
        addCarKilometer = (EditText)findViewById( R.id.addCarKilometer );
        addCarId = (EditText)findViewById( R.id.addCarId );
        addCarButton = (Button)findViewById( R.id.addCarButton );
        home = (ImageButton)findViewById( R.id.imageButton );
        addCarButton.setOnClickListener( this );
        home.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-04-25 21:40:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == addCarButton ) {
            // Handle clicks for addCarButton
            addCarButton();
        } else if ( v == home ) {
            // Handle clicks for imageButton
            home();
        }
    }

    /**
     * define the add Car button
     */
    private void addCarButton()
    {

        BackEnd be= FactoryMySQL_DBManager.getBackEnd();
        String addCarBranchNumber1,addCarModel1,addCarKilometer1,addCarId1;
        addCarBranchNumber1=addCarBranchNumber.getText().toString();
        addCarModel1=addCarModel.getText().toString();
        addCarKilometer1=addCarKilometer.getText().toString();
        addCarId1=addCarId.getText().toString();
        if(!(addCarBranchNumber1.matches("")||addCarModel1.matches("")||addCarKilometer1.matches("")||addCarId1.matches("")||addCarModel1.matches("")))//if all editText are completed
        {

            Car c=new Car(addCarBranchNumber1,addCarModel1,addCarKilometer1,addCarId1);
            try {
                if(be.checkIfCarExistInSystem(c))//checking if the Car exist in system
                    Toast.makeText(AddCar.this, "Car allready exist in system", Toast.LENGTH_SHORT).show();
                else {
                    be.addCar(c);
                    addCarBranchNumber.getText().clear();
                    addCarModel.getText().clear();
                    addCarKilometer.getText().clear();
                    addCarId.getText().clear();
                    Toast.makeText(AddCar.this, "Succeeded", Toast.LENGTH_SHORT).show();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }



    }

    /**
     * define the home button
     */
    private void home() {
        Intent intent = new Intent(AddCar.this, OptionsForUser.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        findViews();
    }
}
