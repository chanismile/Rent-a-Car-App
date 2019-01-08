package com.example.rentacar.model.backend;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import android.content.ContentValues;
import android.widget.DatePicker;

import com.example.rentacar.model.entities.Branch;
import com.example.rentacar.model.entities.Car;
import com.example.rentacar.model.entities.Client;
import com.example.rentacar.model.entities.Gearbox;
import com.example.rentacar.model.entities.Model;
import com.example.rentacar.model.entities.Order;
import com.example.rentacar.model.entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by שרה ויסברגר on 01/05/2018.
 */

public class Convert {
    public static ContentValues BranchToContentValues(Branch branch) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", branch.getAddress());
        contentValues.put("BranchNo", branch.getBranchNo());
        return contentValues;
    }
    public Branch ContentValuesToBranch(ContentValues contentValues) {
        Branch branch = new Branch();
        branch.setAddress(contentValues.getAsString("address"));
        branch.setBranchNo(contentValues.getAsInteger("branchNo"));
        branch.setNumOfParking(contentValues.getAsString("numOfParking"));
        return branch;
    }
    public static ContentValues CarToContentValues(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("carBranchNo", car.getBranchNo());
        contentValues.put("carId", car.getCarId());
        contentValues.put("kilometer", car.getKilometer());
        contentValues.put("model", car.getModel());
        return contentValues;
    }
    public Car ContentValuesToCar(ContentValues contentValues) {
        Car car = new Car();
        car.setBranchNo(contentValues.getAsString("carBranchNo"));
        car.setCarId(contentValues.getAsString("CarId"));
        car.setKilometer(contentValues.getAsString("kilometer"));
        car.setModel(contentValues.getAsString("model"));
        return car;
    }
    public static ContentValues ClientToContentValues(Client client) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditCard", client.getCreditCard());
        contentValues.put("fName", client.getfName());
        contentValues.put("clientId", client.getClientId());
        contentValues.put("lName", client.getlName());
        contentValues.put("clientEmail", client.getClientEmail());
        contentValues.put("phoneNumber", client.getPhoneNumber());
        contentValues.put("clientPassword", client.getClientPassword());
        return contentValues;
    }
    public Client ContentValuesToClient(ContentValues contentValues) {
        Client client = new Client();
        client.setCreditCard(contentValues.getAsString("creditCard"));
        client.setfName(contentValues.getAsString("fName"));
        client.setClientId(contentValues.getAsString("clientId"));
        client.setlName(contentValues.getAsString("lName"));
        client.setClientEmail(contentValues.getAsString("clientEmail"));
        client.setPhoneNumber(contentValues.getAsString("phoneNumber"));
        client.setClientPassword(contentValues.getAsString("clientPassword"));

        return client;
    }
    public static ContentValues ModelToContentValues(Model model) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("engineCapacity", model.getEngineCapacity());
        contentValues.put("factoryName", model.getFactoryName());
        contentValues.put("modelCode", model.getModelCode());
        contentValues.put("modelName", model.getModelName());
        contentValues.put("seats", model.getSeats());
        contentValues.put("gearbox", model.getGearbox().toString());
        return contentValues;
    }
    public Model ContentValuesToModel(ContentValues contentValues) {
        Model model = new Model();
        model.setEngineCapacity(contentValues.getAsString("engineCapacity"));
        model.setFactoryName(contentValues.getAsString("factoryName"));
        model.setGearbox(Gearbox.valueOf(contentValues.getAsString("gearbox")));
        model.setModelCode(contentValues.getAsString("modelCode"));
        model.setModelName(contentValues.getAsString("ModelName"));
        model.setSeats(contentValues.getAsString("seats"));
        return model;
    }
    public static ContentValues UserToContentValues(User user)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("userEmail", user.getEmail());
        contentValues.put("userId", user.getId());
        contentValues.put("hint", user.getHint());
        contentValues.put("password", user.getPassword());
        contentValues.put("userName", user.getUserName());
        return contentValues;
    }
    public User ContentValuesToUser(ContentValues contentValues) {
        User user = new User();
        user.setId(contentValues.getAsString("userId"));
        user.setEmail(contentValues.getAsString("userEmail"));
        user.setHint(contentValues.getAsString("hint"));
        user.setPassword(contentValues.getAsString("password"));
        user.setUserName(contentValues.getAsString("userName"));
        return user;
    }

    public static ContentValues OrderToContentValues(Order order)
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put("orderClientId", order.getOrderClientId());
        if(order.isOrderKind())
            contentValues.put("orderKind",1);
        else
            contentValues.put("orderKind",0);
        contentValues.put("orderCarId", order.getOrderCarId());
        contentValues.put("rentalBegin", order.getRentalBegin().toString());
        contentValues.put("rentalFinish", order.getRentalFinish().toString());
        contentValues.put("kilometerStartValue", order.getKilometerStartValue());
        contentValues.put("kilometerEndValue", order.getKilometerEndValue());
        if(order.isFuelFilling())
            contentValues.put("fuelFilling",1);
        else
            contentValues.put("fuelFilling",0);
        contentValues.put("amountFilling", order.getAmountFilling());
        contentValues.put("orderPayment", order.getOrderPayment());
        contentValues.put("orderId", order.getOrderId());
        return contentValues;
    }
    public Order ContentValuesToOrder(ContentValues contentValues) {
        Order order = new Order();
        java.text.DateFormat format =new java.text.SimpleDateFormat("yyyy-MM-dd");
            order.setOrderClientId(contentValues.getAsString("orderClientId"));
            order.setOrderKind(contentValues.getAsBoolean("orderKind"));
            order.setOrderCarId(contentValues.getAsString("orderCarId"));
        try {
            order.setRentalBegin(format.parse(contentValues.getAsString("rentalBegin")));
            order.setRentalFinish(format.parse(contentValues.getAsString("rentalFinish")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
            order.setKilometerStartValue(contentValues.getAsFloat("kilometerStartValue"));
            order.setKilometerEndValue(contentValues.getAsFloat("kilometerEndValue"));
            order.setFuelFilling(contentValues.getAsBoolean("fuelFilling"));
            order.setAmountFilling(contentValues.getAsFloat("amountFilling"));
            order.setOrderPayment(contentValues.getAsFloat("orderPayment"));
            order.setOrderId(contentValues.getAsInteger("orderId"));

        return order;

    }
}
