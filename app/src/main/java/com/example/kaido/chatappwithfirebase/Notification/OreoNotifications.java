package com.example.kaido.chatappwithfirebase.Notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

public class OreoNotifications extends ContextWrapper {
    private static final String CHANNEL_ID = "com.example.kaido.chatappwithfirebase";
    private static final String CHANNEL_NAME ="chatappwithfirebase";
    private NotificationManager notificationManager;


    public OreoNotifications(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(false);
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManage().createNotificationChannel(notificationChannel);
    }
    public NotificationManager getManage(){
        if (notificationManager == null){
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getOreoNotifications(String tittle, String content, PendingIntent pendingIntent, Uri soundUri, String icon){
        return new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentTitle(tittle)
                .setContentIntent(pendingIntent)
                .setContentText(content)
                .setSound(soundUri)
                .setSmallIcon(Integer.parseInt(icon))
                .setAutoCancel(true);

    }
}
