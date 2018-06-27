package net.onest.zhuanglitong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.onest.zhuanglitong.bean.Dynamic;
import net.onest.zhuanglitong.common.DbConnection;



public class DynamicDao {
	public List<Dynamic> selectAll(){
		List<Dynamic> list=new ArrayList<Dynamic>();
		Connection connection=DbConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from dynamic ds");
			ResultSet eq = ps.executeQuery();
			while(eq.next()) {
				Dynamic dynamic = new Dynamic();
//				book.setBookid(eq.getInt("bookid"));
//				book.setBookName(eq.getString("bookName"));
//				book.setBookDetails(eq.getString("bookDetails"));
//				book.setBookPrice(eq.getString("bookPrice"));
//				book.setBookImages(eq.getString("bookImages"));
//				list.add(book);
				dynamic.setId(eq.getInt("id"));
				dynamic.setImage(eq.getString("image"));
				dynamic.setName(eq.getString("name"));
				dynamic.setDynamic(eq.getString("dynamic"));
				list.add(dynamic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
