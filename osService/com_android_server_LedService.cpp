#include "jni.h"
#include "JNIHelp.h"
#include "android_runtime/AndroidRuntime.h"

#include <utils/misc.h>
#include <utils/Log.h>

#include <stdio.h>


#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/ioctl.h>

#include <hardware/hal_led.h>

namespace android
{

static led_device_t* led_device;

jint ledOpen_c(JNIEnv *env, jobject cls)
{
	jint err;
	hw_module_t* module;
	hw_device_t* device;
	
	err = hw_get_module("led", (hw_module_t const**)&module );

	if(err == 0)
	{
		err = module->methods->open(module, NULL, &device);
		
		if(err == 0)
		{
			led_device = (led_device_t *)device;
			return led_device->led_open(led_device);
		}
		else
			return -1; 
	}

    return -1 ;
}

void ledClose_c(JNIEnv *env, jobject cls)
{
    ALOGI("native ledClose ...");
}

jint ledCtrl_c(JNIEnv *env, jobject cls, jint led, jint status)
{
    return led_device->led_ctrl(led_device, led, status);
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
