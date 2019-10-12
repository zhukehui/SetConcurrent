package com.atguigu.set;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;





/**
 * 
 * 多线程：线程    操作    资源类
 *
 *Lambda：拷贝接口方法小括号(...)  写死右箭头  ->   落实大括号 { 重写接口的方法体 }
 */

/**
 * 
 * @author 86177
 *
 *1.故障现象
 *java.util.ConcurrentModificationException
 *2.导致原因
 *
 *3.解决方法
 *3.1 : Collections.synchronizedSet(new HashSet<>());
 *3.2 : new CopyOnWriteArraySet<>();
 *4.优化建议
 */

public class HashSetConcurrent {

	public static void main(String[] args) {
		Set<String> set =new CopyOnWriteArraySet<>();

		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 6));
				System.out.println(set);
			}, String.valueOf(i)).start();
		}
	}
}

/**
 * 写时复制
 CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，
 复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，
 再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
 public boolean add(E e)
 {
 final ReentrantLock lock = this.lock;

 lock.lock();

 try{
 	Object[] ele ments = getArray();
 	int len = elements.length;
 	Object[] newElements = Arrays.copyOf(elements, len + 1);
 	newElements[len] = e;
 	setArray(newElements);
 	return true;
 }
 finally {
 	lock.unlock();
 	}
 }
 */
