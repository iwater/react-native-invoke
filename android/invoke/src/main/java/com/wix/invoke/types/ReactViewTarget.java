package com.wix.invoke.types;

import android.view.View;

import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wix.invoke.MethodUtilsExt;

import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * Created by rotemm on 25/10/2016.
 */

public class ReactViewTarget extends ReactContextTarget {

    View view;

    @JsonCreator
    public ReactViewTarget(@JsonProperty("value") final Object value) {
        super(value);
    }

    @Override
    public Object execute(Invocation invocation) throws Exception {
        getView();
        return MethodUtils.invokeExactMethod(this.view, invocation.getMethod(), invocation.getArgs());
    }

    private void getView() throws Exception {
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        UIViewOperationQueue operationQ = (UIViewOperationQueue) MethodUtilsExt.invokeMethodEvenIfInaccessible(uiManager.getUIImplementation(), "getUIViewOperationQueue");
        NativeViewHierarchyManager nvhm = (NativeViewHierarchyManager) MethodUtilsExt.invokeMethodEvenIfInaccessible(operationQ, "getNativeViewHierarchyManager");
        view = nvhm.resolveView(((Double)value).intValue());
    }
}
