package com.metacodersbd.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class notificationBackgroudService  extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do your jobs here
        Intent service = new Intent(getApplicationContext(), homePage.class);
        startService(service);
        return super.onStartCommand(intent, flags, startId);
    }
}
