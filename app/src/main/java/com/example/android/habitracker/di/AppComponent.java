package com.example.android.habitracker.di;

import android.app.Application;

import com.example.android.habitracker.application.HabiTrackerApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(
        modules =
                {
                        AndroidInjectionModule.class,
                        AppModule.class,
                        ActivityBuilderModule.class
                }
)
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(HabiTrackerApp habiTrackerApp);
}
