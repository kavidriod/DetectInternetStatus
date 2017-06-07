package com.example.kavitha.detectinternetstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  ConnectivityReceiver.ConnectivityReceiverListener{


    private Button btnCheck;
    private FloatingActionButton fab;
    private String TAG =  MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnCheck = (Button) findViewById(R.id.btn_check);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        checkConnection();


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SubActivity.class));
            }
        });
    }

    public void checkConnection(){
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnackBar(isConnected);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Log.d(TAG , "onNetworkConnectionChanged" +isConnected);
showSnackBar(isConnected);
    }

    public void showSnackBar(boolean isConnected){
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }


        Snackbar snackbar = Snackbar.make(findViewById(R.id.fab),message,Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView sbTextView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        sbTextView.setTextColor(color);
        snackbar.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }
}
