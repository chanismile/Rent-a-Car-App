package com.example.rentacar.controller;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentacar.Model.backend.BackEnd;
import com.example.rentacar.Model.backend.Convert;
import com.example.rentacar.Model.backend.FactoryBE;
import com.example.rentacar.Model.backend.FactoryMySQL_DBManager;
import com.example.rentacar.Model.backend.MySQL_DBManager;
import com.example.rentacar.Model.datasource.Ds_lists;
import com.example.rentacar.Model.entities.Branch;
import com.example.rentacar.Model.entities.Car;
import com.example.rentacar.Model.entities.Client;
import com.example.rentacar.Model.entities.Gearbox;
import com.example.rentacar.Model.entities.Model;
import com.example.rentacar.Model.entities.User;
import com.example.rentacar.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userName;
    private EditText userPassword;
    private EditText userId;
    private EditText idUser_editText;
    private CardView idCard;
    private TextView forget;
    private LinearLayout out_laout;
    private LinearLayout id_layout;
    private LinearLayout idUser_Layout;
    private Button logIn;
    private Button createNewACount;
    //private Button forget_password_button;
    int flag = 0;
    BackEnd be = FactoryMySQL_DBManager.getBackEnd();
    private BackEnd backEnd= FactoryMySQL_DBManager.getBackEnd();

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-15 18:17:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findVieWs() {
        logIn = (Button) findViewById(R.id.logIn);
        out_laout = (LinearLayout) findViewById(R.id.outer);
        id_layout = (LinearLayout) findViewById(R.id.idLayout);
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        idUser_editText = (EditText) findViewById(R.id.userPassword);
        forget = (TextView) findViewById(R.id.forget_password);
        idCard = (CardView) findViewById(R.id.cardViewid);
        idUser_Layout = (LinearLayout) findViewById(R.id.idUserLayout);
        idUser_editText = (EditText) findViewById(R.id.idUser);
        userId = (EditText) findViewById(R.id.idUser);
        createNewACount = (Button) findViewById(R.id.newUser);
        //forget_password_button=(Button)findViewById(R.id.forget_password_button);
        // forget_password_button.setOnClickListener(this);
        createNewACount.setOnClickListener(this);
        logIn.setOnClickListener(this);
        out_laout.setOnClickListener(this);
        id_layout.setOnClickListener(this);
        userName.setOnClickListener(this);
        userPassword.setOnClickListener(this);
        idUser_editText.setOnClickListener(this);
        logIn.setOnClickListener(this);
        forget.setOnClickListener(this);
        idCard.setOnClickListener(this);
        idUser_Layout.setOnClickListener(this);
        idUser_editText.setOnClickListener(this);
        userId.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // init();
        findVieWs();
        out_laout.setVisibility(View.VISIBLE);

    }

    /**
     * initialization database
     */
   /* private void init() {
        try {

            be.addModel(new Model("01", "Mazda", "hello", "330", Gearbox.AUTOMATON, "5"));
            be.addCar(new Car("01", "Mazda", "300000", "1112587"));
            be.addClient(new Client("Sari", "Sari", "53597332", "0528964411", "you@gmail.com", "458527015"));
            User u = new User("wiseberg", "Sw208555", "208sari@gmail.com", "208555748", "My Collage");
            be.addUser(u);
            be.addUser(new User("cglick", "Ab208555", "208sari@gmail.com", "318342102", "sari"));
            be.addUser(new User("1", "1", "1", "1   ", "1"));
            be.addBrunch(new Branch("bnbn", "2"));
            be.addBrunch(new Branch("bnbn", "2"));
            be.addBrunch(new Branch("bnbn", "2"));
            be.addBrunch(new Branch("bnbn", "2"));

            be.checkIfUserExistInSystem(u);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * if the user click on log in Button
     */
    public void logIn() throws ExecutionException, InterruptedException
    {
        String user = userName.getText().toString();
        String password = userPassword.getText().toString();
        final List<User> getUser = be.UsersList();
        be.addBrunch(new Branch("n","j"));
        be.addUser(new User("k","k","j","j","j"));
        be.addUser(new User("k","k","j","j","jlllllllll"));
        be.addCar(new Car("dddd","d","d","d"));
/*be.addUser(new User("jk","jj","jj","kk","jj"));
be.addBrunch(new Branch("hjhj","ji"));
       for(int i=0;i<getUser.size();i++)
        {
            if ((getUser.get(i).getUserName().equals(user)) && (getUser.get(i).getPassword().equals(password)))
            {*/
                Intent intent = new Intent(MainActivity.this, OptionsForUser.class);
                startActivity(intent);
                flag = 1;
           /* }

        }
        /*
        for (User u : Ds_lists.getUser())
        {
            if((user.equals(u.getUserName()))&&(password.equals(u.getPassword()))) {
                Intent intent=new Intent(MainActivity.this,OptionsForUser.class);
                startActivity(intent);
                flag = 1;
            }

        }*/

       /* if (flag == 0) {
            Toast.makeText(MainActivity.this, "Sorry ,incorrect name or password :(", Toast.LENGTH_SHORT).show();
        }*/
    }


    /**
     * if the user click on forget userName or password
     */
    public void forget() {

        final EditText taskEditText = new EditText(this);
        final TextView pass = new TextView(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Get a hint")
                .setMessage("Enter your id:")
                .setView(taskEditText)
                //.setView(pass)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        try {
                            for (User u : be.UsersList()) {
                                if (u.getId().toString().equals(taskEditText.getText().toString())) {
                                    Toast.makeText(MainActivity.this,
                                            u.getHint().toString(), Toast.LENGTH_SHORT).show();


                                }
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                })


                .create();
        dialog.show();
    }

    /**
     * if the user click on createNewACount
     */
    public void createNewACount() {
        Intent intent = new Intent(MainActivity.this, NewUser.class);
        startActivity(intent);
    }

    /**
     * implements View.OnClickListener
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        try {
            if (v == logIn)

                logIn();

            if (v == forget)
                forget();
            if (v == createNewACount)
                createNewACount();


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
