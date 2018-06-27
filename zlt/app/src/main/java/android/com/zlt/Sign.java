package android.com.zlt;

/**
 * Created by yangwenmiao on 2018/5/30.
 */

public class Sign {
    private int signId;//用户id对应的签到的id
    private int userId;//用户的id
    private String date;//签到的日期
    private String userName;//用户名字

    @Override
    public String toString() {
        return "Sign{" +
                "signId=" + signId +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

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
