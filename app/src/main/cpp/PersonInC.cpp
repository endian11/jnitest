//
// Created by lizhanwei on 17/8/28.
//
#include <Android/log.h>
#include "PersonInC.hpp"
using namespace std;
PersonInC::PersonInC(int aget,char * name)
{
    this->age = aget;
    this->name = name;
    cout<<"age:"<<age<<endl<<"name:"<<name<<endl;
    __android_log_print(ANDROID_LOG_VERBOSE,  "PersonInC", "age=%d,name=%s",age,name);
}