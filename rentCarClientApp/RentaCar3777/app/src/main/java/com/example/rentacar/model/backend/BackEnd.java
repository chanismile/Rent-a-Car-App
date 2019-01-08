package com.example.rentacar.model.backend;

import android.os.AsyncTask;

import com.example.rentacar.model.entities.Branch;
import com.example.rentacar.model.entities.Car;
import com.example.rentacar.model.entities.Client;
import com.example.rentacar.model.entities.Model;
import com.example.rentacar.model.entities.Order;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by שרה ויסברגר on 13/05/2018.
 */

public interface BackEnd
{
    public List<Car> CarsList() throws ExecutionException, InterruptedException;//return the cars list
    public List<Order> ordersList() throws ExecutionException, InterruptedException;//return the order list

    public Boolean checkIfClientExistInSystem(Client c) throws ExecutionException, InterruptedException;//checks if a client exist in system

    Boolean checkIfClientExistInSystemById(String id) throws ExecutionException, InterruptedException;

    public AsyncTask<Client, Void, Void> addClient(Client client);//add a client to system
    public void updateCarKilomoter(Car c);//updaate the cars kilometer
    public List<Client> ClientsList() throws ExecutionException, InterruptedException;//return the Client List
    public List<Branch> branchesList() throws ExecutionException, InterruptedException;//return the branch List
    public List<Car> availiableCarsList() throws ExecutionException, InterruptedException;//return the availiable cars
    public List<Car> branchAvailiableCarsList(String branchNum) throws ExecutionException, InterruptedException;//return the branch availiable cars
    public List<Car> currentLocationAvailiableCarsList() throws ExecutionException, InterruptedException;//return the current Location availiable cars
    public List<Model> modelsList() throws ExecutionException, InterruptedException;//return the model List
    public AsyncTask<Model, Void, List<Branch>> availiableCarModelbranchesList(Model model) throws ExecutionException, InterruptedException;//return the branches List which have the model
    public List<Order> openOrders() throws ExecutionException, InterruptedException;//return List of open orders
    public void addOrder(Order order);//add a Order to system
    public void closeOrder(Order order);//close an order
    public Boolean checkIfOrdersHadClosed() throws ExecutionException, InterruptedException;//checks if an order had closed in the last ten minuts
    public void setIsChanged();//change the filed IsChanged to false
    public Boolean checkIfClientExistInSystemByPassword(String password,String email)throws ExecutionException, InterruptedException;//checks if a client exist in system by password and email
}
