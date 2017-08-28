//
// Created by lizhanwei on 17/8/25.
//

#ifndef _Included_cppt_phone_lzw_com_cpptes_JniTest
#define _Included_cppt_phone_lzw_com_cpptes_JniTest
#ifdef __cplusplus

extern "C" {
#endif
    /*
     * Class:     cppt_phone_lzw_com_cpptes_JniTest
     * Method:    stringFromJNI
     * Signature: ()Ljava/lang/String;
     */
    JNIEXPORT jstring JNICALL Java_cppt_phone_lzw_com_cpptes_JniTest_stringFromJNI
    (JNIEnv *, jobject);
    
    /*
     * Class:     cppt_phone_lzw_com_cpptes_JniTest
     * Method:    addSub
     * Signature: (II)I
     */
    JNIEXPORT jint JNICALL Java_cppt_phone_lzw_com_cpptes_JniTest_addSub
    (JNIEnv *, jobject, jint, jint);
    
    /*
     * Class:     cppt_phone_lzw_com_cpptes_JniTest
     * Method:    addPerson
     * Signature: (Lcppt/phone/lzw/com/cpptes/Person;)Lcppt/phone/lzw/com/cpptes/Person;
     */
    JNIEXPORT jobject JNICALL Java_cppt_phone_lzw_com_cpptes_JniTest_addPerson
    (JNIEnv *, jobject, jobject);
    

    
#ifdef __cplusplus
}
#endif
#endif
