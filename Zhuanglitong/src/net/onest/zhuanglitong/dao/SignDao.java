package net.onest.zhuanglitong.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.onest.zhuanglitong.bean.UserSign;
import net.onest.zhuanglitong.common.DbConnection;

public class SignDao {
	public Boolean setSignMessage(String userName,int userId,String date) {
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement("insert into sign(userId,userName,date) values(?,?,?)");
			ps.setInt(1,userId);
			ps.setString(2,userName);
			ps.setString(3, date);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	//根据用户的名字得到用户的签到信息
	public List<UserSign> getSignMessage(String userName) {
		UserSign userSign=null;
		List<UserSign>sign=new ArrayList<UserSign>();
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet eq=null;
		try {
			ps=connection.prepareStatement("select * from sign where userName=?");
			ps.setString(1, userName);
			eq=ps.executeQuery();
			while(eq.next()) {
				userSign=new UserSign();
				userSign.setUserName(userName);
				userSign.setSignId(eq.getInt("signId"));
				userSign.setUserId(eq.getInt("userId"));
				userSign.setDate(eq.getString("date"));
				sign.add(userSign);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sign;
	}

}
