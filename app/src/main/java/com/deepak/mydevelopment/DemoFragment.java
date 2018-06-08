package com.deepak.mydevelopment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.deepak.mydevelopment.recyclerview.MyAdapter;
import com.deepak.mydevelopment.recyclerview.RecyclerViewFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {


    RecyclerView recyclerView;
    MyAdapter adapter;

    TouchListner touchListner;
    public DemoFragment() {



    }


    public void setTouchListner(TouchListner touchListner)
    {
        this.touchListner=touchListner;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        return inflater.inflate(R.layout.fragment_demo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MyAdapter(getContext(), RecyclerViewFragment.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(touchListner!=null)
                    touchListner.onTouch();
                return false;
            }
        });
    }

    public interface TouchListner
    {
        void onTouch();
    }

}
