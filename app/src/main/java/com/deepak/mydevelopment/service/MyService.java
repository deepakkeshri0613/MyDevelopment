package com.deepak.mydevelopment.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;


/**
 * Created by dsk on 14-Apr-18.
 */

public class MyService extends Service {
    final class MyThreadClass implements Runnable{
        int serviceId,i=0;
        MyThreadClass(int serviceId)
        {
            this.serviceId=serviceId;
        }

        @Override
        public void run() {
            synchronized (this)
            {
                while (i<10)
                {
                    try {
                        wait(1500);
                        ++i;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf(serviceId);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Toast.makeText(this,"Starter Servire",Toast.LENGTH_SHORT).show();
        Thread thread=new Thread(new MyThreadClass(startId));
        thread.start();

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Destroyed Starter Servire",Toast.LENGTH_SHORT).show();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
