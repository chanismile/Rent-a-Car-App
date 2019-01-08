package com.example.rentacar.controller;

/**
 * Created by שרה ויסברגר on 06/06/2018.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.rentacar.R;
import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.backend.Static;
import com.example.rentacar.model.entities.Car;
import com.example.rentacar.model.entities.Client;
import com.example.rentacar.model.entities.Gearbox;
import com.example.rentacar.model.entities.Model;
import com.example.rentacar.model.entities.Order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static com.example.rentacar.controller.MainActivity.mypreference;
import static com.example.rentacar.controller.MainActivity.sharedpreferences;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private Activity activity;
    int carIdInShardprefence;
    BackEnd bekend= FactoryMy_Sql.getBackEnd();
    public static List<Car> LikeCar=new ArrayList<Car>();
    private List<Car> expandableListTitle;
    private HashMap<Car, List<String>> expandableListDetail;

    /***
     * a constractor
     * @param context
     * @param expandableListTitle is the list of the parent
     * @param expandableListDetail is the hash map with the child list
     *
     */
    public MyExpandableListAdapter(Context context, List<Car> expandableListTitle,
                                   HashMap<Car, List<String>> expandableListDetail,Activity activity) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.activity=activity;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition));
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final  List<String>  expandedListText = (List<String>) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        TextView branchDetails=(TextView) convertView.findViewById(R.id.TextItemBranch);
        TextView modelDetails=(TextView) convertView.findViewById(R.id.TextItem);
        branchDetails.setText(expandedListText.get(2));
        modelDetails.setText(expandedListText.get(1));
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        Car listTitle = (Car) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.cars_design, null);
        }

        TextView carModel=(TextView)convertView.findViewById(R.id.ModelCars);//gets the items for the list
        final TextView carId=(TextView)convertView.findViewById(R.id.CarsIdList);//gets the items for the list
        TextView kilometer=(TextView)convertView.findViewById(R.id.KilometerCars);//gets the items for the list
        final ImageView like=(ImageView) convertView.findViewById(R.id.LikeACar);
       final TextView makeOrder=(TextView) convertView.findViewById(R.id.OrderTextView);
        makeOrder.setOnClickListener(new View.OnClickListener()//when click on order button
        {
            @Override
            public void onClick(View v)
            {
                makeOrder.setEnabled(false);
               Order order=new Order();
             String clientEmail=sharedpreferences.getString("email","");//geting client details from sharedpreferences
             String clientpassword=sharedpreferences.getString("password","");//geting client details from sharedpreferences
                float kilometerStartValue=0;
                try {
                    for (Client c:bekend.ClientsList())
                    {
                     if(c.getClientEmail().equals(clientEmail)&&c.getClientPassword().equals(clientpassword)) {
                          order.setOrderClientId(c.getClientId());
                     }

                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    for (Car car:bekend.CarsList())
                    {
                        if(car.getCarId().equals(carId.getText().toString())) {
                            kilometerStartValue = (Float.valueOf(car.getKilometer()));
                        }
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                order.setOrderKind(true);
                order.setOrderCarId(carId.getText().toString());
                Date rentalBegin= (Static.d1);
                order.setRentalBegin(rentalBegin);
                Date rentalFinish= (Static.d2);
                order.setRentalFinish(rentalFinish);
                order.setKilometerStartValue(kilometerStartValue);
                bekend.addOrder(order);
                Toast.makeText(MyExpandableListAdapter.this.context, "Your order has been made", Toast.LENGTH_SHORT).show();
           }

        });

        for (int i=0;i<sharedpreferences.getInt("counter",0 );i++)//checks which cars are in  sharedpreferences
        {
            carIdInShardprefence=sharedpreferences.getInt(String.valueOf(i),0);
            if(listTitle.getCarId().equals(String.valueOf(carIdInShardprefence)))
                like.setImageResource(R.drawable.heartaa);

            }

        carModel.setText(listTitle.getModel().toString());//gets the items for the list
        carId.setText(listTitle.getCarId().toString());//gets the items for the list
        kilometer.setText(listTitle.getKilometer().toString());//gets the items for the list
        final View finalConvertView = convertView;
        final View finalConvertView1 = convertView;
        like.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {//what to do when the client click on the like button
                boolean flag=false;
                for (int i=0;i<sharedpreferences.getInt("counter",0 );i++)
                {

                    carIdInShardprefence=sharedpreferences.getInt(String.valueOf(i),0);
                    if(carId.getText().toString().equals(String.valueOf(carIdInShardprefence))) {//if the car is allready in sharedpreferences
                        like.setImageResource(R.drawable.heart);
                        SharedPreferences.Editor editor=sharedpreferences.edit();
                        String hh=String.valueOf(i);
                        editor.putInt(String.valueOf(i),0);//delete the car from sharedpreferences
                        editor.commit();

                        int a=sharedpreferences.getInt(hh,0);
                        flag=true;
                    }

                }
                if(!flag) {////if the car is not allready in sharedpreferences
                    like.setImageResource(R.drawable.heartaa);
                    try {
                        SharedPreferences.Editor editor=sharedpreferences.edit();
                        int co=sharedpreferences.getInt("counter",0);
                        editor.putInt(String.valueOf(co), Integer.valueOf(carId.getText().toString()));//add the car to sharedpreferences
                        editor.commit();
                        int t=sharedpreferences.getInt(String.valueOf(co),0);
                        co+=1;
                        editor.putInt("counter",co);
                        editor.commit();
                        for (Car c:bekend.CarsList()) {
                            if(c.getCarId().equals(carId.getText().toString())) {
                                LikeCar.add(c);
                            }
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MyExpandableListAdapter.this.context, "Succeeded", Toast.LENGTH_SHORT).show();

                }

            }

        });
        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


}
