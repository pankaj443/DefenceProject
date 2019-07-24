package com.py.news1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<newsq> newsLsit;
    public  static TextView data;
    private newsAdapter newsadapter;
    public Intent intent1;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.refresh);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsLsit.clear();
                Toast.makeText(MainActivity.this, "Data Refreshed!", Toast.LENGTH_SHORT).show();

                fetchData process = new fetchData();

                process.execute();
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        newsLsit = new ArrayList<>();

        newsadapter = new newsAdapter(getApplicationContext(),newsLsit);

        recyclerView.setAdapter(newsadapter);

        fetchData process = new fetchData();

        process.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menufile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.tweets)
        {
            Toast.makeText(this,"tweets",Toast.LENGTH_SHORT).show();
            intent1 = new Intent(getApplicationContext(),tweetsActivity.class);
            startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    public class fetchData extends AsyncTask<Void,Void,Void> {
        private ProgressDialog dialog;
        String data ="";
        String dataParsed = "";
        String singleParsed ="";
        public fetchData() {
            dialog = new ProgressDialog(MainActivity.this);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://raw.githubusercontent.com/trialtrial785/chacha-chaudhary/master/news.json");

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                    Log.i("MSGNM", data);
                }

                JSONArray JA = new JSONArray(data);
                for(int i =0 ;i <JA.length(); i++){
                    JSONObject JO = (JSONObject) JA.get(i);


                    String date = (String) JO.get("date");
                    String headline = (String) JO.get("headline");
                    String link = (String) JO.get("link");
                    String brief = (String) JO.get("detailed_news").toString();
                    String brief1 =  brief.replace("[","");
                    String brief2 =  brief1.replace("]","");
                    String brief3 =  brief2.replace("\"","");

                    //String detailed_news = (String) JO.get("detailed_news");
                    String detailed_news = "";
                    newsq u = new newsq(headline,link,date,brief3,detailed_news);
                    newsLsit.add(u);


                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            newsadapter.notifyDataSetChanged();

        }
    }
}
