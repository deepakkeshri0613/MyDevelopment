package com.deepak.mydevelopment.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.deepak.mydevelopment.R;

import java.util.ArrayList;
import static com.deepak.mydevelopment.permission.PermissionUtil.REQUEST_CAMERA;
import static com.deepak.mydevelopment.permission.PermissionUtil.REQUEST_GROUP_PERMISSION;
import static com.deepak.mydevelopment.permission.PermissionUtil.REQUEST_READ;
import static com.deepak.mydevelopment.permission.PermissionUtil.REQUEST_WRITE;
import static com.deepak.mydevelopment.permission.PermissionUtil.TXT_CAMERA;
import static com.deepak.mydevelopment.permission.PermissionUtil.TXT_READ;
import static com.deepak.mydevelopment.permission.PermissionUtil.TXT_WRITE;


public class PermissionFragment extends Fragment{

    private PermissionUtil permissionUtil;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        permissionUtil=new PermissionUtil(getContext(),getActivity());
        return inflater.inflate(R.layout.fragment_permission,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera();
            }
        });
        view.findViewById(R.id.write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write();
            }
        });
        view.findViewById(R.id.read_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readContact();
            }
        });
        view.findViewById(R.id.all_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allPermission();
            }
        });
    }

    public void camera() {

        if(permissionUtil.checkPermission(TXT_CAMERA)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.CAMERA))
            {
                permissionUtil.showExplanation(TXT_CAMERA);
            }
            else if(!permissionUtil.checkPermissionPreferences("camera"))
            {
                permissionUtil.requestPermission(TXT_CAMERA);
                permissionUtil.updatePermissionPreferances("camera");
            }
            else
            {
                Toast.makeText(getActivity(),"Please Allow the camera permission",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",getActivity().getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        }
        else
        {
            /*Toast.makeText(this,"You have camera permisson ",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,ResultActivity.class);
            intent.putExtra("message","Take Pic Or record videos");
            startActivity(intent);*/

        }



    }






    public void write() {

        if(permissionUtil.checkPermission(TXT_WRITE)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                permissionUtil.showExplanation(TXT_WRITE);
            }
            else if(!permissionUtil.checkPermissionPreferences("write"))
            {
                permissionUtil.requestPermission(TXT_WRITE);
                permissionUtil.updatePermissionPreferances("write");
            }
            else
            {
                Toast.makeText(getActivity(),"Please Allow the Write permission",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",getActivity().getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        }
        else
        {
           /* Toast.makeText(this,"You have Write permisson ",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,ResultActivity.class);
            intent.putExtra("message","Store Data");
            startActivity(intent);*/

        }


    }

    public void readContact() {

        if(permissionUtil.checkPermission(TXT_READ)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_CONTACTS))
            {
                permissionUtil.showExplanation(TXT_READ);
            }
            else if(!permissionUtil.checkPermissionPreferences("read"))
            {
                permissionUtil.requestPermission(TXT_READ);
                permissionUtil.updatePermissionPreferances("read");
            }
            else
            {
                Toast.makeText(getActivity(),"Please Allow the Read permission",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",getActivity().getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        }
        else
        {
           /* Toast.makeText(this,"You have Read permisson ",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,ResultActivity.class);
            intent.putExtra("message","Read contact from contact and take sms");
            startActivity(intent);*/

        }



    }

    public void allPermission() {

        ArrayList<String> permissionAvialable=new ArrayList<>();
        ArrayList<String> permissionNeeded=new ArrayList<>();
        permissionAvialable.add(Manifest.permission.READ_CONTACTS);
        permissionAvialable.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionAvialable.add(Manifest.permission.CAMERA);

        for(String permission: permissionAvialable)
        {
            if(ContextCompat.checkSelfPermission(getActivity(),permission)!=PackageManager.PERMISSION_GRANTED)
                permissionNeeded.add(permission);

        }
        permissionUtil.requestGroupPermission(permissionNeeded);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_CAMERA:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                   /* Toast.makeText(this,"camera permission allowed",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this,ResultActivity.class);
                    intent.putExtra("message","you can now take photo");
                    startActivity(intent);*/
                }
                else
                {
                    Toast.makeText(getActivity(),"camera permission not allowed",Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_READ:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                   /* Toast.makeText(this,"Read permission allowed",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this,ResultActivity.class);
                    intent.putExtra("message","you can now read");
                    startActivity(intent);*/
                }
                else
                {
                    Toast.makeText(getActivity(),"Read permission not allowed",Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_WRITE:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                   /* Toast.makeText(this,"write permission allowed",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this,ResultActivity.class);
                    intent.putExtra("message","you can now write data");
                    startActivity(intent);*/
                }
                else
                {
                    Toast.makeText(getActivity(),"write permission not allowed",Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_GROUP_PERMISSION:
                String result="";
                int i=0;
                for(String perm :permissions) {

                    String status="";

                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED)
                            status="GRANTED";
                    else
                        status="DENIED";
                    result=result+"\n"+perm+" :"+status;
                    i++;

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Group Permission");
                builder.setMessage(result);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();

                break;
        }
    }


}
