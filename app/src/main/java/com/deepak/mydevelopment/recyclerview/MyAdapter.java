package com.deepak.mydevelopment.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deepak.mydevelopment.AnimationUtil;
import com.deepak.mydevelopment.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by dsk on 28-Feb-18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    boolean addOrRemoveItemClicked=false;
    public ClickListener clickListener;
    List<Information> data= Collections.emptyList();

    private LayoutInflater inflater;

    private int previousPosition=0;
    public MyAdapter(Context context,List<Information> data)
    {
      inflater = LayoutInflater.from(context);
      this.data=data;
      this.context=context;
    }


   public void setOnClickListener(ClickListener clickListener)
   {
       this.clickListener=clickListener;
   }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.custom_row,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information currentObject=data.get(position);
        holder.textView.setText(currentObject.title);
if(!addOrRemoveItemClicked) {
    if (position > previousPosition) {
        AnimationUtil.animation(holder, true);
    } else {
        AnimationUtil.animation(holder, false);

    }
    previousPosition = position;

}
else {
    addOrRemoveItemClicked=false;
    previousPosition = position;

}

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView textView;


        public MyViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView=itemView.findViewById(R.id.list_title);


        }


        @Override
        public void onClick(View view) {
            if(clickListener!=null)
            {
                clickListener.ItemClick(view , getAdapterPosition());
            }
        }
    }
    public interface ClickListener{
        public void ItemClick(View view,int position);

    }

    public void removeItem(int position)
    {
        if(data.size()!=0) {
            addOrRemoveItemClicked=true;
            data.remove(position);
            notifyItemRemoved(position);
        }
        else {
            Toast.makeText(context,"No item to remove",Toast.LENGTH_SHORT).show();
        }
    }
    public void addItem(String title)
    {
        addOrRemoveItemClicked=true;
        Information item=new Information();
        item.title=title;
        data.add(item);
        notifyItemInserted(data.size());
    }
}
