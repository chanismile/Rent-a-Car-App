package com.example.rentacar.controller;

import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by ezra on 12/1/2015.
 */
public class ListViewFilter extends Filter {
    private List<Branch> list;
    public ArrayAdapter<Branch>  myAdapter;
    private BackEnd backEnd= FactoryMy_Sql.getBackEnd();

    public ListViewFilter(ArrayAdapter<Branch>  myAdapter, List<Branch> objects) {
        list = objects;
        this.myAdapter=myAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        try {
        int flag=1;
        if ((constraint == null || constraint.length() == 0)&&(flag==1)) {

            results.values = backEnd.branchesList();
            results.count = backEnd.branchesList().size();
        } else {
            // We perform filtering operation
            List<Branch> tempList = new ArrayList<Branch>();
            flag=0;
            for (Branch p : backEnd.branchesList()) {
                if (p.getAddress().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                    flag=1;
                    tempList.add(p);
                }

                }
                if(flag==0){
                    tempList.clear();
                    flag=1;
                }


            results.values = tempList;
            results.count = tempList.size();

        }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return results;

    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results.count == 0) {
            list.removeAll(list);
            myAdapter.notifyDataSetChanged();
        }
        else {

            list.removeAll(list);
            for(Branch item : (List<Branch>) results.values)
             list.add(item);



            myAdapter.notifyDataSetChanged();
        }


    }
}
