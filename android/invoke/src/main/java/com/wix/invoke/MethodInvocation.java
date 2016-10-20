package com.wix.invoke;

import com.wix.invoke.exceptions.EmptyInvocationInstructionException;
import com.wix.invoke.parser.JsonParser;
import com.wix.invoke.types.Invocation;
import com.wix.invoke.types.Target;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;


/**
 * Created by rotemm on 10/10/2016.
 */
public class MethodInvocation {

    public static Object invokeOnObject(Object map, Object object) {
        Invocation invocation = JsonParser.parse(map, Invocation.class);
//        (invocation.getTarget().getValue().toString())
        if (invocation.getTarget().getType() == Target.Type.ObjectInstance) {
            invocation.setTarget(new Target(Target.Type.ObjectInstance, object));
        }

        return invoke(invocation);
    }

    public static Object invoke(Object map) {
        Invocation invocation = JsonParser.parse(map, Invocation.class);
        return invoke(invocation);
    }

    public static Object invoke(String invocationJson) {
        Invocation invocation = JsonParser.parse(invocationJson, Invocation.class);
        return invoke(invocation);
    }

    public static Object invoke(Invocation invocation) {
        if (StringUtils.isBlank(invocation.getMethod()))
            throw new EmptyInvocationInstructionException();

        try {
            Target target = invocation.getTarget();
            return target.invoke(invocation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
