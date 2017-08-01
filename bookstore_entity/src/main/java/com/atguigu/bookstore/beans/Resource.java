package com.atguigu.bookstore.beans;
public class Resource {
	
	private Integer resId;
	private String resPath;
	
	//公共资源：true
	//受保护资源：false
	private boolean publicRes;
	
	private Integer resCode;
	private Integer resPos;
	
	public Resource() {
		// TODO Auto-generated constructor stub
	}

	public Resource(Integer resId, String resPath, boolean publicRes,
			Integer resCode, Integer resPos) {
		super();
		this.resId = resId;
		this.resPath = resPath;
		this.publicRes = publicRes;
		this.resCode = resCode;
		this.resPos = resPos;
	}

	@Override
	public String toString() {
		return "Res [resId=" + resId + ", resPath=" + resPath
				+ ", publicRes=" + publicRes + ", resCode=" + resCode
				+ ", resPos=" + resPos + "]";
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getresPath() {
		return resPath;
	}

	public void setresPath(String resPath) {
		this.resPath = resPath;
	}

	public boolean isPublicRes() {
		return publicRes;
	}

	public void setPublicRes(boolean publicRes) {
		this.publicRes = publicRes;
	}

	public Integer getResCode() {
		return resCode;
	}

	public void setResCode(Integer resCode) {
		this.resCode = resCode;
	}

	public Integer getResPos() {
		return resPos;
	}

	public void setResPos(Integer resPos) {
		this.resPos = resPos;
	}

}
