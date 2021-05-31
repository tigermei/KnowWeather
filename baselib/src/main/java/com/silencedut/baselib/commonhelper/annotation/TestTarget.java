package com.silencedut.baselib.commonhelper.annotation;

import com.base.annotation.MethodControl;

/**
 * Created by liaohailiang on 2019-09-26.
 */
@MethodControl
public class TestTarget {

    public int getIntValue() {
        return 10;
    }

    public boolean getBoolValue() {
        return true;
    }

    public int parseLong(long value) {
        return (int) value;
    }
}
