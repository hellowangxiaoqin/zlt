package net.onest.zhuanglitong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.onest.zhuanglitong.bean.Friend;
import net.onest.zhuanglitong.bean.User;
import net.onest.zhuanglitong.common.DbConnection;

public class FriendDao {
	public List<User> findFriend(String userName) {
		User user = null;
		int userId = 0;
		List<User> userList = new ArrayList<User>();
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement ps0 = connection.prepareStatement("select * from user where userName=?");
			ps0.setString(1, userName);
			ResultSet rs0 = ps0.executeQuery();
			if(rs0.next()) {
				userId = rs0.getInt("userId");
				System.out.println(userId);
				PreparedStatement ps1 = connection.prepareStatement("select * from friend where userId=? or friendUserId=?");
				ps1.setInt(1, userId);
				ps1.setInt(2, userId);
				ResultSet rs1 = ps1.executeQuery();
				List<Integer> friendIdList = new ArrayList<Integer>();
				if(rs1.next()) {
					while(rs1.next()) {
						//System.out.println(rs1.getInt("friendUserId"));
						int id = rs1.getInt("friendUserId");
						friendIdList.add(id);
						System.out.println(friendIdList.toString());
					}
					for(int i = 0;i<friendIdList.size();i++) {
						int id = friendIdList.get(i);
						System.out.println(id);
						PreparedStatement ps2 = connection.prepareStatement("select * from user where userId=?");
						ps2.setInt(1, id);
						ResultSet rs2 = ps2.executeQuery();
						while(rs2.next()) {
							User user0 = new User();
							user0.setUserId(rs2.getInt("userId"));
							user0.setUserName(rs2.getString("userName"));
							String name = user0.getUserName();
							System.out.println(name);
							PreparedStatement ps3 = connection.prepareStatement("select * from message where name=? and nameReceive=?");
							ps3.setString(1, name);
							ps3.setString(2, userName);
							ResultSet rs3 = ps3.executeQuery();
							int msg = 0;
							while(rs3.next()) {
								int isRead = rs3.getInt("isRead");
								if(isRead == 0) {
									msg++;
								}
							}
							user0.setSex(rs2.getString("sex"));
							user0.setPhone(rs2.getString("phone"));
							user0.setEmail(rs2.getString("email"));
							user0.setDetail(rs2.getString("detail"));
							user0.setMsg(msg);
							userList.add(user0);
						}
						
					}
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
	}

	public List<User> findSomeFriend(String loginName,String searchName) {
		int userId = 0;
		User user = new User();
		List<User> someUserList = new ArrayList<User>();
		List<User> userList = new ArrayList<User>();
		userList = findFriend(loginName);
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement ps0 = connection.prepareStatement("select * from user where userName=?");
			ps0.setString(1, searchName);
			ResultSet rs0 = ps0.executeQuery();
			while(rs0.next()) {
				User user0 = new User();
				String userName = rs0.getString("userName");
				Boolean flag = false;
				for(int i=0;i<userList.size();i++) {
					String name = userList.get(i).getUserName();
					if(name.equals(userName)) {
						flag = true;
					}
				}
				System.out.println(flag);
				if(flag) {
					user0.setUserId(rs0.getInt("userId"));
					user0.setUserName(rs0.getString("userName"));
					String name = user0.getUserName();
					System.out.println(name);
					PreparedStatement ps3 = connection.prepareStatement("select * from message where name=? and nameReceive=?");
					ps3.setString(1, name);
					ps3.setString(2, loginName);
					ResultSet rs3 = ps3.executeQuery();
					int msg = 0;
					while(rs3.next()) {
						int isRead = rs3.getInt("isRead");
						if(isRead == 0) {
							msg++;
						}
					}
					user0.setSex(rs0.getString("sex"));
					user0.setPhone(rs0.getString("phone"));
					user0.setEmail(rs0.getString("email"));
					user0.setDetail(rs0.getString("detail"));
					user0.setMsg(msg);
					someUserList.add(user0);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return someUserList;
	} 
	
	public List<User> findNewFriend(String loginName,String searchName) {
		int userId = 0;
		User user = new User();
		List<User> someUserList = new ArrayList<User>();
		List<User> userList = new ArrayList<User>();
		userList = findFriend(loginName);
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement ps0 = connection.prepareStatement("select * from user where userName=?");
			ps0.setString(1, searchName);
			ResultSet rs0 = ps0.executeQuery();
			while(rs0.next()) {
				User user0 = new User();
					user0.setUserId(rs0.getInt("userId"));
					user0.setUserName(rs0.getString("userName"));
					String name = user0.getUserName();
					System.out.println(name);
					PreparedStatement ps3 = connection.prepareStatement("select * from message where name=? and nameReceive=?");
					ps3.setString(1, name);
					ps3.setString(2, loginName);
					ResultSet rs3 = ps3.executeQuery();
					int msg = 0;
					while(rs3.next()) {
						int isRead = rs3.getInt("isRead");
						if(isRead == 0) {
							msg++;
						}
					}
					user0.setSex(rs0.getString("sex"));
					user0.setPhone(rs0.getString("phone"));
					user0.setEmail(rs0.getString("email"));
					user0.setDetail(rs0.getString("detail"));
					user0.setMsg(msg);
					someUserList.add(user0);
				}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return someUserList;
	} 
}
