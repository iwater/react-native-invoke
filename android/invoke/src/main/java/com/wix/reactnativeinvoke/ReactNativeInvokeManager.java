package com.wix.reactnativeinvoke;


import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.wix.invoke.MethodInvocation;


public class ReactNativeInvokeManager extends ReactContextBaseJavaModule {

    public static final String NAME = "RNInvokeManager";

    public ReactNativeInvokeManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void invoke(final ReadableMap params, final Promise promise) {

        Object invocationResult = null;

        try {
            invocationResult = MethodInvocation.invoke("cd");
        } catch (Exception ex) {
            promise.reject(ex);
        }
        promise.resolve(invocationResult);
    }
}