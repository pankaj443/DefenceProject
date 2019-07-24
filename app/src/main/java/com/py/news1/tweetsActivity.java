package com.py.news1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        tweet.add("DG ISPR");
        tweet.add("Walter Ladwing III");
        tweet.add("Takshashila Institution");
        tweet.add("Pak China Security Institute");
        tweet.add("Husain Haqqani");
        tweet.add("Admiral Arun Prakash");
        tweet.add("Raza Ahmed Rumi");
        tweet.add("Barkha Dutt");
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,tweet);
        listView.setAdapter(arrayAdapter);

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
}
