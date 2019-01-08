package com.example.rentacar.Model.datasource;

import com.example.rentacar.Model.entities.Branch;
import com.example.rentacar.Model.entities.Car;
import com.example.rentacar.Model.entities.Client;
import com.example.rentacar.Model.entities.Model;
import com.example.rentacar.Model.entities.Order;
import com.example.rentacar.Model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by שרה ויסברגר on 13/03/2018.
 */

public class Ds_lists    //the lists of the entities

{
    private static List<Branch> Branches;
    private static List<Car> Cars;
    private static List<Client> Clients;
    private static List<Model> Models;
    private static List<Order> Orders;
    private static List<User> Users;
    //..................................constructors...............................................
    public Ds_lists()
    {
        Branches=new ArrayList<Branch>();
        Cars=new ArrayList<Car>();
        Clients=new ArrayList<Client>();
        Models=new ArrayList<Model>();
        Orders=new ArrayList<Order>();
        Users=new ArrayList<User>();

    }
    //....................................getters..................................................
    public static List<Branch> getBranches() {
        return Branches;
    }

    public static List<Car> getCars() {
        return Cars;
    }

    public static List<Client> getClients() {
        return Clients;
    }

    public static List<Model> getModels() {
        return Models;
    }

    public static List<Order> getOrders() {
        return Orders;
    }

    public static List<User> getUser() {
        return Users;
    }

}
