package com.example.rentacar.controller;

/**
 * Created by שרה ויסברגר on 06/06/2018.
 */
import android.content.SharedPreferences;

import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.entities.Branch;
import com.example.rentacar.model.entities.Car;
import com.example.rentacar.model.entities.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.rentacar.controller.MainActivity.sharedpreferences;

/***
 * pump data into the expandableList
 */
public class ExpandableListDataPump
{

    /***
     *
     * @param bn is the branch no. that we want to perform its the availbale cars
     * @return the car list with details
     */
    public static HashMap<Car, List<String>> getData(String bn) {
     List<String> itemsList=new ArrayList<String>();
        List<Car> cars=new ArrayList<Car>();
        HashMap<Car, List<String>> expandableListDetail = new HashMap<Car,List<String>>();
        BackEnd backEnd = FactoryMy_Sql.getBackEnd();

        try {
            if(bn!=null)//performing the availible cars of the branch
            {
                cars = backEnd.branchAvailiableCarsList(String.valueOf(bn));
            }
            else//performing the wish cars
            {
                int carId;
                for (int i=0;i<sharedpreferences.getInt("counter",0 );i++)
                {
                    carId=sharedpreferences.getInt(String.valueOf(i),0);
                    for(Car c:backEnd.CarsList()) {
                    if(c.getCarId().equals(String.valueOf(carId)))
                        cars.add(c);
                    }
                }
            }
            for (Car c : cars) {
                for (Branch b:backEnd.branchesList()) {//getting the car's branch's details
                    if (c.getBranchNo().equals(String.valueOf(b.getBranchNo())))
                        itemsList.add(b.toString());
                }
                for (Model m : backEnd.modelsList())//getting the car's model's details
                    if (c.getModel().equals(m.getModelName()))
                        itemsList.add(m.toString());
                expandableListDetail.put(c,itemsList);
            }
            return expandableListDetail;

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return expandableListDetail;

    }
}
