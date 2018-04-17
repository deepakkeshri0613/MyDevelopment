package com.deepak.mydevelopment.permission;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Switch;
import android.widget.TextView;

import com.deepak.mydevelopment.R;

import java.util.ArrayList;




/**
 * Created by dsk on 27-Dec-17.
 */

public class PermissionUtil {


    public static final int REQUEST_CAMERA=125;
    public static final int REQUEST_READ=225;
    public static final int REQUEST_WRITE=325;
    public static final int REQUEST_GROUP_PERMISSION=425;
    public static final int TXT_CAMERA=1;
    public static final int TXT_READ=2;
    public static final int TXT_WRITE=3;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Activity activity;
    PermissionUtil(Context context,Activity activity)
    {
        this.context= context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.permission_preference),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        this.activity=activity;


    }


    void requestPermission(int permission)
    {

        switch (permission)
        {
            case TXT_CAMERA :
                ActivityCompat.requestPermissions(activity,new String[] {Manifest.permission.CAMERA},REQUEST_CAMERA);
                break;
            case TXT_READ :
                ActivityCompat.requestPermissions(activity,new String[] {Manifest.permission.READ_CONTACTS},REQUEST_READ);
                break;
            case TXT_WRITE:
                ActivityCompat.requestPermissions(activity,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE);
                break;
        }
    }

    int checkPermission(int permission)
    {
        int status= PackageManager.PERMISSION_DENIED;
        switch (permission)
        {
            case TXT_CAMERA :
                status= ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
                break;
            case TXT_READ :
                status=ContextCompat.checkSelfPermission(activity,Manifest.permission.READ_CONTACTS);
                break;
            case TXT_WRITE:
                status=ContextCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;

        }
        return status;
    }

    void showExplanation(final int permission)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);

        if(permission==TXT_CAMERA)
        {

            builder.setMessage("Need camera for scanning");
            builder.setTitle("Camera Needed ");

        }
        if(permission==TXT_READ)
        {
            builder.setMessage("Need contact for sms");
            builder.setTitle("Read Needed ");

        }
        if(permission==TXT_WRITE)
        {
            builder.setMessage("Need write for data entery");
            builder.setTitle("Write permission Needed ");

        }
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(permission==TXT_CAMERA)
                {
                    requestPermission(TXT_CAMERA);
                }
                else if(permission==TXT_READ)
                {
                    requestPermission(TXT_READ);
                }
                else if(permission==TXT_WRITE)
                {
                    requestPermission(TXT_WRITE);
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();;


    }

    public void requestGroupPermission(ArrayList<String> permissions)
    {

        String [] permissionList=new String[permissions.size()];
        permissions.toArray(permissionList);
        ActivityCompat.requestPermissions(activity,permissionList,REQUEST_GROUP_PERMISSION);
    }
//******************************************************************************

    public void updatePermissionPreferances(String permission)
    {
        switch (permission)
        {
            case "camera":
                editor.putBoolean(context.getString(R.string.permission_camera),true);
                editor.commit();
                break;
            case "read":
                editor.putBoolean(context.getString(R.string.permission_read),true);
                editor.commit();
                break;
            case "write":
                editor.putBoolean(context.getString(R.string.permission_write),true);
                editor.commit();
                break;

        }

    }


    public boolean checkPermissionPreferences(String permission)
    {
        boolean isShown=false;
        switch(permission)
        {
            case "camera":
                isShown=sharedPreferences.getBoolean(context.getString(R.string.permission_camera),false);

                break;
            case "read":
                isShown=sharedPreferences.getBoolean(context.getString(R.string.permission_read),false);
                break;
            case "write":
                isShown=sharedPreferences.getBoolean(context.getString(R.string.permission_write),false);
                break;

        }
        return isShown;
    }


    //***************************************************************///




}
