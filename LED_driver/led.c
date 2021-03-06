#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/miscdevice.h>
#include <linux/device.h>
#include <linux/fs.h>
#include <linux/types.h>
#include <linux/moduleparam.h>
#include <linux/slab.h>
#include <linux/ioctl.h>
#include <linux/cdev.h>
#include <linux/delay.h>
 
#include <linux/gpio.h>
#include <mach/gpio.h>
#include <plat/gpio-cfg.h>


static int led_gpios[] = {
	EXYNOS4212_GPM4(0),
	EXYNOS4212_GPM4(1),
	EXYNOS4212_GPM4(2),
	EXYNOS4212_GPM4(3),		
};

static int led_open(struct inode *inode, struct file *file)
{
	int i;
	for( i = 0; i < 4; i++)
		s3c_gpio_cfgpin(led_gpios[i],S3C_GPIO_OUTPUT);

	return 0;
}

static long led_ioctl(struct file *file, unsigned int cmd, unsigned long arg)
{
	gpio_set_value(led_gpios[arg], !cmd);

	return 0;
}

static int major;
static struct class *cls;

static struct file_operations leds_ops = {
	.owner = THIS_MODULE,
	.open = led_open,
	.unlocked_ioctl = led_ioctl,
};


static int __init leds_init(void)
{
	major = register_chrdev(0, "leds_test", &leds_ops);

	cls = class_create(THIS_MODULE, "leds_test");
	device_create(cls, NULL, MKDEV(major, 0), NULL, "leds_test");

	return 0;
}

static void __exit leds_exit(void)
{
	device_destroy(cls, MKDEV(major, 0));
	class_destroy(cls);
	unregister_chrdev(major, "leds_test");

}

module_init(leds_init);
module_exit(leds_exit);

MODULE_LICENSE("GPL");

