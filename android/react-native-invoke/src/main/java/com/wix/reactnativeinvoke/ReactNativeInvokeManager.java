package com.wix.reactnativeinvoke;


import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.wix.invoke.MethodInvocation;
import com.wix.reactnativeinvoke.types.TargetExtendMixIn;

import java.util.HashMap;


public class ReactNativeInvokeManager extends ReactContextBaseJavaModule {

    public static final String NAME = "RNInvokeManager";
    ReactApplicationContext reactContext;

    public ReactNativeInvokeManager(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        ContextWrapper.init(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void execute(final ReadableMap params, final Promise promise) {
        UIManagerModule uiManager = reactContext.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            @Override
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                ContextWrapper.setNativeViewHierarchyManager(nativeViewHierarchyManager);
                HashMap paramsMap = ((ReadableNativeMap) params).toHashMap();

                Object invocationResult = MethodInvocation.invoke(paramsMap, TargetExtendMixIn.class);
                promise.resolve(invocationResult);
            }
        });
    }
}