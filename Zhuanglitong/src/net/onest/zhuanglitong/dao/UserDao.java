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
			return "�û����ظ��룬����ע��";
		}else if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")) {
			return "�������ӦΪ6-20��Ӣ�ĺ����֣�������ע��";
		}else if(!m.matches()) {
			return "�ֻ������ʽ����ȷ��������ע��";
		}else if(sex == null){
			return "�Ա���Ϊ�գ�������ע��";
		}else if(detail.length()>60) {
			return "���˼��Ӧ������60���֣�������ע��";
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
			return "�ɹ�";
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
			//��ѡ��������ת����users������
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
			//��ѡ��������ת����users������
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
			//��ѡ��������ת����users������
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
	//�ͻ�����ʾ�û�����ϸ��Ϣ
	public User getUserMessage(int userId) {
		//ʹ��jdbc�������ݿ⣬���������࣬�������ݿ⣬�������ݿ�
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
	//��Ӧ�ͻ����˺Ź������û���Ϣ���޸�
	public Boolean editUserMessage(User user) {				
		//�޸��û�����Ϣ
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
	//�޸��ֻ���
	public Boolean changePhone(String phone, int userId) {				
		//�޸��û�����Ϣ
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
	//�޸�����
		public Boolean changePassword(String password, int userId) {				
			//�޸��û�����Ϣ
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
	//���ʵ����֤
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
	//����email
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
