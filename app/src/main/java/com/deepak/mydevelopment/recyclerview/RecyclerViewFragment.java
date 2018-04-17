package com.deepak.mydevelopment.recyclerview;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import com.deepak.mydevelopment.R;
import com.deepak.mydevelopment.recyclerview.Information;
import com.deepak.mydevelopment.recyclerview.MyAdapter;

import java.util.ArrayList;
import java.util.List;
public class RecyclerViewFragment extends Fragment implements MyAdapter.ClickListener {

    private RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_recycleview,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.recyclerView);
        myAdapter=new MyAdapter(getActivity(),getData());
        myAdapter.setOnClickListener(this);
        // DefaultItemAnimator itemAnimator=new DefaultItemAnimator();
        recyclerView.setAdapter(myAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        view.findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.addItem("new item");
            }
        });
        view.findViewById(R.id.remove_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.removeItem(0);
            }
        });

    }

    public static List<Information> getData()
    {
        List<Information> data=new ArrayList<>();
        String title[]={"deepak","vhgfg","dgfhhg","fdgdfd"};
        for (int i=0;i<15;++i)
        {
            Information current=new Information();
            current.title=title[i%title.length];
            data.add(current);
        }

        return data;
    }

    @Override
    public void ItemClick(View view, int position) {
        Toast.makeText(getActivity(),""+position+"keshri",Toast.LENGTH_SHORT).show();
    }


}
