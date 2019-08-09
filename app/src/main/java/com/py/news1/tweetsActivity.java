package com.py.news1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class tweetsActivity extends AppCompatActivity {

    ArrayList<String> tweet = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        listView = (ListView)findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter(this, tweet);
        listView.setAdapter(adapter);


        tweet.add("DG ISPR");
        tweet.add("Walter Ladwing III");
        tweet.add("Takshashila Institution");
        tweet.add("Pak China Security Institute");
        tweet.add("Husain Haqqani");
        tweet.add("Admiral Arun Prakash");
        tweet.add("Raza Ahmed Rumi");
        tweet.add("Barkha Dutt");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("msgnm", String.valueOf(position));

                if (position == 0){

                    get("officialdgispr");
                }else if (position == 1){

                    get("walterladwig");
                }else if (position == 2){

                    get("TakshashilaInst");
                }else if (position == 3){

                    get("pcipakchina");
                }else if (position == 4){

                    get("husainhaqqani");
                }else if (position == 5){

                    get("arunp2810");
                }else if (position == 6){

                    get("Razarumi");
                }else if (position == 7){

                    get("BDUTT");
                }


            }
        });
    }

    public  void get(String url){
        Uri uri = Uri.parse("https://twitter.com/"+url); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> tweet;


        MyAdapter(Context c, ArrayList<String> title) {
            super(c, R.layout.row, R.id.name, title);
            this.context = c;
            this.tweet = title;


        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);

            TextView myTitle = row.findViewById(R.id.name);


            // now set our resources on views

            myTitle.setText(tweet.get(position));


            return row;
        }
    }



}
