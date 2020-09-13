package com.pof.jettravel.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.pof.jettravel.data.model.model.Article;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static Repository repository;
    private RetrofitService retrofitService;
    private String TAG = Repository.class.getSimpleName();

    private Repository(){

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        //initialise retrofit and instantiate RetrofitService
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BaseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        retrofitService = retrofit.create(RetrofitService.class);
    }

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }

    //Singleton instance
    public synchronized static Repository getInstance() {
        if(repository == null) {
            repository = new Repository();
        }
        return repository;
    }


    public LiveData<List<Article>> getArticles() {
        final MutableLiveData<List<Article>> liveData = new MutableLiveData<>();

        retrofitService.getArticles("1","10").enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.d(TAG, t.getStackTrace().toString());
                liveData.setValue(null);
            }
        });
        return liveData;
    }

}
