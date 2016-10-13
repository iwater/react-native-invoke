package com.wix.invoke.types;

/**
 * Created by rotemm on 10/10/2016.
 */
public class Invocation {
    Target target;
    String method;
    Object[] args;

    public Invocation(Target target, String method, Object... args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
