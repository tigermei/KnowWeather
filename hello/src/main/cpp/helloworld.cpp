#include <jni.h>
#include <string>
#include "Hello.h"



extern "C" JNIEXPORT jstring JNICALL
Java_com_tigermei_hello_HelloWorld_stringFromJNIHello(
        JNIEnv* env,
        jobject /* this */) {

    std::string hello = helloWorld();
    return env->NewStringUTF(hello.c_str());
}