package com.xianwei.movies.mvp.di.component;

import com.xianwei.movies.mvp.di.module.AppModule;
import com.xianwei.movies.mvp.di.module.NetworkModule;
import com.xianwei.movies.mvp.utils.MyApplication;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by xianweili on 4/19/18.
 */

@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MyApplication app);

    Retrofit.Builder retrofitBuilder();

    OkHttpClient okHttpClient();
}
