#include <jni.h>
#include<stdio.h>
#include "TestJNI.h"

JNIEXPORT jint JNICALL Java_TestJNI_add(JNIEnv *env, jobject thisobj, jint n1, jint n2)
{
    jint res ;
    res = n2 + n2 ;
    return res ;
}