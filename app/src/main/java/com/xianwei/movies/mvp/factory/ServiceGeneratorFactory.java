package com.xianwei.movies.mvp.factory;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by xianweili on 4/21/18.
 */

public class ServiceGeneratorFactory {

    private Retrofit.Builder retrofitBuilder;
    private OkHttpClient okHttpClient;

    @Inject
    public ServiceGeneratorFactory(Retrofit.Builder retrofitBuilder, OkHttpClient okHttpClient) {
        this.retrofitBuilder = retrofitBuilder;
        this.okHttpClient = okHttpClient;
    }

    public <S> S createService(Class<S> serviceCLass) {
        return retrofitBuilder.build().create(serviceCLass);
    }
}
