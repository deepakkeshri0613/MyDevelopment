package com.deepak.mydevelopment.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.TelephonyManager;

import com.deepak.mydevelopment.R;

/**
 * Created by dsk on 13-Apr-18.
 */

public class BroadCastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            String number=intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveNumber(number,database);
            dbHelper.close();
            NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"channal")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Incomming Call")
                    .setContentText(number)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(1,builder.build());
        }



        Intent intent1=new Intent(DbContract.UPDATE_UI_FILTER);
        context.sendBroadcast(intent1);
    }
}
