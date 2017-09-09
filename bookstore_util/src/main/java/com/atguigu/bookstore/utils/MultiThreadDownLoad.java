package com.atguigu.bookstore.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.atguigu.bookstore.beans.FileBlock;
import com.atguigu.bookstore.thread.DownLoadThread;
import com.sdonkey.score.util.StringUtil;

public class MultiThreadDownLoad {

	private  int threadCount = 3;//开启3个线程
	private  int blockSize = 0;//每个线程下载的大小
	private  String fileName = null;
	private  List<FileBlock> list =null;
	private String filePath = ConfigUtils.getString("file.path");
	
	public MultiThreadDownLoad(int threadCount, String fileName) {
		super();
		this.threadCount = threadCount;
		this.fileName = fileName;
	}

	/**
	 * @param args
	 */
	public int downloadByMultiThread() {
        System.out.println("当前线程id " + Thread.currentThread().getId());
		ExecutorService threadPool = null;
		HttpURLConnection openConnection = null;
		
		try{	
			String path =filePath + fileName;
			//1.请求url地址获取服务端资源的大小
			URL url = new URL(path);
			openConnection = (HttpURLConnection) url.openConnection();
			openConnection.setRequestMethod("GET");
			openConnection.setConnectTimeout(10*1000);
			RandomAccessFile randomAccessFile =null;
			int code = openConnection.getResponseCode();
			if(code == 200){
				//获取资源的大小
				int filelength = openConnection.getContentLength();
				
				//2.在本地创建一个与服务端资源同样大小的一个文件（占位）
				randomAccessFile = new RandomAccessFile(new File("d://" + StringUtil.getFileName(path) ), "rw");
				randomAccessFile.setLength(filelength);//设置随机访问文件的大小

				//3.把一个文件分成多个模块f
				blockSize = filelength/threadCount;//计算出每个线程理论下载大小
				list = new ArrayList<>(threadCount);
				FileBlock block1 = null;
				for (int i = 0; i < threadCount; i++) {
					int startIndex =  i * blockSize;//计算每个线程下载的开始位置
					int endIndex = (i + 1) * blockSize - 1;//计算每个线程下载的结束位
					if (i == threadCount - 1) {
					   endIndex = filelength - 1;
					}
					block1 = new FileBlock(startIndex, endIndex);
					list.add(block1);
				}
			}	
			    //4.创建一个定长的线程池，启动多个线程来下载文件
				final CountDownLatch latch=new CountDownLatch(3);
				for (int i = 0; i < threadCount; i++) {
					threadPool = Executors.newFixedThreadPool(3);  
					threadPool.execute(new DownLoadThread(list.get(i), path ,latch));
					System.out.println("开启时线程计数： " + latch.getCount());
					
				}
			    
				latch.await();//让执行完上传的线程先等待
				randomAccessFile.close();
				System.out.println("线程计数： " + latch.getCount());
				System.out.println(Thread.currentThread().getId()+ "" + ((Thread.currentThread().isAlive())? "村话" :"死亡"));
			    System.out.println("上传已全部结束！");
			    System.out.println("结束时当前线程的id: " + Thread.currentThread().getId());
			    return 1;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		} 
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public void setfileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getThreadCount() {
		return threadCount;
	}

	public String getfileName() {
		return fileName;
	}
	
}