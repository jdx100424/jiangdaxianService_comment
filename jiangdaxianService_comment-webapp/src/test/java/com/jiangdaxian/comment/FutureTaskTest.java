package com.jiangdaxian.comment;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class FutureTaskTest {

	public static void main(String []s) throws Exception {
		for(int i=0;i<10000;i++) {
			Callable<Integer> call = new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					System.out.println("正在计算结果...");
					System.out.println("计算结果OK...");
					return 1;
				}
			};
			FutureTask<Integer> task = new FutureTask<>(call);
	
			Thread thread = new Thread(task);
			thread.start();
	
			// do something
			System.out.println(" 干点别的...");
			Integer result = task.get();
			System.out.println("拿到的结果为：" + result);
		}
	}

}
