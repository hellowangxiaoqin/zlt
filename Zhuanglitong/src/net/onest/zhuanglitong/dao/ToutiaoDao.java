package net.onest.zhuanglitong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import net.onest.zhuanglitong.bean.TouTiao;
import net.onest.zhuanglitong.common.DbConnection;

public class ToutiaoDao {
	public ArrayList<TouTiao> findAll(){
		ArrayList<TouTiao> list=new ArrayList<TouTiao>();
		Connection connection=(Connection) DbConnection.getConnection();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("select * from toutiao");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				TouTiao toutiao=new TouTiao();
				toutiao.setTitle(rs.getString("tittle"));
				toutiao.setContent(rs.getString("content"));
				toutiao.setNewsImages(rs.getString("newsImages"));
				toutiao.setTag(rs.getString("tag"));
				list.add(toutiao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}
}
