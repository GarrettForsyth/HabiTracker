package com.example.android.habitracker.util;

import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnitRunner;

import com.example.android.habitracker.application.HabiTrackerTestApp;

public class HabiTrackerTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return super.newApplication(cl, HabiTrackerTestApp.class.getName(), context);
    }
}
