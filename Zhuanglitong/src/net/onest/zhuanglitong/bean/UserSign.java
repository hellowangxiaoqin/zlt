package net.onest.zhuanglitong.bean;

import java.util.Date;

public class UserSign {

	private int signId;//用户对应的签到的id
	private int userId;//用户的id
	private String date;//用户签到过的日期
	private String userName;//用户的名字
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

}
