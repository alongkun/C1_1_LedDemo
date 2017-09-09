//
// Created by alongkun on 17-9-9.
//

#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/ioctl.h>

#include <android/log.h>

static jint fd;

jint ledOpen_c(JNIEnv *env, jobject cls)
{
    __android_log_print(ANDROID_LOG_DEBUG, "LEDDemo", "native ledOpen ...");
    return 0;
}

void ledClose_c(JNIEnv *env, jobject cls)
{
    __android_log_print(ANDROID_LOG_DEBUG, "LEDDemo", "native ledClose ...");

}

jint ledCtrl_c(JNIEnv *env, jobject cls, jint led, jint status)
{
    __android_log_print(ANDROID_LOG_DEBUG, "LEDDemo", "native ledCtrl ...");
    return 0;
}

static const JNINativeMethod methods[] = {
    {"ledOpen", "()I", (void *)ledOpen_c},
    {"ledClose", "()V", (void *)ledClose_c},
    {"ledCtrl", "(II)I", (void *)ledCtrl_c},
};

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
    JNIEnv *env;
    jclass cls;

    if((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_4))
    {
        return JNI_ERR;
    }

    cls = (*env)->FindClass(env, "HardLib/HardControl");
    if(cls == NULL)
    {
        return JNI_ERR;
    }

    if((*env)->RegisterNatives(env, cls, methods, 3) < 0)
    {
        return JNI_ERR;
    }

    return JNI_VERSION_1_4;
}

