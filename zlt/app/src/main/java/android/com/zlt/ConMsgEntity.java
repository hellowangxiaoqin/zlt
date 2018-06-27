package android.com.zlt;

/**
 * Created by DELL on 2018/6/19.
 */

public class ConMsgEntity {
    private String name;//消息来自
    private String date;//消息日期
    private String message;//消息内容
    private boolean isComMeg = true;// 是否为收到的消息
    private int isRead;
    private String nameReceive;
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

    public ConMsgEntity() {
    }
    public ConMsgEntity(String name, String date, String text, boolean isComMsg,int isRead,String nameReceive) {
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
        return "ConMsgEntity{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                ", isComMeg=" + isComMeg +
                ", isRead=" + isRead +
                ", nameReceive='" + nameReceive + '\'' +
                '}';
    }
}
