package com.wix.reactnativeinvoke;

import com.facebook.react.bridge.ReactApplicationContext;

import java.lang.ref.WeakReference;

/**
 * Created by rotemm on 25/10/2016.
 */

public class ContextWrapper {

    private static WeakReference<ReactApplicationContext> sReactContext;

    public static void init(ReactApplicationContext reactContext) {
        sReactContext = new WeakReference<>(reactContext);
    }

    public static ReactApplicationContext getReactApplicationContext() {
        if (sReactContext == null) {
            throw new RuntimeException("ContextWrapper needs to be initialized using init(context)");
        }
        return sReactContext.get();
    }
}
