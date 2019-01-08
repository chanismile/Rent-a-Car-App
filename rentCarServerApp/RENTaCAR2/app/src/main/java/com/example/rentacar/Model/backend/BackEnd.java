package com.example.rentacar.Model.backend;

import android.os.AsyncTask;

import com.example.rentacar.Model.entities.Branch;
import com.example.rentacar.Model.entities.Car;
import com.example.rentacar.Model.entities.Client;
import com.example.rentacar.Model.entities.Model;
import com.example.rentacar.Model.entities.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by שרה ויסברגר on 13/03/2018.
 */

public interface BackEnd
{
    public Boolean checkIfClientExistInSystem(Client c) throws ExecutionException, InterruptedException;//checks if a client exist in system
    public Boolean checkIfBranchExistInSystem(Branch b) throws ExecutionException, InterruptedException;//checks if a Branch exist in system
    public Boolean checkIfModelExistInSystem(Model m) throws ExecutionException, InterruptedException;//checks if a Model exist in system
    public Boolean checkIfCarExistInSystem(Car c) throws ExecutionException, InterruptedException;//checks if a Car exist in system
    public Boolean checkIfUserExistInSystem(User u) throws ExecutionException, InterruptedException;//checks if a user exist in system

    public AsyncTask<Client, Void, Void> addClient(Client client);//add a client to system
    public void addModel(Model model);//add a model to system
    public void addCar(Car car);//add a car to system
    public void addBrunch(Branch branch);//add a brunch to system
    public void addUser(User user);
    public List<Model> modelsList() throws ExecutionException, InterruptedException;
    public List<Branch> branchesList() throws ExecutionException, InterruptedException;
    public List<Car> CarsList() throws ExecutionException, InterruptedException;
    public List<User> UserList() throws ExecutionException, InterruptedException;
    public List<Client> ClientsList() throws ExecutionException, InterruptedException;


}
