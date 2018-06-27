package net.onest.zhuanglitong.bean;

public class ConMessage {
	private String name;//��Ϣ����
    private String date;//��Ϣ����
    private String message;//��Ϣ����
    private boolean isComMeg = true;// �Ƿ�Ϊ�յ�����Ϣ
    private int isRead;//�Ƿ��Ѷ�
    private String nameReceive;//��Ϣ����
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean getMsgType() {
        return isComMeg;
    }
    public void setMsgType(boolean isComMsg) {
        isComMeg = isComMsg;
    }
    public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public String getNameReceive() {
		return nameReceive;
	}
	public void setNameReceive(String nameReceive) {
		this.nameReceive = nameReceive;
	}
	public ConMessage() {
    }
    public ConMessage(String name, String date, String text, boolean isComMsg,int isRead,String nameReceive) {
        super();
        this.name = name;
        this.date = date;
        this.message = text;
        this.isComMeg = isComMsg;
        this.isRead = isRead;
        this.nameReceive = nameReceive;
    }
	@Override
	public String toString() {
		return "ConMessage [name=" + name + ", date=" + date + ", message=" + message + ", isComMeg=" + isComMeg
				+ ", isRead=" + isRead + ", nameReceive=" + nameReceive + "]";
	}
	
    
}
