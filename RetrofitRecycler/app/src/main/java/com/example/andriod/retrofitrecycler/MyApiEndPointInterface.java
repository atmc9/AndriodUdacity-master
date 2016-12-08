package com.example.andriod.retrofitrecycler;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by atummala on 12/8/2016.
 */

public interface MyApiEndPointInterface {
    @GET("search/users")
    Call<User> getUserNamedTom(@Query("q") String name);
}
