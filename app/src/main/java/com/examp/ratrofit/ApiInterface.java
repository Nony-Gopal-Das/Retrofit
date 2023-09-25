package com.examp.ratrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("products/3")
    Call<Product_Model> fetchdata();


}
