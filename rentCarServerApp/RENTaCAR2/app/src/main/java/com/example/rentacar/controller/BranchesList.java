package com.example.rentacar.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rentacar.Model.backend.BackEnd;
import com.example.rentacar.Model.backend.FactoryBE;
import com.example.rentacar.Model.backend.FactoryMySQL_DBManager;
import com.example.rentacar.Model.entities.Branch;
import com.example.rentacar.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BranchesList extends Activity {

    private BackEnd backEnd;
    private RelativeLayout rootView;
    private ListView list;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-15 18:17:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews()
    {
        rootView = (RelativeLayout)findViewById( 0 );
        list=(ListView)findViewById(R.id.List);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches_list);
        backEnd= FactoryMySQL_DBManager.getBackEnd();
      //  backEnd.addBrunch(new Branch("1","1",1));
        findViews();
        showList();
    }

    /**
     * show the brunches list
     */
    private void showList()
    {
        try {
            final List<Branch> branchesList =backEnd.branchesList();
            ArrayAdapter<Branch> adapter = new ArrayAdapter<Branch>(this,R.layout.branch_design, branchesList){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(BranchesList.this, R.layout.branch_design, null);//gets the desing layout
                    }
                    TextView BranchNoTextView = (TextView) convertView.findViewById(R.id.BranchNoTextView);//gets the items for the list
                    TextView AddressTextView=(TextView)convertView.findViewById(R.id.AddressTextView);//gets the items for the list
                    BranchNoTextView.setText(((Integer) branchesList.get(position).getBranchNo()).toString());
                    AddressTextView.setText(branchesList.get(position).getAddress());
                    return convertView;

                }


            };
            list.setAdapter(adapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
