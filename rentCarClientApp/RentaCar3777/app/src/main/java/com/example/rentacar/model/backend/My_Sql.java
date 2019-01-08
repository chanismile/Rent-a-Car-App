package com.example.rentacar.model.backend;

import android.content.ContentValues;
import android.os.AsyncTask;

import com.example.rentacar.model.dataSource.PHPtools;
import com.example.rentacar.model.entities.Branch;
import com.example.rentacar.model.entities.Car;
import com.example.rentacar.model.entities.Client;
import com.example.rentacar.model.entities.Gearbox;
import com.example.rentacar.model.entities.Model;
import com.example.rentacar.model.entities.Order;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by שרה ויסברגר on 13/05/2018.
 */


public class My_Sql implements BackEnd {
    private String WEB_URL = "http://wiseberg.vlab.jct.ac.il/Academy";
    private boolean isChanged=true;

    /***
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
     * @param c is theclient that we want to check if exist in system
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Boolean checkIfClientExistInSystem(Client c) throws ExecutionException, InterruptedException {
        {
            try {
                List<Client> gg=ClientsList();
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
     * @param id is theclient id that we want to check if exist in system
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Boolean checkIfClientExistInSystemById(String id) throws ExecutionException, InterruptedException {
        {
            try {
                List<Client> gg=ClientsList();
                if(gg==null)
                    return false;
                for (Client c:gg) {
                    if(c.getClientId().equals(id))
                        return true;
                 return false;
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * add client to system
     * @param client
     * @return
     */
    @Override
    public AsyncTask<Client, Void, Void> addClient(Client client) {
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
        }.execute(client);    }

    /**
     * update the kilometer of the car
     * @param car
     */
    @Override
    public void updateCarKilomoter(Car car) {
        new AsyncTask<Car,Void,Void>(){
            @Override
            protected Void doInBackground(Car... params) {
                try {
                    String result = PHPtools.POST(WEB_URL + "/updateCar.php",Convert.CarToContentValues(params[0]));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute(car);
    }

    /**
     * get the ClientsList
     * @return ClientsList
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
                        client.setClientId(jsonObject.getString("clientId"));
                        client.setPhoneNumber(jsonObject.getString("phoneNumber"));
                        client.setfName(jsonObject.getString("fName"));
                        client.setClientEmail(jsonObject.getString("clientEmail"));
                        client.setCreditCard(jsonObject.getString("creditCard"));
                        client.setlName(jsonObject.getString("lName"));
                        client.setClientPassword(jsonObject.getString("clientPassword"));

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

    /**
     * get the branchesList
     * @return branchesList
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
     * get the ordersList
     * @return ordersList
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public List<Order> ordersList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Order>>() {
            @Override
            protected List<Order> doInBackground(Void... params) {
                List<Order> result = new ArrayList<Order>();
                try {
                    String str = PHPtools.GET(WEB_URL + "/Orders.php");
                    JSONArray array = new JSONObject(str).getJSONArray("orders");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Order order = new Order();
                        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                        order.setOrderClientId(jsonObject.getString("orderClientId"));
                        order.setRentalBegin(format.parse(jsonObject.getString("rentalBegin")));
                        order.setRentalFinish(format.parse(jsonObject.getString("rentalFinish")));
                        if (jsonObject.getInt("orderKind") == 1)
                            order.setOrderKind(true);
                        else
                            order.setOrderKind(false);
                        order.setOrderCarId(jsonObject.getString("orderCarId"));
                        order.setKilometerEndValue(Float.parseFloat(jsonObject.getString("kilometerEndValue")));
                       order.setKilometerStartValue(Float.parseFloat(jsonObject.getString("kilometerStartValue")));
                        if (jsonObject.getInt("fuelFilling") == 1)
                            order.setFuelFilling(true);
                        else
                            order.setFuelFilling(false);
                        order.setAmountFilling(Float.parseFloat(jsonObject.getString("amountFilling")));
                        order.setOrderPayment(Float.parseFloat(jsonObject.getString("orderPayment")));
                        order.setOrderId(jsonObject.getInt("orderId"));
                        result.add(order);
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
     * @return list of the availiable cars
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public List<Car> availiableCarsList() throws ExecutionException, InterruptedException {
        List<Car> carsList=CarsList();
        List<Car> result=new ArrayList<Car>();
        List<Order> OrdersList=ordersList();
        int flag=0;
        for (Car c:carsList) {
            for (Order o:OrdersList)
            {
                if (o.getOrderCarId().equals(c.getCarId())&&o.isOrderKind())//if the car is orderd
                    flag=1;
            }
            if(flag==0)//the car is not order-the car is availiable
                result.add(c);
            flag=0;
        }
        return result;

    }



    @Override
    public List<Car> currentLocationAvailiableCarsList() throws ExecutionException, InterruptedException {
        return null;
    }

    /**
     *
     * @param branch
     * @return the branch availiable cars
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public  List<Car> branchAvailiableCarsList(final String branch) throws ExecutionException, InterruptedException {
                List<Car> availiableCarsList = null;
                List<Car> result = new ArrayList<Car>();

                try {
                    availiableCarsList = availiableCarsList();
                    for (Car c : availiableCarsList) {
                        if (c.getBranchNo().equals(branch))//check if the car exist in the branch
                            result.add(c);
                    }
                    return result;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;

            }

    /**
     * get the modelsList
     * @return modelsList
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
        }.execute().get();    }

    /**
     * get the availiable CarModel branchesList
     * @param model
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public AsyncTask<Model, Void, List<Branch>> availiableCarModelbranchesList(Model model) throws ExecutionException, InterruptedException {
        return new AsyncTask<Model, Void, List<Branch>>() {
            @Override
            protected List<Branch> doInBackground(Model... params) {
                List<Car> availiableCarsList= null;
                try {
                    availiableCarsList = availiableCarsList();

                List<Branch> result=new ArrayList<Branch>();
                Branch branch=new Branch();
                for (Car c: availiableCarsList )
                {
                    if(c.getModel().equals(params[0]))
                        for (Branch b:branchesList())
                        {
                            if(c.getBranchNo().toString().equals(b.getBranchNo()))
                            {
                                if(!result.contains(b))
                                    result.add(branch);
                            }
                        }

                }
                    return  result;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute(model);


    }

    /**
     *
     * @return the list of openOrders
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public List<Order> openOrders() throws ExecutionException, InterruptedException {
        List<Order> result=new ArrayList<Order>();
        for (Order o:ordersList()
             ) {
            if(o.isOrderKind())
                result.add(o);
        }
        return result;
    }

    /**
     * add the order to system
     * @param order
     */
    @Override
    public void addOrder(Order order) {
        new AsyncTask<Order,Void,Void>()
        {
            @Override
            protected Void doInBackground(Order... params)
            {
                try {
                    String result = PHPtools.POST(WEB_URL + "/Add_order.php", Convert.OrderToContentValues(params[0]));
                }
                catch (IOException e)
                {
                    e.printStackTrace();

                }
                return null;
            }
        }.execute(order);
    }

    /**
     * close the order
     * @param order
     */
    @Override
    public void closeOrder(Order order)
    {
        new AsyncTask<Order,Void,Void>()
        {


            @Override
            protected Void doInBackground(Order... params) {
                try {

                    String result;
                    result = PHPtools.POST(WEB_URL + "/updateOrder.php", Convert.OrderToContentValues(params[0]));
                    isChanged=true;
                    Static.CARRELEASED=1;

                }
                catch (IOException e)
                {
                    e.printStackTrace();

              }

                return null;
            }
        }.execute(order);
    }

    @Override
    public Boolean checkIfOrdersHadClosed() throws ExecutionException, InterruptedException {
        return isChanged;
    }
    @Override
    public void setIsChanged() {
        isChanged=false;
    }

    /**
     * check If Clien tExist In System By Password
     * @param password
     * @param email
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Boolean checkIfClientExistInSystemByPassword(String password,String email) throws ExecutionException, InterruptedException {
            try {
                List<Client> clientsList=ClientsList();
                for(Client c:clientsList)
                {
                    if(c.getClientEmail().equals(email)&&c.getClientPassword().equals(password))
                        return true;
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }


}
