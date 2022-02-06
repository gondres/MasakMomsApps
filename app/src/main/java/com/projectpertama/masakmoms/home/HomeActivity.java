package com.projectpertama.masakmoms.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projectpertama.masakmoms.R;
import com.projectpertama.masakmoms.allMenu.AllMenuActivity;
import com.projectpertama.masakmoms.detail.DetailActivity;
import com.projectpertama.masakmoms.service.API;
import com.projectpertama.masakmoms.service.GetList;
import com.projectpertama.masakmoms.service.RestClient;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    ProgressBar progressBar;
    Button btnAllMenu;

   
    private List<DataMenu> dataMenus;
    private HomeAdapter adapter;
    private HomeAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAllMenu = findViewById(R.id.btnAllMenu);
        rvHome = findViewById(R.id.rv_home);
        progressBar = findViewById(R.id.progressBar);
        fetchData();

        btnAllMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAllMenu = new Intent(getApplicationContext(), AllMenuActivity.class);
                startActivity(goToAllMenu);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    private void fetchData() {

        progressBar.setVisibility(View.VISIBLE);
        RestClient.getService().getList().enqueue(new Callback<GetList>() {
            @Override
            public void onResponse(Call<GetList> call, Response<GetList> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if(response.isSuccessful()){
                    dataMenus = response.body().getResult();
                    adapter = new HomeAdapter(dataMenus,HomeActivity.this,listener);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                    rvHome.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvHome.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onFailure(Call<GetList> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Failed to connect",Toast.LENGTH_LONG).show();
            }
        });
    }

//  
}