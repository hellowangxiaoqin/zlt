package net.onest.zhuanglitong.bean;

import java.util.Date;

public class UserSign {

	private int signId;//�û���Ӧ��ǩ����id
	private int userId;//�û���id
	private String date;//�û�ǩ����������
	private String userName;//�û�������
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
