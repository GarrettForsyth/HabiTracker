package com.example.android.habitracker.di;

import android.app.Application;

import com.example.android.habitracker.application.HabiTrackerTestApp;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, TestAppModule.class })
public interface TestAppComponent extends AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        TestAppComponent build();
    }

    void inject(HabiTrackerTestApp habiTrackerTestApp);
}
