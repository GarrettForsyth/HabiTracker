package com.example.android.habitracker.di;

import com.example.android.habitracker.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = { FragmentBuildersModule.class })
    abstract MainActivity contributeActivityInjector();

}
