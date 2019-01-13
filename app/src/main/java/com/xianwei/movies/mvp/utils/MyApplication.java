package com.xianwei.movies.mvp.utils;

import android.app.Application;

import com.xianwei.movies.BuildConfig;
import com.xianwei.movies.mvp.di.component.AppComponent;
import com.xianwei.movies.mvp.di.component.DaggerAppComponent;
import com.xianwei.movies.mvp.di.module.AppModule;
import com.xianwei.movies.mvp.di.module.NetworkModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by xianweili on 4/19/18.
 */

public class MyApplication extends Application {

    private static MyApplication sInstance = null;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        createAppComponent();
        initialRealm();
    }

    private void initialRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void createAppComponent() {
        appComponent = getAppComponentBuilder().build();
    }

    private DaggerAppComponent.Builder getAppComponentBuilder() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.HOST));
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
