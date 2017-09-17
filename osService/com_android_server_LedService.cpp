#define LOG_TAG "LedService"

#include "jni.h"
#include "JNIHelp.h"
#include "android_runtime/AndroidRuntime.h"

#include <utils/misc.h>
#include <utils/Log.h>
//#include <hardware_legacy/vibrator.h>

#include <stdio.h>


#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/ioctl.h>

namespace android
{

static jint fd;

jint ledOpen_c(JNIEnv *env, jobject cls)
{
    fd = open("/dev/leds_test", O_RDWR);
    if (fd >= 0)
        ALOGI("ledOpen success...");
    else
        ALOGI("ledOpen error...");

    return 0 ;
}

void ledClose_c(JNIEnv *env, jobject cls)
{
    ALOGI("native ledClose ...");

    close(fd);
}

jint ledCtrl_c(JNIEnv *env, jobject cls, jint led, jint status)
{
    int ret = ioctl(fd, status, led);

    ALOGI("ledCtrl ret = %d", ret);

    return ret;
}

static const JNINativeMethod methods[] = {
    {"native_ledOpen", "()I", (void *)ledOpen_c},
    {"native_ledClose", "()V", (void *)ledClose_c},
    {"native_ledCtrl", "(II)I", (void *)ledCtrl_c},
};

int register_android_server_LedService(JNIEnv *env)
{
    return jniRegisterNativeMethods(env, "com/android/server/LedService",
            methods, NELEM(methods ));
}

};
