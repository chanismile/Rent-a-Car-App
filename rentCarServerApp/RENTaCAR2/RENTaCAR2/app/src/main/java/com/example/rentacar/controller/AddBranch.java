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
import com.example.rentacar.R;

import java.util.concurrent.ExecutionException;

public class AddBranch extends AppCompatActivity implements View.OnClickListener
{
    private LinearLayout linearLayout6;
    private TextView textView6;
    private EditText AddCarAdress;
    private TextView textView7;
    private EditText AddCarNumOfParking;
    private TextView textView13;
    private Button addBranchButton;
    private ImageView imageView6;
    private ImageButton home;
    private static int COUNTER=1;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-25 20:48:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews()
    {
        linearLayout6 = (LinearLayout)findViewById( R.id.linearLayout6 );
        textView6 = (TextView)findViewById( R.id.textView6 );
        AddCarAdress = (EditText)findViewById( R.id.AddCarAdress );
        textView7 = (TextView)findViewById( R.id.textView7 );
        AddCarNumOfParking = (EditText)findViewById( R.id.AddCarNumOfParking );
        textView13 = (TextView)findViewById( R.id.textView13 );
        addBranchButton = (Button)findViewById( R.id.addBranchButton );
        imageView6 = (ImageView)findViewById( R.id.imageView6 );
        home=(ImageButton)findViewById( R.id.addBI );
        home.setOnClickListener(this);
        addBranchButton.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-04-25 20:48:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v)
    {
        if ( v == addBranchButton )
        {
            addBranchButton();
        }
        if(v==home)
        {
            home();
        }
    }

    /**
     * define the home button
     */
    private void home() {
        Intent intent = new Intent(AddBranch.this, OptionsForUser.class);
        startActivity(intent);
    }

    /**
     * define the add Branch button
     */
    private void addBranchButton()
    {
        BackEnd DB= FactoryMySQL_DBManager.getBackEnd();
        String adrress,NumOfParking;
        adrress=AddCarAdress.getText().toString();
        NumOfParking=AddCarNumOfParking.getText().toString();
    if(!(adrress.matches("")||NumOfParking.matches("")))//if all editText are completed
    {
        Branch b=new Branch(adrress,NumOfParking);
       try {
            if(DB.checkIfBranchExistInSystem(b))
            {//checking if the branch exist in system
                Toast.makeText(AddBranch.this, "Branch allready exist in system", Toast.LENGTH_SHORT).show();
            }
            else {
                DB.addBrunch(b);
                AddCarAdress.getText().clear();
                AddCarNumOfParking.getText().clear();

                Toast.makeText(AddBranch.this, "Succeeded", Toast.LENGTH_SHORT).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DB.addBrunch(b);


    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);
        findViews();
    }
}
