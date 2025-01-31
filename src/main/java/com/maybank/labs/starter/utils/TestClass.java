//package com.maybank.labs.starter.utils;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.channels.FileChannel;
//import java.nio.channels.FileLock;
//import java.nio.channels.OverlappingFileLockException;
//
//public class TestClass {
//
//	public static void main(String[] args) {
//		
//		TestClass tc = new TestClass();
//		
//		Runnable r = () -> {
//			tc.doSynchronizedTask();
//		};
//		
//		Thread t1 = new Thread(r, "Thread1");
//		Thread t2 = new Thread(r, "Thread2");
//		t1.start();
//		t2.start();
//	}
//	
//	public void doSynchronizedTask() {
//		FileLock lock = null;
//		System.out.println(Thread.currentThread().getName()+" Getting lock...");
//		try (FileOutputStream raf = new FileOutputStream("D:\\to-lock.txt");
//				FileChannel fileChannel = raf.getChannel()) {
//			while(lock == null) {
//				try {
//					lock = fileChannel.lock();
//				} catch (OverlappingFileLockException e) {
//					// TODO: handle exception
//				}
//			}
//			System.out.println(Thread.currentThread().getName()+" Got lock...");
//			doSomething();
//			System.out.println(Thread.currentThread().getName()+" Releasing lock...");
//			lock.release();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public void doSomething() {
//		System.out.println(Thread.currentThread().getName()+" Doing task...");
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(Thread.currentThread().getName()+"Task Complete...");
//	}
//
//}
