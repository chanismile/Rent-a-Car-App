package com.example.rentacar.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rentacar.Model.backend.BackEnd;
import com.example.rentacar.Model.backend.FactoryBE;
import com.example.rentacar.Model.backend.FactoryMySQL_DBManager;
import com.example.rentacar.Model.entities.Client;
import com.example.rentacar.Model.entities.User;
import com.example.rentacar.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClientList extends Activity {

    private ListView listview;
    private BackEnd backEnd;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-15 18:17:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews()
    {
        listview=(ListView)findViewById(R.id.usersList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        backEnd = FactoryMySQL_DBManager.getBackEnd();
       // backEnd=new MySQL_DBManager();
        findViews();
        showList();

    }
    /**
     * show the user list
     */
    private void showList() {
        try {
             List<Client> clientList1 =backEnd.ClientsList();
             final List<Client> clientList = clientList1;

            ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this,R.layout.client_design, clientList){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(ClientList.this, R.layout.client_design, null);//gets the desing layout
                    }
                    TextView clientId=(TextView) convertView.findViewById(R.id.clientId);//gets the items for the list
                    TextView clientLName=(TextView) convertView.findViewById(R.id.clientLName);//gets the items for the list
                    TextView clientFName=(TextView) convertView.findViewById(R.id.clientFName);//gets the items for the list
                    TextView clientPhoneNumber=(TextView) convertView.findViewById(R.id.clientPhoneNumber);//gets the items for the list
                    TextView clientMail=(TextView) convertView.findViewById(R.id.clientMail);//gets the items for the list
                    clientId.setText(( clientList.get(position).getId()).toString());//gets the items for the list
                    clientLName.setText(( clientList.get(position).getlName()).toString());//gets the items for the list
                    clientFName.setText(( clientList.get(position).getfName()).toString());//gets the items for the list
                    clientPhoneNumber.setText(( clientList.get(position).getPhoneNumber()).toString());//gets the items for the list
                    clientMail.setText(( clientList.get(position).getMail()).toString());
                    return convertView;

                }


            };
            listview.setAdapter(adapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
