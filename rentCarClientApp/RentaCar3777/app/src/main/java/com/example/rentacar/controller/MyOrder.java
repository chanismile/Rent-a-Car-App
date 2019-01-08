package com.example.rentacar.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentacar.R;
import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.entities.Branch;
import com.example.rentacar.model.entities.Car;
import com.example.rentacar.model.entities.Client;
import com.example.rentacar.model.entities.Model;
import com.example.rentacar.model.entities.Order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.rentacar.controller.MainActivity.sharedpreferences;

/***
 * performing the order details and let close orders
 */
public class MyOrder extends Fragment {
    BackEnd backEnd= FactoryMy_Sql.getBackEnd();
    ListView theOrdersList;
    ArrayAdapter<Car> adapter;
    Button okButton;
    EditText fuel;
    EditText kilomemet;
    TextView carsDetails;
    CheckBox fullfuel;
    float totalPrice=0;
    LinearLayout closeLinarLayout;
    TextView theTotalPrice;
    Order order;
    Car car;
    String kilometer;
    List<Order> orders=new ArrayList<Order>();
    List<Car> carsList=new ArrayList<Car>();


    public MyOrder() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_my_order, container, false);
        findViews(rootView);
        showList();
        closeLinarLayout.setVisibility(View.INVISIBLE);
        theOrdersList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               closeLinarLayout.setVisibility(View.VISIBLE);
                order=orders.get(position);
                car=carsList.get(position);
                kilometer=car.getKilometer();

            }
        });
        kilomemet.addTextChangedListener(new TextWatcher() {//the kilomrter must be more then before order was opened
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (kilomemet.getText().toString().matches(""))
                {
                    okButton.setEnabled(false);

                }
                else if (Integer.parseInt(kilomemet.getText().toString())<=Integer.parseInt(kilometer)) {
                    kilomemet.setError("Invalid email address");
                    okButton.setEnabled(false);
                }
                else {
                    okButton.setEnabled(true);
                }

            }
        });


        fullfuel.setOnClickListener(new View.OnClickListener() {//if the client filled fuel
            @Override
            public void onClick(View v)
            {
                if(fullfuel.isChecked()) {
                    fuel.setVisibility(View.VISIBLE);

                }
            }
        });
        okButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {//closing the order
                try {
                    Order o=backEnd.ordersList().get(1);
                    totalPrice=calcTotalPrice(order);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if((kilomemet.getText().toString().matches("")))
                    Toast.makeText(getContext(), "enter kilometers", Toast.LENGTH_SHORT).show();
                else if(fullfuel.isChecked()&&fuel.getText().toString().matches(""))
                    Toast.makeText(getContext(), "enter enter fuel amount", Toast.LENGTH_SHORT).show();
                else {
                    if (fullfuel.isChecked()) {
                        totalPrice += (6 * Integer.parseInt(fuel.getText().toString()));
                        order.setFuelFilling(true);
                        order.setAmountFilling(Float.valueOf(fuel.getText().toString()));
                    }
                    else
                        order.setFuelFilling(false);
                    theTotalPrice.setText("price: "+String.valueOf(totalPrice));
                    order.setOrderKind(false);
                    order.setKilometerEndValue(Float.valueOf(kilomemet.getText().toString()));
                    order.setOrderPayment(totalPrice);
                    car.setKilometer(kilomemet.getText().toString());
                    backEnd.updateCarKilomoter(car);
                    backEnd.closeOrder(order);
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Order had closed");
                    alertDialog.setMessage("Price" +" : "+totalPrice);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "ok", new
                            DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(getActivity(), MyMenu.class);
                                    startActivity(intent);
                                    dialog.cancel();
                                }

                            });
                   alertDialog.show();

                }
            }
        });

        return rootView;
    }

    private void findViews(View view) {
        carsDetails=(TextView)view.findViewById( R.id.carDetails );
        okButton=(Button)view.findViewById( R.id.button );
        kilomemet=(EditText)view.findViewById(R.id.editText2);
        fuel=(EditText)view.findViewById(R.id.editText3);
        fullfuel=(CheckBox) view.findViewById(R.id.checkBox);
        theOrdersList=(ListView) view.findViewById( R.id.theOrdersList );
        closeLinarLayout=(LinearLayout)view.findViewById(R.id.closeLinarLayout) ;
        theTotalPrice=(TextView)view.findViewById(R.id.totalPrice);

    }

    /***
     * set an adapter and show the list
     */
    private void showList() {

        try {
            for (Order o:backEnd.ordersList())
            {
                if (o.getOrderClientId().equals(MainActivity.sharedpreferences.getString("id", "")))
                    for(Car c:backEnd.CarsList())
                        if(o.getOrderCarId().equals(c.getCarId())&&(o.isOrderKind())) {
                            carsList.add(c);
                            orders.add(o);
                        }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<Car>(getActivity(), R.layout.my_order_design, carsList) {

                @Override
                public View getView(final int  position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(getContext(), R.layout.my_order_design, null);//gets the desing layout
                    }
                    TextView carDetails = (TextView) convertView.findViewById(R.id.carDetails);//gets the items
                    carDetails.setText(carsList.get(position).toString());
                    TextView orderIdTextView=(TextView) convertView.findViewById(R.id.orderIdTextView);//gets the items
                    orderIdTextView.setText(((Integer)orders.get(position).getOrderId()).toString());

                    return convertView;
                }
            };
        theOrdersList.setAdapter(adapter);
    }

    /***
     * calculate the price
     * @param order is the order that we want to calculate the price for.
     * @return the price
     */
    public float calcTotalPrice(Order order){

        //calculate the num of days:
        long difference=order.getRentalFinish().getTime()-order.getRentalBegin().getTime();
        float daysBetween = (difference / (1000*60*60*24));
        if(daysBetween<=0)
            daysBetween=31;
        daysBetween+=1;
        //calculate price per day to car:
        int seats=0;
        float pricePerDay,priceToCar=0;
        if(daysBetween<31)
            pricePerDay=100;
        else
            pricePerDay=80;
        try {
            for(Car car:backEnd.CarsList())
            {
                if(order.getOrderCarId().equals(car.getCarId()))
                    for (Model model:backEnd.modelsList())
                        if(car.getModel().equals(model.getModelName()))
                            seats=Integer.parseInt(model.getSeats());
            }
            switch (seats) {
                case(3):
                    priceToCar=(pricePerDay-10)*daysBetween;
                    break;
                case(5):
                    priceToCar=pricePerDay*daysBetween;
                    break;
                case(7):
                    priceToCar=(pricePerDay+70)*daysBetween;
                    break;
                case(9):
                    priceToCar=(pricePerDay+100)*daysBetween;
                    break;
                default:
                    priceToCar=pricePerDay*daysBetween;

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return priceToCar;
    }

}
