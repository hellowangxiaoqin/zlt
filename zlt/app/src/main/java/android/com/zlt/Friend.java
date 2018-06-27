package android.com.zlt;

/**
 * Created by dell on 2018/5/15.
 */

public class Friend {
    private int friendId;
    private int userId;
    private int friendUserId;

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(int friendUserId) {
        this.friendUserId = friendUserId;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendId=" + friendId +
                ", userId=" + userId +
                ", friendUserId=" + friendUserId +
                '}';
    }
}
