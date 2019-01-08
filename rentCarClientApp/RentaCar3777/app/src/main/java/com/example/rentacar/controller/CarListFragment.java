package com.example.rentacar.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.rentacar.R;
import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.entities.Branch;
import com.example.rentacar.model.entities.Car;

import java.util.List;
import java.util.concurrent.ExecutionException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.rentacar.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.rentacar.R.id.AlltheCarList;

/***
 *
 * performing all the cars
 */
public class CarListFragment extends Fragment implements SearchView.OnQueryTextListener {
   BackEnd backEnd= FactoryMy_Sql.getBackEnd();
    private SearchView searchViewCars;
    private ListView theCarsList;
    ArrayAdapter<Car> adapter;

    private void findViews(View view) {
        searchViewCars = (SearchView)view.findViewById( R.id.search_viewAllCars );
        theCarsList = (ListView)view.findViewById( R.id.AlltheCarList );
        theCarsList.setClickable(true);
        searchViewCars.setOnQueryTextListener(this);
    }
    public CarListFragment() {
        // Required empty public constructor
    }

    /***
     * sets an adaptor and showing the list
     */
    private void showList()
    {
        try {
            final List<Car> carList =backEnd.CarsList();
            adapter = new ArrayAdapter<Car>(getActivity(),R.layout.cars_design, carList)
            {
                private CarListViewFilter listViewFilter;

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(getContext(), R.layout.cars_design, null);//gets the desing layout
                    }
                    TextView carModel=(TextView)convertView.findViewById(R.id.ModelCars);//gets the items for the list
                    final TextView carId=(TextView)convertView.findViewById(R.id.CarsIdList);//gets the items for the list
                    TextView kilometer=(TextView)convertView.findViewById(R.id.KilometerCars);//gets the items for the list
                    final ImageView like=(ImageView) convertView.findViewById(R.id.LikeACar);
                    TextView order=(TextView)convertView.findViewById(R.id.OrderTextView);
                    carModel.setText(carList.get(position).getModel());
                    carId.setText(carList.get(position).getCarId());
                    order.setVisibility(View.INVISIBLE);
                    kilometer.setText(carList.get(position).getKilometer());
                    like.setImageResource(R.drawable.car_3);
                    return convertView;

                }
                @Override
                public Filter getFilter(){
                    if(listViewFilter==null)
                        listViewFilter=new CarListViewFilter(this,carList);
                    return listViewFilter;
                }


            };

            theCarsList.setAdapter(adapter);

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
        View view=inflater.inflate(R.layout.fragment_car_list2, container, false);
        findViews(view);
        showList();
        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }
}
