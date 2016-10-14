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

    public Target() {

    }

    public Target(String type, Object value) {
        this.type = Type.valueOf(type);
        this.value = value;
    }


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
        if (type.equals(Type.Invocation)) {
            this.value = new Invocation(value);
        } else {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Target)) return false;

        Target target = (Target) o;

        if (type != target.type) return false;
        return value != null ? value.equals(target.value) : target.value == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}