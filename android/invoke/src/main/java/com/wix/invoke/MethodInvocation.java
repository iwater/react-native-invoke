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

    public static Object invoke(String invocationJson) {
        Invocation invocation = JsonParser.parse(invocationJson, Invocation.class);
        return invoke(invocation);
    }

    public static Object invoke(Invocation invocation) {
        if (StringUtils.isBlank(invocation.getMethod()))
            throw new EmptyInvocationInstructionException();

        Object retVal;
        try {
            Target target = invocation.getTarget();
            switch (target.getType()) {
                case Class:
                    retVal = MethodUtils.invokeStaticMethod(Class.forName(target.getValue().toString()), invocation.getMethod(), invocation.getArgs());
                    break;
                case Invocation:
                    Invocation innerInvocation = (Invocation) target.getValue();
                    Object intermediate = invoke(innerInvocation);
                    retVal = MethodUtils.invokeExactMethod(intermediate, invocation.getMethod(), invocation.getArgs());
                    break;
                default:
                    throw new RuntimeException("unsupported target type:" + target.getType());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (retVal != null) {
            return retVal;
        }
        return null;
    }
}
