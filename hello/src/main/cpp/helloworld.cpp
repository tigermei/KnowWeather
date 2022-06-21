#include <jni.h>
#include <string>
#include "Hello.h"
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>


class Person{
public:
    Person();
    ~Person();

public:
    string getName();
};

Person::Person() {
    cout << "new Person" << endl;
}

Person::~Person() {
    cout << "delete Person" << endl;
}

string Person::getName() {
    cout << "getName" << endl;
    return string("tigermei");
}

string getHelloWorld(){
    void* handle = nullptr;
    string retValue;
    typedef string (*pf_t)(); //声明函数指针

    handle = dlopen("libnativelib.so", RTLD_NOW);
    if(nullptr != handle){
        pf_t pf = (pf_t) dlsym(handle, "_Z10helloWorldv");
        if(nullptr != pf){
            retValue = pf();
        }

        dlclose(handle);
    }

    return retValue;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_tigermei_hello_HelloWorld_stringFromJNIHello(
        JNIEnv* env,
        jobject /* this */) {


    std::string hello = getHelloWorld();
    return env->NewStringUTF(hello.c_str());
}
