package com.atguigu.bookstore.beans;
/**
 * 分块下载使用的文件快
 * @author gezongyang
 *
 */
public class FileBlock {

	private long start;//开始位置
	private long end;
	
	public FileBlock() {
		super();
	}
	public FileBlock(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	
	
}
