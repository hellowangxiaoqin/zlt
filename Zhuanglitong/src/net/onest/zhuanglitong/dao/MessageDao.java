package net.onest.zhuanglitong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.onest.zhuanglitong.bean.ConMessage;
import net.onest.zhuanglitong.bean.User;
import net.onest.zhuanglitong.common.DbConnection;

public class MessageDao {
	public List<ConMessage> getMsg(String sendName,String receiveName) {
		User user = null;
		String name = null;
		List<ConMessage> msgList = new ArrayList<ConMessage>();
		Connection connection = DbConnection.getConnection();
		try {
			//接受到的消息
			PreparedStatement ps0 = connection.prepareStatement("select * from message where (name=? and nameReceive=?) or (name=? and nameReceive=?)");
			ps0.setString(1, sendName);
			ps0.setString(2, receiveName);
			ps0.setString(3, receiveName);
			ps0.setString(4, sendName);
			ResultSet rs0 = ps0.executeQuery();
			if(rs0.next()) {
				name = rs0.getString("name");
				System.out.println(name);
			}
			while(rs0.next()) {
				ConMessage msg = new ConMessage();
				String sendName1 = rs0.getString("name");
				msg.setName(rs0.getString("name"));
				msg.setDate(rs0.getString("date"));
				msg.setMessage(rs0.getString("message"));
				//int isComMeg = rs0.getInt("isComMeg");
				if(sendName1.equals(sendName)) {
					msg.setMsgType(true);
				}else if(sendName1.equals(receiveName)){
					msg.setMsgType(false);
				}
				msg.setIsRead(rs0.getInt("isRead"));
				msg.setNameReceive(rs0.getString("nameReceive"));
				msgList.add(msg);
			}
			
//			//发送过的消息
//			PreparedStatement ps1 = connection.prepareStatement("select * from message where name=? and nameReceive=?");
//			ps0.setString(1, receiveName);
//			ps0.setString(2, sendName);
//			ResultSet rs1 = ps1.executeQuery();
//			if(rs1.next()) {
//				name = rs1.getString("name");
//				System.out.println(name);
//			}
//			while(rs1.next()) {
//				ConMessage msg = new ConMessage();
//				msg.setName(rs1.getString("nameReceive"));
//				msg.setDate(rs1.getString("date"));
//				msg.setMessage(rs0.getString("message"));
//				msg.setMsgType(false);
//				msg.setIsRead(rs1.getInt("isRead"));
//				msg.setNameReceive(rs1.getString("name"));
//				msgList.add(msg);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msgList;
	}

	public boolean sendMsg(String sendName, String receiveName, String date, String message) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("insert into message(name,nameReceive,date,message, isComMeg,isRead) values(?,?,?,?,?,?)");
			ps.setString(1, receiveName);
			ps.setString(2, sendName);
			ps.setString(3, date);
			ps.setString(4, message);
			ps.setInt(5, 1);
			ps.setInt(6, 0);
			ps.execute();
			System.out.println("send success");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public int getMsgsNums(String loginName) {
		int nums = 0;
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps0 = null;
		try {
			//接受到的消息
			ps0 = connection.prepareStatement("select * from message where nameReceive=?");
			ps0.setString(1, loginName);
			ResultSet rs0 = ps0.executeQuery();
			while(rs0.next()) {
				int isRead = rs0.getInt("isRead");
				if(isRead == 0) {
					++nums;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try {
				ps0.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nums;
	}
	
	public boolean readMsg(String sendName, String receiveName) {
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("update message set isRead=1 where name=? and nameReceive=?");
			ps.setString(1, sendName);
			ps.setString(2, receiveName);
			ps.execute();
			System.out.println("read success");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
