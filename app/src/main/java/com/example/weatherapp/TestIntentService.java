package com.example.weatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestIntentService extends IntentService {

    public static final String INTENT_SERVICE = "IntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TestIntentService() {
        super("Test");
//        Log.i(INTENT_SERVICE, "TestIntentService");
        Log.i(INTENT_SERVICE, Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        Log.i(INTENT_SERVICE, "onHandleIntent");
        Log.i(INTENT_SERVICE, Thread.currentThread().getName());

    }
}
