package com.py.news1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.Userviewholder> {


    private Context cntxt;
    private List<newsq> newsList;
    public newsAdapter(Context applicationContext, List<newsq> newsLsit) {
        this.cntxt = applicationContext;
        this.newsList = newsLsit;
    }


    @NonNull
    @Override
    public newsAdapter.Userviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(cntxt).inflate(R.layout.card , viewGroup ,false );
        return  new Userviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final newsAdapter.Userviewholder newViewHolder, int i) {

        final newsq u = newsList.get(i);
        newViewHolder.date.setText(u.date);
        newViewHolder.news.setText(u.headline);
        newViewHolder.brief.setText(u.brief);
        newViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newViewHolder.itemView.getContext(),detailed.class);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("link",u.link);
                newViewHolder.itemView.getContext().startActivity(intent);
            }
        });
        newViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "TOP NEWS");
                intent.putExtra(Intent.EXTRA_TEXT, u.link);
                newViewHolder.button.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public class Userviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {



        TextView news,date,brief;
        ImageView button;


        public Userviewholder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            brief = itemView.findViewById(R.id.brief);
            news = itemView.findViewById(R.id.head);
            button = itemView.findViewById(R.id.share);
            itemView.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {

        }
    }



}
