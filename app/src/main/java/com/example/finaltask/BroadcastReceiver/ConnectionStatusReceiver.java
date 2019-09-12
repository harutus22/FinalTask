package com.example.finaltask.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.finaltask.StaticValues;
import com.example.finaltask.Utils.NetworkUtil;

public class ConnectionStatusReceiver extends BroadcastReceiver {

    private static boolean isConnected = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        String status;
        boolean connected = NetworkUtil.getConnectivityStatusString(context);
        if(!connected) {
            status = StaticValues.INTERNET_DISCONNECTED;
            isConnected = false;
        } else {
            status = StaticValues.INTERNET_CONNECTED;
            isConnected = true;
        }
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }

    public static boolean getConnection(){
        return isConnected;
    }
}
