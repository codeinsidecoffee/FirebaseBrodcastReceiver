package com.mrlonewolfer.firebasebrodcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "ch_01";
    int count=0;
    Button btnNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotification=findViewById(R.id.btnnotification);
        BroadcastReceiver broadcastReceiver=new MyReceiver();
        IntentFilter filter= new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

btnNotification.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showNotification();
    }
});

        //below line used to register your action in brodcast reciver
//        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(broadcastReceiver,filter);
    }
        private void showNotification(){

            NotificationCompat.Builder builder= new NotificationCompat.Builder(this,CHANNEL_ID);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Title")
                    .setContentText("Content")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            CharSequence name = "My Channel";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importance);
                notificationChannel.setDescription(description);

                notificationManager.createNotificationChannel(notificationChannel);
            }
            Notification notification = builder.build();
            notificationManager.notify(count++,notification);
        }
}
