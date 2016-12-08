package com.example.andriod.retrofitrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by atummala on 12/8/2016.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User.ItemsBean> itemsBeans;

     public  UserAdapter(List<User.ItemsBean> itemsBeans){
         this.itemsBeans = itemsBeans;
     }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_row_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.title.setText(itemsBeans.get(position).getAvatar_url());
        holder.score.setText(itemsBeans.get(position).getScore()+ "");
    }

    @Override
    public int getItemCount() {
        return itemsBeans.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title, score;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            score = (TextView) itemView.findViewById(R.id.tv_count);
        }
    }
}
