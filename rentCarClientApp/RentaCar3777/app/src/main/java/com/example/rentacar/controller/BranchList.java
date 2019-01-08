package com.example.rentacar.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Filter;
import com.example.rentacar.R;
import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.backend.My_Sql;
import com.example.rentacar.model.backend.Static;
import com.example.rentacar.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class BranchList extends Fragment implements SearchView.OnQueryTextListener{
    private SearchView searchViewCars;
    private ListView theBrabchesList;
    ArrayAdapter<Branch> adapter;
    BackEnd backEnd= FactoryMy_Sql.getBackEnd();

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-06-03 19:20:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        searchViewCars = (SearchView)view.findViewById( R.id.search_viewCars );
        theBrabchesList = (ListView)view.findViewById( R.id.theCarList );
        theBrabchesList.setClickable(true);
        searchViewCars.setOnQueryTextListener(this);
    }
    public BranchList() {
        // Required empty public constructor
    }
    private void showList()//setting an adaptar and shows the branch's list
    {
        try {
            final List<Branch> branchesList =backEnd.branchesList();
            adapter = new ArrayAdapter<Branch>(getActivity(),R.layout.branch_design, branchesList){
                private ListViewFilter listViewFilter;

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(getContext(), R.layout.branch_design, null);//gets the desing layout
                    }
                    TextView BranchNoTextView = (TextView) convertView.findViewById(R.id.BranchNoTextView);//gets the items for the list
                    final TextView AddressTextView=(TextView)convertView.findViewById(R.id.AddressTextView);//gets the items for the list
                    TextView parkingSpace=(TextView)convertView.findViewById(R.id.parkingSpaceTextView);//gets the items for the list
                    BranchNoTextView.setText(((Integer) branchesList.get(position).getBranchNo()).toString());
                    AddressTextView.setText(branchesList.get(position).getAddress());
                    parkingSpace.setText(branchesList.get(position).getNumOfParking());
                    AddressTextView.setOnClickListener(new View.OnClickListener() {//open the address's location in google maps
                        @Override
                        public void onClick(View v) {
                            Static.url=AddressTextView.getText().toString();
                            Intent intent = new Intent(getContext(), WebActivityGoogleMap.class);
                            startActivity(intent);
                        }
                    });
                    return convertView;

                }
                @Override
                public Filter getFilter(){//setting the filter of thr list
                    if(listViewFilter==null)
                        listViewFilter=new ListViewFilter(this,branchesList);
                    return listViewFilter;
                }

            };

            theBrabchesList.setAdapter(adapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_branch_list, container, false);
        findViews(view);
        showList();
        theBrabchesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {//what to do when a branch selcted
            Fragment myfragment;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myfragment=new CarList();//opening the suitable car list
                Bundle bundle=new Bundle();
                Branch b= (Branch) parent.getItemAtPosition(position);
                bundle.putInt("branch num",b.getBranchNo());//setting details to help openning the avalibale cars list that are in the selected branch
                myfragment.setArguments(bundle);
                if (myfragment != null) {//opening the fragment car list
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, myfragment,"findThisFragment")
                            .addToBackStack(null)
                            .commit();

                }
            }
        });
        return view;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        adapter.getFilter().filter(newText);
        return false;
    }
}
