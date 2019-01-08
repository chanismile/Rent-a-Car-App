package com.example.rentacar.service;

import android.app.IntentService;
import android.content.Intent;

import com.example.rentacar.model.backend.BackEnd;
import com.example.rentacar.model.backend.FactoryMy_Sql;
import com.example.rentacar.model.backend.My_Sql;
import com.example.rentacar.model.backend.Static;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    private boolean isRunning=false;
    BackEnd backEnd= FactoryMy_Sql.getBackEnd();
    @Override
    public void onCreate()
    {
    super.onCreate();
        isRunning = true;

    }
    public MyIntentService() {

        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        while(true)
       {

            try {
                Thread.sleep(10000);
                if(Static.CARRELEASED==1)
                {
                    Static.CARRELEASED=0;
                    Intent intent1 = new Intent(/*"MyPerfectReceiver"*/);
                    intent1.setAction("change");
                    MyIntentService.this.sendBroadcast(intent1);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

       }


    }


}
