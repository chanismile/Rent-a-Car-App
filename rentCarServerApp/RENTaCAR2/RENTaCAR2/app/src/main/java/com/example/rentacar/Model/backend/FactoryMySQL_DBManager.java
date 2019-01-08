package com.example.rentacar.Model.backend;

/**
 * Created by שרה ויסברגר on 06/05/2018.
 */

public class FactoryMySQL_DBManager {
    //..................................constructors...............................................
    FactoryMySQL_DBManager (){}
    protected static MySQL_DBManager mySQL_DBManager = null;
    public static BackEnd getBackEnd()//singelton
    {
        if (mySQL_DBManager == null)
            mySQL_DBManager = new MySQL_DBManager();
        return mySQL_DBManager;
    }
}
