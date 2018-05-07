package com.deepak.mydevelopment.AlertDialog;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepak.mydevelopment.R;

import utility.MyAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertDialogFragment extends Fragment {


    MyAlertDialog myAlertDialog;
    public AlertDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myAlertDialog=new MyAlertDialog(getActivity());



        return inflater.inflate(R.layout.fragment_alert_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myAlertDialog.setPositiveButton("ok");
        myAlertDialog.setNegativeButton("Cancel");
        myAlertDialog.showAlertDialog();
    }
}
