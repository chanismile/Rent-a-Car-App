package com.example.rentacar.model.backend;

/**
 * Created by שרה ויסברגר on 06/05/2018.
 */

public class FactoryMy_Sql {
    //..................................constructors...............................................
    FactoryMy_Sql(){}
    protected static My_Sql mySQL_DBManager = null;
    public static BackEnd getBackEnd()//singelton
    {
        if (mySQL_DBManager == null)
            mySQL_DBManager = new My_Sql();
        return mySQL_DBManager;
    }
}
