#include <jni.h>
#include <string>
#include "Hello.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_tigermei_nativelib_NativeLib_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    Hello *pHelloClass = new Hello();
    std::string hello = "Hello";
    if(pHelloClass){
        hello = pHelloClass->getName();
        delete pHelloClass;
        pHelloClass = nullptr;
    }

    return env->NewStringUTF(hello.c_str());
}