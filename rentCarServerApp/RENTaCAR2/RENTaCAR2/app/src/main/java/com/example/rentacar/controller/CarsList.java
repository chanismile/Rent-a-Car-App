package com.example.rentacar.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rentacar.Model.backend.BackEnd;
import com.example.rentacar.Model.backend.DB_Manager;
import com.example.rentacar.Model.backend.FactoryBE;
import com.example.rentacar.Model.backend.FactoryMySQL_DBManager;
import com.example.rentacar.Model.entities.Car;
import com.example.rentacar.Model.entities.Model;
import com.example.rentacar.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CarsList extends AppCompatActivity {

    private ListView list;
    private BackEnd backEnd= FactoryMySQL_DBManager.getBackEnd();
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-15 18:17:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews()
    {
        list=(ListView)findViewById(R.id.carList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_list);
        findViews();
        showList();

    }
    /**
     * show the car list
     */
    private void showList() {
        try {
            final List<Car> carsList =backEnd.CarsList();


            ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(this,R.layout.car_design, carsList){
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    if (convertView == null) {
                        convertView = View.inflate(CarsList.this, R.layout.car_design, null);//gets the desing layout
                    }
                    TextView carId = (TextView) convertView.findViewById(R.id.carId);//gets the items for the list
                    TextView carModel=(TextView)convertView.findViewById(R.id.carModel);//gets the items for the list
                    TextView kilometer=(TextView)convertView.findViewById(R.id.kilometerCar);//gets the items for the list
                    TextView branchNo=(TextView)convertView.findViewById(R.id.branchNoCar);//gets the items for the list
                    carId.setText(( carsList.get(position).getCarId()).toString());//gets the items for the list
                    carModel.setText((carsList.get(position).getModel()).toString());//gets the items for the list
                    kilometer.setText(( carsList.get(position).getKilometer()).toString());//gets the items for the list
                    branchNo.setText(( carsList.get(position).getBranchNo()).toString());

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