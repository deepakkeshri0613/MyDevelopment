package com.deepak.mydevelopment.broadcastreciver;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deepak.mydevelopment.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by dsk on 13-Apr-18.
 */

public class BroadcastAdapter extends RecyclerView.Adapter<BroadcastAdapter.PageViewHolder> {
    LayoutInflater inflater;
    ArrayList<ContactDetail> data=new ArrayList<>();
    public BroadcastAdapter(Context context,ArrayList<ContactDetail> data)
    {
        this.data=data;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.broadcast_mobile_no,parent,false);
        PageViewHolder pageViewHolder=new PageViewHolder(view);
        return pageViewHolder;
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {

        holder.mob.setText(data.get(position).getMob());
        holder.id.setText(data.get(position).getId()+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PageViewHolder extends RecyclerView.ViewHolder{

        TextView mob,id;
        public PageViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.broadCast_srno);
            mob=itemView.findViewById(R.id.broadCast_mob);
        }
    }
}
