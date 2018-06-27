package net.onest.zhuanglitong.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import net.onest.zhuanglitong.bean.News;
import net.onest.zhuanglitong.bean.Place;
import net.onest.zhuanglitong.common.DbConnection;

public class PlaceDao {
	public ArrayList<Place> findAll(){
		ArrayList<Place> list=new ArrayList<Place>();
		Connection connection=(Connection) DbConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from place");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
		    	Place place=new Place();
		    	place.setName(rs.getString("name"));
		    	place.setAddress(rs.getString("address"));
		    	place.setDetail(rs.getString("detail"));
		    	place.setImage(rs.getString("image"));
		        //把每次循环news的值，都存放到数组list中；
		        list.add(place);
		     }     
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
