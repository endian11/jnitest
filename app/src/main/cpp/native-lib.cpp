#include <jni.h>
#include <string>
#include "Demo.hpp"
#include "native_lib.hpp"
#include "PersonInC.hpp"
#include <Android/log.h>

#define LOG_TAG  “jnidebug”

#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE,  LOG_TAG, __VA_ARGS__)

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG ,  LOG_TAG, __VA_ARGS__)

#define LOGI(...) __android_log_print(ANDROID_LOG_INFO  ,  LOG_TAG, __VA_ARGS__)

#define LOGW(...) __android_log_print(ANDROID_LOG_WARN  ,  LOG_TAG, __VA_ARGS__)

#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR  ,  LOG_TAG, __VA_ARGS__)

using namespace std;

JNIEXPORT jstring JNICALL
Java_cppt_phone_lzw_com_cpptes_JniTest_stringFromJNI(
JNIEnv* env,
jobject /* this */) {

string hello = "Hello from C++";
return env->NewStringUTF(hello.c_str());
}


/*
 * Class:     cppt_phone_lzw_com_cpptes_MainActivity
 * Method:    addSub
 * Signature: (II)I
 */

JNIEXPORT jint JNICALL
Java_cppt_phone_lzw_com_cpptes_JniTest_addSub
(JNIEnv *env, jobject, jint x, jint y){
    Demo demo = Demo();
    int a = demo.add(x,y);
    return a;
}

/*
 * Class:     cppt_phone_lzw_com_cpptes_JniTest
 * Method:    addPerson
 * Signature: (Lcppt/phone/lzw/com/cpptes/Person;)Lcppt/phone/lzw/com/cpptes/Person;
 */
JNIEXPORT jobject JNICALL Java_cppt_phone_lzw_com_cpptes_JniTest_addPerson
(JNIEnv *env, jobject,  jobject obj_person){
    jclass person = env->GetObjectClass(obj_person);
    if (person == NULL){
        cout<<"GetObjectClass Failed!!\n";
    }
    int i=5;
__android_log_print(ANDROID_LOG_INFO,"JNI","i=%d",i);
jfieldID ageFieldId = env->GetFieldID(person,"age","I");
    jfieldID nameFieldId = env->GetFieldID(person,"name","Ljava/lang/String;");
    jint age = env->GetIntField(obj_person,ageFieldId);
    jstring name = (jstring)env->GetObjectField(obj_person,nameFieldId);
    const char * c_name = env->GetStringUTFChars(name,NULL);
    char* cc  = new char[strlen(c_name)+1];
    strcpy(cc,c_name);
    PersonInC p = PersonInC(age,cc);
    //   这两种类型 都可以获得class引用cppt.phone.lzw.com.cpptes.Person
    jclass stucls = env->FindClass("cppt/phone/lzw/com/cpptes/Person"); //或得Student类引用

    //获得得该类型的构造函数  函数名为 <init> 返回类型必须为 void 即 V
    jmethodID constrocMID = env->GetMethodID(stucls,"<init>","(ILjava/lang/String;)V");

    jstring str = env->NewStringUTF(" come from Native ");

    jobject stu_ojb = env->NewObject(stucls,constrocMID,11,str);  //构造一个对象，调用该类的构造函数，并且传递参数


    return stu_ojb ;
}

