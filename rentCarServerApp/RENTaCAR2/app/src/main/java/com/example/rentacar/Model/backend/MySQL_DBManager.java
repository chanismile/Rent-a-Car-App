package com.example.rentacar.Model.backend;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.rentacar.Model.datasource.Ds_lists;
import com.example.rentacar.Model.datasource.PHPtools;
import com.example.rentacar.Model.entities.Branch;
import com.example.rentacar.Model.entities.Car;
import com.example.rentacar.Model.entities.Client;
import com.example.rentacar.Model.entities.Model;
import com.example.rentacar.Model.entities.User;
import com.example.rentacar.Model.entities.Gearbox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by שרה ויסברגר on 01/05/2018.
 */

public class MySQL_DBManager implements BackEnd
{
    private String WEB_URL = "http://wiseberg.vlab.jct.ac.il/Academy";

    /**
     *
     * @param u
     * @return true if the user exist in system
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Boolean checkIfUserExistInSystem(User u) throws ExecutionException, InterruptedException
    {

            {
                try {
                    if (UserList().contains(u))
                        return true;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
           }
        }

    /**
     *
     * @param c
     * @return true if the client exist in system
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Boolean checkIfClientExistInSystem(Client c) throws ExecutionException, InterruptedException {

        {
            try {
                if (ClientsList().contains(c))
                    return true;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
        }
        /**
         *
         * @param m
         * @return true if the model exist in system
         * @throws ExecutionException
         * @throws InterruptedException
         */
    @Override
        public Boolean checkIfModelExistInSystem(Model m) throws ExecutionException, InterruptedException {

            {
                try {
                    if (modelsList().contains(m))
                        return true;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
/**
 *
 * @param b
 * @return true if the branch exist in system
 * @throws ExecutionException
 * @throws InterruptedException
 */
    @Override
    public Boolean checkIfBranchExistInSystem(Branch b) throws ExecutionException, InterruptedException {

        {
            try {
                if (branchesList().contains(b))
                    return true;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     *
     * @param c
     * @return true if the car exist in system
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Boolean checkIfCarExistInSystem(Car c) throws ExecutionException, InterruptedException {
        {
            try {
                if (CarsList().contains(c))
                    return true;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

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
                try
                {
                    String result = PHPtools.POST(WEB_URL + "/Add_Client.php", Convert.ClientToContentValues(params[0]));
                }
                catch (IOException e) {
                    e.printStackTrace();

                }
                return null;
            }
        }.execute(client);
    }

    /**
     *adds model to system
     * @param model
     */
    @Override
    public void addModel(final Model model)
    {
        new AsyncTask<Model,Void,Void>(){
            @Override
            protected Void doInBackground(Model... params)
            {
                try {
                    String result = PHPtools.POST(WEB_URL + "/Add_model.php", Convert.ModelToContentValues(params[0]));
                }
                catch (IOException e)
                {
                    e.printStackTrace();

                }
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
                try {
                    String result = PHPtools.POST(WEB_URL + "/Add_car.php", Convert.CarToContentValues(params[0]));
                }
                catch (IOException e)
                {
                    e.printStackTrace();

                }
                return null;
            }
        }.execute(car);

    }

    /**
     * add branch to system
     * @param branch
     */
    @Override
    public void addBrunch(final Branch branch)
    {
        new AsyncTask<Branch,Void,Void>()
        {
            @Override
            protected Void doInBackground(Branch... params)
            {
                try {
                    String result = PHPtools.POST(WEB_URL + "/Add_branch.php", Convert.BranchToContentValues(params[0]));
                }
                catch (IOException e)
                {
                    e.printStackTrace();

                }
                return null;
            }
        }.execute(branch);

    }




    /**
     * add User to system
     * @param user
     */
    @Override
    public void addUser(final User user) {
        new AsyncTask<User,Void,Void>(){
            @Override
            protected Void doInBackground(User... params)
            {
                try {
                    String result = PHPtools.POST(WEB_URL + "/Add_user.php", Convert.UserToContentValues(params[0]));
                }
                catch (IOException e)
                {
                    e.printStackTrace();

                }
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
                List<Model> result = new ArrayList<Model>();
                try {
                    String str = PHPtools.GET(WEB_URL + "/Models.php");
                    JSONArray array = new JSONObject(str).getJSONArray("models");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Model model = new Model();
                        model.setSeats(jsonObject.getString("seats"));
                        model.setModelName(jsonObject.getString("modelName"));
                        model.setModelCode(jsonObject.getString("modelCode"));
                        Gearbox gearbox = null;
                        String re = jsonObject.getString("gearbox");
                        switch (re)
                        {
                            case "AUTOMATON":
                                gearbox = Gearbox.AUTOMATON;
                                break;
                            case "MANUAL":
                                gearbox = Gearbox.MANUAL;
                                break;

                        }
                        model.setGearbox(gearbox);
                        model.setFactoryName(jsonObject.getString("factoryName"));
                        model.setEngineCapacity(jsonObject.getString("engineCapacity"));

                        result.add(model);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
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
                List<Branch> result = new ArrayList<Branch>();
                try {
                    String str = PHPtools.GET(WEB_URL + "/Branches.php");
                    JSONArray array = new JSONObject(str).getJSONArray("branches");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Branch branch = new Branch();
                        branch.setNumOfParking(jsonObject.getString("numOfParking"));
                        branch.setAddress(jsonObject.getString("address"));
                        branch.setBranchNo(jsonObject.getInt("branchNo"));
                        result.add(branch);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
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
                List<Car> result = new ArrayList<Car>();
                try {
                    String str = PHPtools.GET(WEB_URL + "/Cars.php");
                    JSONArray array = new JSONObject(str).getJSONArray("cars");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Car car = new Car();
                        car.setModel(jsonObject.getString("model"));
                        car.setKilometer(jsonObject.getString("kilometer"));
                        car.setCarId(jsonObject.getString("carId"));
                        car.setBranchNo(jsonObject.getString("carBranchNo"));

                        result.add(car);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
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
    public List<User> UserList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {
                List<User> result = new ArrayList<User>();
                try {
                    String str = PHPtools.GET(WEB_URL + "/Users.php");
                    JSONArray array = new JSONObject(str).getJSONArray("users");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        User user = new User();
                        user.setUserName(jsonObject.getString("userName"));
                        user.setPassword(jsonObject.getString("password"));
                        user.setEmail(jsonObject.getString("userEmail"));
                        user.setId(jsonObject.getString("userId"));
                        user.setHint(jsonObject.getString("hint"));
                        result.add(user);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;}

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
                List<Client> result = new ArrayList<Client>();
                try {
                    String str = PHPtools.GET(WEB_URL + "/Clients.php");
                    JSONArray array = new JSONObject(str).getJSONArray("clients");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Client client = new Client();
                        client.setId(jsonObject.getString("clientId"));
                        client.setPhoneNumber(jsonObject.getString("phoneNumber"));
                        client.setfName(jsonObject.getString("fName"));
                        client.setMail(jsonObject.getString("clientEmail"));
                        client.setCreditCard(jsonObject.getString("creditCard"));
                        client.setlName(jsonObject.getString("lName"));

                        result.add(client);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute().get();

    }


}