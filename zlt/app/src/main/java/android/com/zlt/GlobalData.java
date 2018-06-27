package android.com.zlt;

/**
 * Created by wangxiaoqin on 2018/5/30.
 */

public class GlobalData
{
    private static String userName;
    public static void setUserName(String userName)
    {
        GlobalData.userName = userName;
    }
    public static String getUserName()
    {
        return userName;
    }
}
