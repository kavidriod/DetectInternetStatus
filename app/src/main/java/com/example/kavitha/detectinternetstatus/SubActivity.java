package com.example.kavitha.detectinternetstatus;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity implements    ConnectivityReceiver.ConnectivityReceiverListener{

    private String TAG =  SubActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        checkConnection();

    }

    public void checkConnection(){
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnackBar(isConnected);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Log.d(TAG , "onNetworkConnectionChanged" +isConnected);
        showSnackBar(isConnected);
    }

  private void  showSnackBar(boolean isConnected){
      String message;
      int color;
      if (isConnected) {
          message = "Good! Connected to Internet";
          color = Color.WHITE;
      } else {
          message = "Sorry! Not connected to internet";
          color = Color.RED;
      }

      Log.i("ds","dsf " +message);

      Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout),message,Snackbar.LENGTH_LONG);

      View sbView = snackbar.getView();
      TextView sbTextView =  (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
      sbTextView.setTextColor(color);
      snackbar.show();


  }
}
