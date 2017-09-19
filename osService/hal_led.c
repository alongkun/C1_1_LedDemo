#define LOG_TAG "LedService"

#include <hardware/hal_led.h>
#include <hardware/hardware.h>

#include <cutils/log.h>

#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>

#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/ioctl.h>

#include <utils/Log.h>
#include <hardware/hal_led.h>

static int fd;

static int led_close(struct hw_device_t* device)
{
    close(fd);
    return 0;
}

static int led_open(struct led_device_t* dev)
{
    fd = open("/dev/leds_test", O_RDWR);
    if (fd >= 0)
        ALOGI("ledOpen success...");
    else
        ALOGI("ledOpen error...");

    return 0 ;
}

static int led_ctrl(struct led_device_t* dev, int led, int status)
{
    int ret = ioctl(fd, status, led);

    ALOGI("ledCtrl ret = %d", ret);

    return ret;
}

static struct led_device_t led_dev = {
    .common = {
        .tag = HARDWARE_DEVICE_TAG,
        .close = led_close,
    },
    .led_open = led_open,
    .led_ctrl = led_ctrl,
};

static int led_device_open(const struct hw_module_t* module, const char* id,struct hw_device_t** device)
{

    *device = &led_dev;

    return 0;
}




static struct hw_module_methods_t led_module_methods = {
    .open = led_device_open,
};

struct hw_module_t HAL_MODULE_INFO_SYM = {
    .tag = HARDWARE_MODULE_TAG,
    .id = "led",
    .methods = &led_module_methods,
};
