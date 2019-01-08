package com.example.rentacar.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rentacar.R;

public class OptionsForUser extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayout3;
    private CheckBox clientChackBox;
    private CheckBox carChackBox;
    private CheckBox modelChackBox;
    private CheckBox branchChackBox;
    private Button add;
    private Button showList;
    private ImageView imageView4;
    private TextView textView4;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-24 18:27:22 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        linearLayout3 = (LinearLayout)findViewById( R.id.linearLayout3 );
        clientChackBox = (CheckBox)findViewById( R.id.clientChackBox );
        carChackBox = (CheckBox)findViewById( R.id.carChackBox );
        modelChackBox = (CheckBox)findViewById( R.id.modelChackBox );
        branchChackBox = (CheckBox)findViewById( R.id.branchChackBox );
        add = (Button)findViewById( R.id.addButton);
        showList = (Button)findViewById( R.id.showListButton);
        imageView4 = (ImageView)findViewById( R.id.imageView4 );
        textView4 = (TextView)findViewById( R.id.textView4 );

        add.setOnClickListener( this );
        showList.setOnClickListener( this );
        clientChackBox.setOnClickListener(this);
        carChackBox.setOnClickListener(this);
        modelChackBox.setOnClickListener(this);
        branchChackBox.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-04-24 18:27:22 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == add) {// Handle clicks for button
            if(clientChackBox.isChecked())
                addClient();
            if(carChackBox.isChecked())
                addCar();
            if(modelChackBox.isChecked())
                addModel();

            if(branchChackBox.isChecked())
                addBranch();
        }
         if ( v == showList )// Handle clicks for button2
         {
             if(clientChackBox.isChecked())
                 showListClient();
             if(carChackBox.isChecked())
                 showListCar();
             if(modelChackBox.isChecked())
                 showlistModel();
             if(branchChackBox.isChecked())
                 showListBranch();

        }
        if(v==branchChackBox)
            setCheckBoxs(branchChackBox);
        if(v==clientChackBox)
            setCheckBoxs(clientChackBox);
        if(v==carChackBox)
            setCheckBoxs(carChackBox);
        if(v==modelChackBox)
            setCheckBoxs(modelChackBox);

    }

    private void showListClient()
    {

        Intent intent=new Intent(OptionsForUser.this,ClientList.class);
        startActivity(intent);

    }
    private void showListCar()
    {
        Intent intent=new Intent(OptionsForUser.this,CarsList.class);
        startActivity(intent);
    }

    private void showlistModel()
    {
        Intent intent=new Intent(OptionsForUser.this,ModelsList.class);
        startActivity(intent);
    }

    private void showListBranch()
    {
        Intent intent=new Intent(OptionsForUser.this,BranchesList.class);
        startActivity(intent);
    }


    private void addBranch()
    {
        Intent intent=new Intent(OptionsForUser.this,AddBranch.class);
        startActivity(intent);
    }

    private void addModel()
    {
        Intent intent=new Intent(OptionsForUser.this,AddModel.class);
        startActivity(intent);
    }

    private void addCar()
    {
        Intent intent=new Intent(OptionsForUser.this,AddCar.class);
        startActivity(intent);
    }

    private void addClient()
    {
        Intent intent=new Intent(OptionsForUser.this,AddClient.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_for_user);
        findViews();



    }

    private void setCheckBoxs(CheckBox c)
    {
        if(!clientChackBox.equals(c))
            clientChackBox.setChecked(false);
        if(!carChackBox.equals(c))
            carChackBox.setChecked(false);
        if(!modelChackBox.equals(c))
            modelChackBox.setChecked(false);
        if(!branchChackBox.equals(c))
            branchChackBox.setChecked(false);
    }


}
