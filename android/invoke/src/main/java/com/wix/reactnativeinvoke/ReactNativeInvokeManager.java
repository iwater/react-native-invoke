package com.wix.reactnativeinvoke;


import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.wix.invoke.MethodInvocation;

import java.util.HashMap;


public class ReactNativeInvokeManager extends ReactContextBaseJavaModule {

    public static final String NAME = "RNInvokeManager";

    public ReactNativeInvokeManager(ReactApplicationContext reactContext) {
        super(reactContext);
        ContextWrapper.init(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void execute(final ReadableMap params, final Promise promise) {
        HashMap paramsMap = ((ReadableNativeMap) params).toHashMap();
        Object invocationResult = MethodInvocation.invoke(paramsMap);
        promise.resolve(invocationResult);
    }
}