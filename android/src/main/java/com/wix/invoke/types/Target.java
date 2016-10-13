package com.wix.invoke.types;

/**
 * Created by rotemm on 10/10/2016.
 */
public class Target {

    public enum Type {
         Class,
         Invocation
     }

    Type type;
    Object value;

    public Target(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
