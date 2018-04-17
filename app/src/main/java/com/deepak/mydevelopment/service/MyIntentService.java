package com.deepak.mydevelopment.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by dsk on 14-Apr-18.
 */

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("myIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this,"Starter Intent Servire",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int i=0;
        synchronized (this)
        {
            while (i<10)
            {
                try {
                    wait(1500);

                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stopSelf();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Destroyed Intent Starter Servire",Toast.LENGTH_SHORT).show();
    }
}
