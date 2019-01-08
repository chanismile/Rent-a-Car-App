package com.example.rentacar.controller;

import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.entities.Branch;
import com.example.rentacar.model.entities.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by This_user on 18/06/2018.
 */

public class CarListViewFilter extends Filter {
    private List<Car> list;
    public ArrayAdapter<Car> myAdapter;
    private BackEnd backEnd= FactoryMy_Sql.getBackEnd();

    public CarListViewFilter(ArrayAdapter<Car>  myAdapter, List<Car> objects) {
        list = objects;
        this.myAdapter=myAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        try {
            int flag=1;//init
            if ((constraint == null || constraint.length() == 0)&&(flag==1)) {
                results.values = backEnd.CarsList();
                results.count = backEnd.CarsList().size();
            }

            else {//if the list is not empty
                // We perform filtering operation
                List<Car> tempList = new ArrayList<Car>();
                flag=0;
                for (Car p : backEnd.CarsList()) {
                    if (p.getModel().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
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
            for(Car item : (List<Car>) results.values)
                list.add(item);

            myAdapter.notifyDataSetChanged();
        }

    }
}
