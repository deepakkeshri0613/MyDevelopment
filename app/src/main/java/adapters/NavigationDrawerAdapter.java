package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deepak.mydevelopment.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dsk on 08-Apr-18.
 */

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.PageViewHolder> {
    LayoutInflater inflater;
    ClickListener clickListener;
    List<String> data= Collections.emptyList();
    public NavigationDrawerAdapter(Context context,List<String> data)
    {
        this.data=data;
        inflater=LayoutInflater.from(context);
    }
    public void setOnClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }
    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.menu_item_layout,parent,false);
        PageViewHolder pageViewHolder=new PageViewHolder(view);
        return pageViewHolder;
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {
        holder.menuItemName.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView menuItemName;

        public PageViewHolder(View itemView) {
            super(itemView);
            menuItemName=itemView.findViewById(R.id.menu_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener!=null)
            {
                clickListener.onMenuItemClick(getAdapterPosition());
            }
        }
    }

    public interface ClickListener{
        void onMenuItemClick(int position);
    }
}
