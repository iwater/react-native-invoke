package com.wix.invoke.types;

import android.view.View;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wix.invoke.MethodUtilsExt;
import com.wix.reactnativeinvoke.ContextWrapper;

/**
 * Created by rotemm on 25/10/2016.
 */

public class ReactViewTarget extends ReactContextTarget {

    @JsonCreator
    public ReactViewTarget(@JsonProperty("value") final Object value) {
        super(value);
    }

    @Override
    public Object execute(Invocation invocation) throws Exception {
        View view = ContextWrapper.getNativeViewHierarchyManager().resolveView(((Double)value).intValue());
        return MethodUtilsExt.invokeExactMethodNoAutobox(view, invocation.getMethod(), invocation.getArgs());
    }
}
