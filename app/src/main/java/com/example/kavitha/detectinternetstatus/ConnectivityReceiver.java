package com.example.kavitha.detectinternetstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import  android.util.Log;

/**
 * Created by Kavitha on 07-06-2017.
 */

public class ConnectivityReceiver extends BroadcastReceiver {

   static String  TAG = ConnectivityReceiver.class.getSimpleName();

public static  ConnectivityReceiverListener connectivityReceiverListener;

    public ConnectivityReceiver(){
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG , "action: "
                + intent.getAction());
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
         if (connectivityReceiverListener != null){
             connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
         }
    }

    public interface ConnectivityReceiverListener{

        void onNetworkConnectionChanged(boolean isConnected);
    }

    public static boolean isConnected(){
       /* ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication
                .getMyApplicationInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return  activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
*/
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }
}
