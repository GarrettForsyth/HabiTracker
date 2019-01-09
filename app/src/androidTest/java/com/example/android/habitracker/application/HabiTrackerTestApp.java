package com.example.android.habitracker.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.example.android.habitracker.di.AppInjector;
import com.example.android.habitracker.di.DaggerTestAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * This class is the injection root used by Dagger.
 */
public class HabiTrackerTestApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        Log.d("testtrace", "onCreate: testapp ");

        super.onCreate();
        DaggerTestAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
        AppInjector.init(this);
    }
}
