package com.maybank.labs.starter.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maybank.labs.starter.exception.StarterCustomException;

@Service
public class StarterFileSynchronization {
	
	private final String file2Lock = "/home/jadmin/JBOSS-EAP-7.3.0/standalone/log/to-lock.txt";
	
	public void doSynchronizedTask(String toPrint) throws StarterCustomException {
		FileLock lock = null;
		logMe(" Getting lock...");
		try (FileOutputStream raf = new FileOutputStream(file2Lock);
				FileChannel fileChannel = raf.getChannel()) {
			while(lock == null) {
				try {
					lock = fileChannel.lock();
				} catch (OverlappingFileLockException e) {
					// TODO: handle exception
				}
			}
			logMe(" Got lock...");
			doSomething(toPrint);
			logMe(" Releasing lock...");
			lock.release();
		} catch (FileNotFoundException e) {
			throw new StarterCustomException(HttpStatus.INTERNAL_SERVER_ERROR, 999, LocalDateTime.now(), "Synchronization File not found!");
		} catch (IOException e) {
			throw new StarterCustomException(HttpStatus.INTERNAL_SERVER_ERROR, 998, LocalDateTime.now(), "IO Exception while Synchronization!");
		}
		
	}
	
	public void doSomething(String toPrint) {
		logMe(Thread.currentThread().getName()+" Doing task...");
		try {
			Thread.sleep(5000);
			logMe(">>>Printing task... "+ toPrint);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logMe(Thread.currentThread().getName()+"Task Complete...");
	}
	
	private void logMe(String message) {
		System.out.println(StarterConstants.PROJECT_IDENTIFIER+"["+Thread.currentThread().getName()+"] : "+message);
	}

}
