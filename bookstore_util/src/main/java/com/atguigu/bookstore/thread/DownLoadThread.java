package com.atguigu.bookstore.thread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

import com.atguigu.bookstore.beans.FileBlock;
import com.sdonkey.score.util.StringUtil;

public class DownLoadThread implements Runnable{
	
	private FileBlock fileBlock;//文件块
	private String path;//资源路径
	private CountDownLatch latch;
	//private Lock lock = new ReentrantLock();
	
	public DownLoadThread() {
		super();
	}

	public DownLoadThread(FileBlock fileBlock, String path, CountDownLatch latch) {
		super();
		this.fileBlock = fileBlock;
		this.path = path;
		this.latch = latch;
	}


	@Override
	public void run() {
		
			//WriteDataToFile();
			//分段请求网络连接，分段保存文件到本地
			InputStream inputStream = null;
			RandomAccessFile randomAccessFile = null;
			try {
				URL url = new URL(path);
				HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
				openConnection.setRequestMethod("GET");
				openConnection.setConnectTimeout(10*1000);

				//设置分段下载的头信息。  Range:做分段数据请求用的。
				openConnection.setRequestProperty("Range", "bytes:"+fileBlock.getStart()+"-"+fileBlock.getEnd());//bytes:0-500:请求服务器资源中0-500之间的字节信息  501-1000:
				System.out.println("实际下载：  线程：" + Thread.currentThread().getId()+"，开始位置："+fileBlock.getStart()+";结束位置:"+fileBlock.getEnd());
			
				if (openConnection.getResponseCode() == 206){//200：请求全部资源成功， 206代表部分资源请求成功
					inputStream = openConnection.getInputStream();
					//请求成功将流写入本地文件中，已经创建的占位那个文件中
					randomAccessFile = new RandomAccessFile(new File("d://" + StringUtil.getFileName(path)), "rw");
					randomAccessFile.seek(fileBlock.getStart());//设置随机文件从哪个位置开始写。
					//将流中的数据写入文件
					byte[] buffer = new byte[1024];
					int length = -1;
					while((length= inputStream.read(buffer)) != -1){
						randomAccessFile.write(buffer, 0, length);
					}
					inputStream.close();
					randomAccessFile.close();
					latch.countDown();
					
				}
				randomAccessFile.close();
			} catch (Exception e) {
				e.printStackTrace();
		    } 
		}
	}
