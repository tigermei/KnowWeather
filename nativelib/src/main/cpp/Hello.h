//
// Created by tigermei on 2022/6/16.
//

#ifndef KNOWWEATHER_HELLO_H
#define KNOWWEATHER_HELLO_H
#include <string>
#include <iostream>

using namespace std;

class Hello {
public:
    Hello();
    ~Hello();

    string getName();
};

//声明接口
extern string helloWorld();


#endif //KNOWWEATHER_HELLO_H
