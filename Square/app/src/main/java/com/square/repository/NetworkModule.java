package com.square.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.square.BuildConfig;
import com.square.gson.JsonRequiredDeserializer;
import com.square.model.Employee;
import com.square.model.EmployeeList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public static String BASE_URL = "https://s3.amazonaws.com";

    @Singleton
    @Provides
    RestApi providesApiService() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Employee.class, new JsonRequiredDeserializer<>())
                        .create();

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return retrofit.create(RestApi.class);
    }
}
