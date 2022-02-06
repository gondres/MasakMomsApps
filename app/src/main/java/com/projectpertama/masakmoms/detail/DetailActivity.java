package com.projectpertama.masakmoms.detail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.projectpertama.masakmoms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    String title ="",imgUrl="",key="",langkahLabel="",bahanLabel="";
    String buka = ",";
    TextView tvTitle, tvAuthor, tvDate, tvMinute, tvDeskLabel, tvBahanLabel, tvStepLabel;
    ImageView imgMenu;
    ProgressBar proggBar;
    private String apiURL = "https://masak-apa.tomorisakura.vercel.app/api/recipe/:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_2);

        initialize();
//        fetchDetail();

        fetchData();

        //Get Data From Clicked RecycleView
        key = getIntent().getStringExtra("keyName");
        title = getIntent().getStringExtra("title");
        imgUrl = getIntent().getStringExtra("image");
        tvTitle.setText(title);

        Glide.with(this)
                .asBitmap()
                .load(imgUrl)
                .into(imgMenu);



    }

    private void fetchData() {
        getHomeAsyncTask getHomeAsynTask = new getHomeAsyncTask();
        getHomeAsynTask.execute();
//        checkingStrings();
    }

    public class getHomeAsyncTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            proggBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try{
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(apiURL+key);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1){
                        current += (char) data;
                        data = isw.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection!=null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            proggBar.setVisibility(View.INVISIBLE);

            try{
                //get "results" Object in API
                JSONObject object = new JSONObject(s);
                JSONObject results = object.getJSONObject("results"); //Array Object
                String time = results.getString("times"); //Object

                //get "ingredient" Object Arrays in API
                List<String> bahanList = new ArrayList<String>();
                JSONArray bahanArray = results.getJSONArray("ingredient");
                for(int i = 0;i<bahanArray.length();i++){
                    bahanList.add(bahanArray.getString(i));
                }
                //get "step" Object Arrays in API
                List<String> langkahList = new ArrayList<String>();
                JSONArray langkahArray = results.getJSONArray("step");
                for(int i = 0;i<langkahArray.length();i++){
                    langkahList.add(langkahArray.getString(i));

                }
                bahanLabel = bahanList.toString();
                langkahLabel = langkahList.toString();


                //get "author" Arrays in API
                JSONObject authorObj = results.getJSONObject("author"); //Array Object
                String date = authorObj.getString("datePublished"); //Object
                String author = authorObj.getString("user"); //Object

                String desc = results.getString("desc");

                tvAuthor.setText(author);
                tvDate.setText(date);
                tvMinute.setText(time);
                tvDeskLabel.setText(desc);
                tvBahanLabel.setText(bahanLabel);
                tvStepLabel.setText(langkahLabel);


                if(bahanLabel.contains(",")){
                    bahanLabel=bahanLabel.replaceAll(buka,"\r");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void checkingStrings() {

    }
//    private void fetchDetail() {
//        proggBar.setVisibility(View.VISIBLE);
//       RestClient.getService().getDetail().enqueue(new Callback<GetDetailList>() {
//           @Override
//           public void onResponse(Call<GetDetailList> call, Response<GetDetailList> response) {
//               proggBar.setVisibility(View.INVISIBLE);
//               if(response.isSuccessful()){
//                   proggBar.setVisibility(View.INVISIBLE);
//                   dataDetail = response.body().getResults();
//                   for (int i=0;i<dataDetail.size();i++){
//                       Log.v("MainActivity",dataDetail.get(i).getIngredient());
//                       tvBahan.setText(dataDetail.get(i).getIngredient());
//                   }
//               }else{
//                   proggBar.setVisibility(View.VISIBLE);
//                   Log.d("Failed","Failed fetch data");
//               }
//           }
//
//           @Override
//           public void onFailure(Call<GetDetailList> call, Throwable t) {
//                Log.v("Failed",t.toString());
//           }
//       });
//    }

    private void initialize() {

        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvDate = findViewById(R.id.tvDate);
        tvMinute = findViewById(R.id.tvTime);
        imgMenu = findViewById(R.id.ivFood);
        tvDeskLabel = findViewById(R.id.tvDeksripsiLabel);
        tvBahanLabel = findViewById(R.id.tvBahanLabel);
        tvStepLabel = findViewById(R.id.tvStepLabel);
        proggBar = findViewById(R.id.proggBar);
    }
}