package com.deepak.mydevelopment.service;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.deepak.mydevelopment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment {

    private Button startServiceButton,stopServiceButton,startIntentServiceButton,stopIntentServiceButton
            ,startBoundServiceButton,stopBoundServiceButton;


    public static ServiceFragment createFragment()
    {
        return new ServiceFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startServiceButton=view.findViewById(R.id.start_service_but);
        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MyService.class);
                getActivity().startService(intent);
            }
        });
        stopServiceButton=view.findViewById(R.id.stop_service_but);
        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MyService.class);
                getActivity().stopService(intent);

            }
        });
        startIntentServiceButton=view.findViewById(R.id.start_intent_service_but);
        startIntentServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MyIntentService.class);
                getActivity().startService(intent);
            }
        });

        stopIntentServiceButton=view.findViewById(R.id.stop_intent_service_but);
        stopIntentServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MyIntentService.class);
                getActivity().stopService(intent);
            }
        });
        startBoundServiceButton=view.findViewById(R.id.start_bound_service_but);
        startBoundServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        stopBoundServiceButton=view.findViewById(R.id.stop_bound_service_but);
        stopBoundServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
