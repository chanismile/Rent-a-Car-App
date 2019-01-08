package com.example.rentacar.Model.backend;

import android.content.ContentValues;

import com.example.rentacar.Model.entities.Branch;
import com.example.rentacar.Model.entities.Car;
import com.example.rentacar.Model.entities.Client;
import com.example.rentacar.Model.entities.Gearbox;
import com.example.rentacar.Model.entities.Model;
import com.example.rentacar.Model.entities.User;

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
        contentValues.put("CarId", car.getCarId());
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
        contentValues.put("clientId", client.getId());
        contentValues.put("lName", client.getlName());
        contentValues.put("clientEmail", client.getMail());
        contentValues.put("phoneNumber", client.getPhoneNumber());
        return contentValues;
    }
    public Client ContentValuesToClient(ContentValues contentValues) {
        Client client = new Client();
        client.setCreditCard(contentValues.getAsString("creditCard"));
        client.setfName(contentValues.getAsString("fName"));
        client.setId(contentValues.getAsString("clientId"));
        client.setlName(contentValues.getAsString("lName"));
        client.setMail(contentValues.getAsString("clientEmail"));
        client.setPhoneNumber(contentValues.getAsString("phoneNumber"));
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
}
