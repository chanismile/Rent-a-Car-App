package com.example.rentacar.Model.backend;

/**
 * Created by שרה ויסברגר on 09/04/2018.
 */

public class FactoryBE
{
    //..................................constructors...............................................
    FactoryBE(){}
    protected static BE be = null;
    public static BackEnd getBackEnd()//singelton
    {
        if (be == null)
            be = new BE();
        return be;
    }

}
