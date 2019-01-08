package com.example.rentacar.controller;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.rentacar.R;
import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.entities.Car;
import com.example.rentacar.model.entities.Gearbox;
import com.example.rentacar.model.entities.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/***
 * the fragment performs the wish list
 */
public class LikesCarList extends Fragment implements
        SearchView.OnQueryTextListener
{
    MyExpandableListAdapter myExpandableListAdapter;
    List<Car> expandableListTitle;
    HashMap<Car, List<String>> expandableListDetail;
    private ExpandableListView list;
    private SearchView searchView;

    public LikesCarList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_likes_car_list, container, false);
        list=(ExpandableListView)view.findViewById(R.id.theCarList2);
        expandableListDetail = ExpandableListDataPump.getData(null);
        expandableListTitle = new ArrayList<Car>(expandableListDetail.keySet());
        myExpandableListAdapter = new MyExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail,getActivity());
        list.setAdapter(myExpandableListAdapter);
        return view;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        return false;
    }
}
