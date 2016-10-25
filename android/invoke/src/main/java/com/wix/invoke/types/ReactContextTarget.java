package com.wix.invoke.types;

import com.facebook.react.bridge.ReactApplicationContext;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wix.reactnativeinvoke.ContextWrapper;

/**
 * Created by rotemm on 25/10/2016.
 */
public abstract class ReactContextTarget extends Target {

    protected ReactApplicationContext context;

    public ReactContextTarget(@JsonProperty("value") Object value) {
        super(value);
        this.context = ContextWrapper.getReactApplicationContext();
    }
}
