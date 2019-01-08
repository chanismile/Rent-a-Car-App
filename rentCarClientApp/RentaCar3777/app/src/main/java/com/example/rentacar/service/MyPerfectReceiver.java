package com.example.rentacar.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyPerfectReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

     if (intent.getAction().matches("change"))
        Toast.makeText(context, "more cars added to system!!", Toast.LENGTH_LONG).show();
    }
}
