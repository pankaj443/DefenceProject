package com.py.news1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


                Intent intent = new Intent(getApplicationContext(),t.class);

                startActivity(intent);
                // Log.i("MSGNM", url);
            }
        });
    }
}
