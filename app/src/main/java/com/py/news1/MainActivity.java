package com.py.news1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        newsLsit = new ArrayList<>();

        newsadapter = new newsAdapter(getApplicationContext(),newsLsit);

        recyclerView.setAdapter(newsadapter);

        fetchData process = new fetchData();

        process.execute();

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
            dialog.setMessage("Getting Data, please wait.");
            dialog.show();
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
