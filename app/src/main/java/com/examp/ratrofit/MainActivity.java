package com.examp.ratrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView id, titlee, desk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of views
        id = findViewById(R.id.productid);
        titlee = findViewById(R.id.procutctitlee);
        desk = findViewById(R.id.productdetails);

//retrofit

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//interface

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

//change model namee and medhod you named on interface class
        Call<Product_Model> call = apiInterface.fetchdata();


        call.enqueue(new Callback<Product_Model>() {
            @Override
            public void onResponse(Call<Product_Model> call, Response<Product_Model> response) {

                id.setText(String.valueOf(response.body().getId()));
                titlee.setText(response.body().getTitle());
                desk.setText(response.body().getDescription());
            }

            @Override
            public void onFailure(Call<Product_Model> call, Throwable t) {
//handle when failer
                Toast.makeText(MainActivity.this, "Failed to load data from server" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}