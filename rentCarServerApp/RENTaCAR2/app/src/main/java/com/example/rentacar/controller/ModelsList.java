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
import com.example.rentacar.Model.entities.Gearbox;
import com.example.rentacar.Model.entities.Model;
import com.example.rentacar.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelsList extends Activity
{

    private ListView list;
    private BackEnd backend;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-04-15 18:17:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews()
    {
        list=(ListView)findViewById(R.id.modelsList);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_models_list);
        backend= FactoryMySQL_DBManager.getBackEnd();
        findViews();
        showList();
    }
    /**
     * show the models list
     */
    private void showList() {
        try {
            final List<Model> modelslList = backend.modelsList();
            ArrayAdapter<Model> adapter = new ArrayAdapter<Model>(this, R.layout.model_design, modelslList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(ModelsList.this, R.layout.model_design, null);//gets the desing layout
                    }
                    TextView modelCode = (TextView) convertView.findViewById(R.id.modelCode);//gets the items for the list
                    TextView factoryName=(TextView)convertView.findViewById(R.id.factoryName);//gets the items for the list
                    TextView modelName=(TextView)convertView.findViewById(R.id.modelName);//gets the items for the list
                    TextView engineCapacity=(TextView)convertView.findViewById(R.id.engineCapacity);//gets the items for the list
                    TextView gearbox=(TextView)convertView.findViewById(R.id.gearbox);//gets the items for the list
                    TextView seats=(TextView)convertView.findViewById(R.id.seats);//gets the items for the list
                    modelCode.setText(( modelslList.get(position).getModelCode()).toString());//gets the items for the list
                    factoryName.setText(modelslList.get(position).getFactoryName());//gets the items for the list
                    modelName.setText(modelslList.get(position).getModelName());//gets the items for the list
                    engineCapacity.setText(( modelslList.get(position).getEngineCapacity()).toString());
                    gearbox.setText(((Gearbox) modelslList.get(position).getGearbox()).toString());//gets the items for the list
                    seats.setText(( modelslList.get(position).getSeats()).toString());

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
