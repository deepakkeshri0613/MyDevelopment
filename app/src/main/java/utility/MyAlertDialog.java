package utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class MyAlertDialog {

    Context context;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    public AlertDialog.Builder getAlertDialogBuilder() {
        return builder;
    }
    public MyAlertDialog(Context context)
    {
        this.context=context;
         builder=new AlertDialog.Builder(context);
        builder.setMessage("Really Want To Logout ?");
        builder.setTitle("AlertDialog");

    }

    public void showAlertDialog()
    {
        alertDialog=builder.create();
        alertDialog.show();

    }

    public void setPositiveButton(String buttonName)
    {
        builder.setPositiveButton(buttonName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context,"Logout",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setNegativeButton(String buttonName)
    {
        builder.setNegativeButton(buttonName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

    }

}
