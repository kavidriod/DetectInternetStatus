package com.example.kavitha.detectinternetstatus;

import android.app.Application;

/**
 * Created by Kavitha on 07-06-2017.
 */

public class MyApplication extends Application{

    private static MyApplication myApplicationInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplicationInstance = this;
    }

    public static synchronized MyApplication getInstance(){
        return  myApplicationInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener receiverListener){
        ConnectivityReceiver.connectivityReceiverListener = receiverListener;
    }
}
