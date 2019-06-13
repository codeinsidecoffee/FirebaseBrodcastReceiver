package com.mrlonewolfer.firebasebrodcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
//            String action = intent.getAction();
//
//            Toast.makeText(context, action, Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
