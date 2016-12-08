package com.example.andriod.retrofitrecycler;

import android.app.DownloadManager;
import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.github.com/";

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;

    private UserAdapter mAdapter;

    List<User.ItemsBean> users;

    private RecyclerView.LayoutManager mLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //we have the recycler view reference
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_content);

        //okHttp reference
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("Accept", "Application/JSON").build();
                                return chain.proceed(request);
                            }
                        }
                ).build();

        //retrofit reference
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //MyEnd point
        MyApiEndPointInterface service = retrofit.create(MyApiEndPointInterface.class);

        //My callbacks
        retrofit2.Call<User> call = service.getUserNamedTom("anvesh");
        call.enqueue(
                new Callback<User>() {
                    @Override
                    public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {
                        Log.d(TAG, response.code() + "");
                        if(response.isSuccessful()){
                            users = new ArrayList<User.ItemsBean>();

                            User result = response.body();
                            users = result.getItems();
                            mAdapter = new UserAdapter(users);

                            //Attach adapter to Recycle Viewer
                            mLayoutManger = new LinearLayoutManager(getApplicationContext());
                            mRecyclerView.setLayoutManager(mLayoutManger);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<User> call, Throwable t) {

                    }
                }
        );
    }
}
