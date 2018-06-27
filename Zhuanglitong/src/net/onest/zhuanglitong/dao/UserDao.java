package net.onest.zhuanglitong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.onest.zhuanglitong.bean.User;
import net.onest.zhuanglitong.common.DbConnection;






public class UserDao {
	public User getUserMessage1(String userName) {
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet eq=null;
		User user=new User();
		try {
			ps=connection.prepareStatement("select * from user where userName=?");
			ps.setString(1, userName);
			System.out.println(ps.toString());
			eq=ps.executeQuery();
			if(eq.next()) {
				user.setUserId(eq.getInt("userId"));
				user.setUserName(userName);
				user.setAddress(eq.getString("address"));
				user.setBirth(eq.getString("birth"));
				user.setSex(eq.getString("sex"));
				user.setPassword(eq.getString("password"));
				user.setDetail(eq.getString("detail"));
				user.setPhone(eq.getString("phone"));
				user.setImage(eq.getString("image"));
			}
//			DbConnection.closeConnection(connection, ps, eq);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public String register(String userName, String password,String phone,String sex,String detail) {
		// TODO Auto-generated method stub
		System.out.println(sex);
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(phone);
		if(isExistUserName(userName) != null) {
			return "用户名重复请，重新注册";
		}else if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")) {
			return "密码必须应为6-20个英文和数字，请重新注册";
		}else if(!m.matches()) {
			return "手机号码格式不正确，请重新注册";
		}else if(sex == null){
			return "性别不能为空，请重新注册";
		}else if(detail.length()>60) {
			return "个人简介应不超过60个字，请重新注册";
		}else {
			Connection connection = DbConnection.getConnection();
			PreparedStatement ps = null;
			try {
				ps = connection.prepareStatement("insert into user(userName, password,phone,sex,detail) values(?,?,?,?,?)");
				ps.setString(1, userName);
				ps.setString(2, password);
				ps.setString(3, phone);
				ps.setString(4, sex);
				ps.setString(5, detail);
				ps.execute();
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
			return "成功";
		}
	}
	public User isExistUserName(String userName) {
		// TODO Auto-generated method stub
		User user = null;
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("select * from user where userName=?");
			ps.setString(1, userName);
			rs = ps.executeQuery();
			//把选出的内容转换成users的形势
			if(rs.next()) {
				user = new User();
				user.setUserName(userName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public User isExist(String userName, String password) {
		// TODO Auto-generated method stub
		User user = null;
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("select * from user where userName=? and password=?");
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			//把选出的内容转换成users的形势
			if(rs.next()) {
				user = new User();
				user.setUserName(userName);
				user.setPassword(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public User isLogin(String userName) {
		// TODO Auto-generated method stub
		User user = null;
		Connection connection = DbConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("select * from user where userName=?");
			ps.setString(1, userName);
			rs = ps.executeQuery();
			//把选出的内容转换成users的形势
			if(rs.next()) {
				user = new User();
				user.setUserName(userName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	//客户端显示用户的详细信息
	public User getUserMessage(int userId) {
		//使用jdbc连接数据库，加载驱动类，连接数据库，访问数据库
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet eq=null;
		User user=new User();
		try {
			ps=connection.prepareStatement("select * from user where userId=?");
			ps.setInt(1, userId);
			System.out.println(ps.toString());
			eq=ps.executeQuery();
			if(eq.next()) {
				user.setUserName(eq.getString("userName"));
				user.setUserId(userId);
				user.setAddress(eq.getString("address"));
				user.setBirth(eq.getString("birth"));
				user.setSex(eq.getString("sex"));
				user.setPassword(eq.getString("password"));
				user.setDetail(eq.getString("detail"));
				user.setPhone(eq.getString("phone"));
				user.setImage(eq.getString("image"));
			}
//			DbConnection.closeConnection(connection, ps, eq);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	//对应客户端账号管理处的用户信息的修改
	public Boolean editUserMessage(User user) {				
		//修改用户的信息
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=connection.prepareStatement("update user set userId=?,userName=?,password=?,phone=?,sex=?,birth=?,detail=?,address=?,image=? where userId=?");
			ps.setInt(1, user.getUserId());
			ps.setString(2,user.getUserName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPhone());
			System.out.println(user.getSex()+"jisfjsdklf");
			ps.setString(5, user.getSex());
			ps.setString(6, user.getBirth());
			ps.setString(7, user.getDetail());
			ps.setString(8, user.getAddress());
			ps.setString(9, user.getImage());
			ps.setInt(10, user.getUserId());
			ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
	public int getUserId(String userName) {
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet eq=null;
		User user=new User();
		try {
			ps=connection.prepareStatement("select userId from user where userName=?");
			ps.setString(1,userName);
			eq=ps.executeQuery();
			if(eq.next()) {
				user.setUserId(eq.getInt("userId"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user.getUserId();
		
	}
	public boolean editUserName(String userName) {
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement("update user set userName=? where userName=?");
			ps.setString(1, userName);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	//修改手机号
	public Boolean changePhone(String phone, int userId) {				
		//修改用户的信息
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement("update user set phone=? where userId=?");
		
			ps.setString(1, phone);
			ps.setInt(2, userId);
			
			ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
	//修改密码
		public Boolean changePassword(String password, int userId) {				
			//修改用户的信息
			Connection connection=DbConnection.getConnection();
			PreparedStatement ps=null;
			try {
				ps=connection.prepareStatement("update user set password=? where userId=?");
			
				ps.setString(1, password);
				ps.setInt(2, userId);
				
				ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return true;
		}
	//添加实名认证
	public boolean saveIdentity(String realName, String number,int userId) {
		// TODO Auto-generated method stub
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement("update user set realName=?,number=? where userId=?");
		
			ps.setString(1, realName);
			ps.setString(2, number);
			ps.setInt(3, userId);
			ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
	//储存email
	public boolean storeEmail(String email, int userId) {
		// TODO Auto-generated method stub
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement("update user set email=? where userId=?");
		
			ps.setString(1, email);
			ps.setInt(2, userId);
			
			ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
	
}
