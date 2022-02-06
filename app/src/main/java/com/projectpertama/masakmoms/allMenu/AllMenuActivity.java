package com.projectpertama.masakmoms.allMenu;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projectpertama.masakmoms.R;
import com.projectpertama.masakmoms.home.DataMenu;
import com.projectpertama.masakmoms.home.HomeAdapter;
import com.projectpertama.masakmoms.service.GetList;
import com.projectpertama.masakmoms.service.RestClient;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMenuActivity extends AppCompatActivity {

    @BindView(R.id.rvAllMenu)
    RecyclerView rvAllMenu;
    ProgressBar progressBar;

   
    private List<DataMenu> dataMenus;
    private AllMenuAdapter adapter;
    private AllMenuAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu);

        rvAllMenu = findViewById(R.id.rvAllMenu);
        progressBar = findViewById(R.id.progBarAllMenu);
        fetchData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    private void fetchData() {

        progressBar.setVisibility(View.VISIBLE);
        RestClient.getService().getPage().enqueue(new Callback<GetList>() {
            @Override
            public void onResponse(Call<GetList> call, Response<GetList> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if(response.isSuccessful()){
                    dataMenus = response.body().getResult();
                    adapter = new AllMenuAdapter(dataMenus, AllMenuActivity.this,listener);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                    rvAllMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvAllMenu.setAdapter(adapter);
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