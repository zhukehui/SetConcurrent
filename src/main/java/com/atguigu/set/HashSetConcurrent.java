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


