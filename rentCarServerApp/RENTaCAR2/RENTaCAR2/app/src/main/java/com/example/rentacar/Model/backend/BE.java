package com.example.rentacar.Model.backend;

import android.os.AsyncTask;

import com.example.rentacar.Model.datasource.Ds_lists;
import com.example.rentacar.Model.entities.Branch;
import com.example.rentacar.Model.entities.Car;
import com.example.rentacar.Model.entities.Client;
import com.example.rentacar.Model.entities.Model;
import com.example.rentacar.Model.entities.User;
import com.example.rentacar.controller.CarsList;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * this class implements the backEnd interface
 */

public class BE implements BackEnd
{

    private Ds_lists ds;
    public BE(){ds=new Ds_lists();}

    /**
     *
     * @param u
     * @return true if the user exist in system
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Boolean checkIfUserExistInSystem(User u) throws ExecutionException, InterruptedException {

            return new AsyncTask<User,Void,Boolean>()
            {
                @Override
                protected Boolean doInBackground(User... params) {
                    for (User u : Ds_lists.getUser())
                    {
                        if(u.getId().equals(params[0].getId()))
                            return true;
                    }
                    return false;
                }
            }.execute(u).get();


    }
    /**
     *
     * @param c
     * @return true if the client exist in system
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Boolean checkIfClientExistInSystem(Client c) throws ExecutionException, InterruptedException {

        return new AsyncTask<Client,Void,Boolean>()
        {
            @Override
            protected Boolean doInBackground(Client... params) {
                for (Client c : Ds_lists.getClients())
                {
                    if(c.getId().equals(params[0].getId()))
                        return true;
                }
                return false;
            }
        }.execute(c).get();
        /**
         *
         * @param m
         * @return true if the model exist in system
         * @throws ExecutionException
         * @throws InterruptedException
         */

    }
    public Boolean checkIfModelExistInSystem(Model m) throws ExecutionException, InterruptedException {

        return new AsyncTask<Model,Void,Boolean>()
        {
            @Override
            protected Boolean doInBackground(Model... params) {
                for (Model m : Ds_lists.getModels())
                {
                    if(m.getModelCode().equals(params[0].getModelCode()))
                        return true;
                }
                return false;
            }
        }.execute(m).get();

/**
 *
 * @param b
 * @return true if the branch exist in system
 * @throws ExecutionException
 * @throws InterruptedException
 */
    }
    public Boolean checkIfBranchExistInSystem(Branch b) throws ExecutionException, InterruptedException {

        return new AsyncTask<Branch,Void,Boolean>()
        {
            @Override
            protected Boolean doInBackground(Branch... params) {
                for (Branch b : Ds_lists.getBranches())
                {
                    if(b.getAddress().equals(params[0].getAddress()))
                        return true;
                }
                return false;
            }
        }.execute(b).get();


    }

    @Override
    public Boolean checkIfCarExistInSystem(Car c) throws ExecutionException, InterruptedException {
        return new AsyncTask<Car,Void,Boolean>()
        {
            @Override
            protected Boolean doInBackground(Car... params) {
                for (Car c : Ds_lists.getCars())
                {
                    if(c.getCarId().equals(params[0].getCarId()))
                        return true;
                }
                return false;
            }
        }.execute(c).get();    }

    /**
     * adds a client to system
     * @param client
     * @return
     */
    //add client to system
    @Override
    public AsyncTask<Client, Void, Void> addClient(final Client client)
    {
        return new AsyncTask<Client,Void,Void>(){
            @Override
            protected Void doInBackground(Client... params) {

                   /* if(checkIfClientExistInSystem(client.getId()))
                           return null;*/
                    Ds_lists.getClients().add(params[0]);
                    return null;
               /* } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


            }
        }.execute(client);


    }

    /**
     *adds model to system
     * @param model
     */
    @Override
    public void addModel(Model model)
    {
        new AsyncTask<Model,Void,Void>(){
            @Override
            protected Void doInBackground(Model... params) {
                Ds_lists.getModels().add(params[0]);
                return null;
            }
        }.execute(model);

    }

    /**
     * add Car to system
     * @param car
     */
    @Override
    public void addCar(Car car)
    {
        new AsyncTask<Car,Void,Void>(){
            @Override
            protected Void doInBackground(Car... params) {
                Ds_lists.getCars().add(params[0]);
                return null;
            }
        }.execute(car);

    }

    @Override
    public void addBrunch(Branch branch) {
        new AsyncTask<Branch,Void,Void>(){
            @Override
            protected Void doInBackground(Branch... params) {
                Ds_lists.getBranches().add(params[0]);
                return null;
            }
        }.execute(branch);
    }

    /**
     * add User to system
     * @param user
     */
    @Override
    public void addUser(User user) {
        new AsyncTask<User,Void,Void>(){
            @Override
            protected Void doInBackground(User... params) {
                Ds_lists.getUser().add(params[0]);
                return null;
            }
        }.execute(user);

    }

    /**
     *
     * @return the Model list
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public List<Model> modelsList() throws ExecutionException, InterruptedException {
          return new AsyncTask<Void, Void, List<Model>>() {
              @Override
              protected List<Model> doInBackground(Void... params) {
                  return Ds_lists.getModels();
              }
          }.execute().get();
    }

    /**
     *
     * @return the branch list
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public List<Branch> branchesList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Branch>>() {
            @Override
            protected List<Branch> doInBackground(Void... params) {
                return Ds_lists.getBranches();
            }
        }.execute().get();

    }

    /**
     *
     * @return the car list
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public List<Car> CarsList() throws ExecutionException, InterruptedException {

        return new AsyncTask<Void, Void, List<Car>>() {
            @Override
            protected List<Car> doInBackground(Void... params) {
                return Ds_lists.getCars();
            }
        }.execute().get();

    }

    /**
     *
     * @return the users list
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public List<User> UsersList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {
                return Ds_lists.getUser();
            }
        }.execute().get();

    }

    /**
     *
     * @return the client list
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public List<Client> ClientsList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Client>>() {
            @Override
            protected List<Client> doInBackground(Void... params) {
                return Ds_lists.getClients();
            }
        }.execute().get();
    }
}
