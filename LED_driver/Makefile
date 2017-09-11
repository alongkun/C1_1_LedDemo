KERN_DIR = /home/alongkun/work/tiny4412/linux-3.0.86

all:	
	make -C $(KERN_DIR) M=`pwd` modules 
	
clean:	
	make -C $(KERN_DIR) M=`pwd` modules clean	
	rm -rf modules.order

obj-m	+= led.o
obj-m	+= led.o