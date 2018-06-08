package com.deepak.mydevelopment.broadcastreciver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deepak.mydevelopment.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BroadCastFragment extends Fragment {
    ArrayList<ContactDetail> data=new ArrayList<>();
    BroadcastAdapter adapter;
    RecyclerView recyclerView;
    TextView textView;
    BroadcastReceiver broadcastReceiver;
    public static Fragment createFragment()
    {
        return new BroadCastFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        adapter=new BroadcastAdapter(getActivity(),data);
        return inflater.inflate(R.layout.fragment_broad_cast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=view.findViewById(R.id.broadCastTextView_id);
        recyclerView=view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        readFromDatabase();
        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                readFromDatabase();
            }
        };
    }


    private void readFromDatabase()
    {
        data.clear();
        DbHelper dbHelper=new DbHelper(getActivity());
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Cursor cursor=dbHelper.readNumber(database);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                String number;
                int id;
                number=cursor.getString(cursor.getColumnIndex(DbContract.INCOMING_NUMBER));
                id=cursor.getInt(cursor.getColumnIndex("id"));
                data.add(new ContactDetail(id,number));
            }
            cursor.close();
            dbHelper.close();
            adapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(broadcastReceiver,new IntentFilter(DbContract.UPDATE_UI_FILTER));
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(broadcastReceiver);
    }
}
