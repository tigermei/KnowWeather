package com.tigermei.hello;

public class HelloWorld {

    // Used to load the 'hello' library on application startup.
    static {
//        System.loadLibrary("hello");
    }

    /**
     * A native method that is implemented by the 'hello' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNIHello();
}