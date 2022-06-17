//
// Created by tigermei on 2022/6/16.
//

#include "Hello.h"

Hello::Hello(){
    cout << "constructor Hello" << endl;
}
Hello::~Hello(){
    cout << "destructor hello" << endl;
}

string Hello::getName(){
    return string("Hello C++!!");
}

extern string helloWorld(){
    return string("Hello World");
}
