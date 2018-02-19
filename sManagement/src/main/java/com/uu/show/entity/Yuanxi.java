package com.uu.show.entity;

public class Yuanxi {

	public String xiID;
	public String xiName;
	public String yuan;

	public String getXiID() {
		return xiID;
	}

	public void setXiID(String xiID) {
		this.xiID = xiID;
	}

	public String getXiName() {
		return xiName;
	}

	public void setXiName(String xiName) {
		this.xiName = xiName;
	}

	public String getYuan() {
		return yuan;
	}

	public void setYuan(String yuan) {
		this.yuan = yuan;
	}

	@Override
	public String toString() {
		return "Yuanxi [xiID=" + xiID + ", xiName=" + xiName + ", yuan=" + yuan + "]";
	}
	
	

}
