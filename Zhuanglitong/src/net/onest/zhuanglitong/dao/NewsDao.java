package net.onest.zhuanglitong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.onest.zhuanglitong.bean.News;
import net.onest.zhuanglitong.common.DbConnection;



public class NewsDao {
	//这个方法是用来获取总页数的，与下面的获取每页内容没有联系。
    public int getPage(int pagesize){
    	Connection connection = DbConnection.getConnection();
    	int pageCount = 0;
    	//用coun(*)获取当前表的条数，注意返回的只有一行数据，并且是整数；
    	PreparedStatement ps;
		try {
			ps = connection.prepareStatement("select count(*) from news");
			ResultSet rs = ps.executeQuery();
	        //那么rs.next()必然会指向下一个
	        rs.next();
	        //因为整数除整数必然是个整数，所以要乘以1.0变成小数，然后用Math.ceil获取 大于或者等于 当前数的 最小整数值；
	        pageCount=(int)Math.ceil(1.0*rs.getInt(1)/pagesize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return pageCount;
    }
  //输入两个参数，pagenum是第几页；pagesize是每页的行数
    public ArrayList<News> Select(int pagenum, int pagesize){
        //定义一个当前要获取的类的类型的数组；
        ArrayList<News> list=new ArrayList<News>();
        //mysql的分页语句；
        Connection connection = DbConnection.getConnection();
        /**
         * select * from 表名  limit  (pagenum-1)*pagesize, pagesize;

			pagenum是当前第几页，pagesize是每页的条数。

			那么（pagenum-1）*pagesize就表示翻过 这些条数，接着显示 pagesize  条。
         */
        
		try {
			PreparedStatement ps = connection.prepareStatement("select * from news limit ?,?");
	        ps.setInt(1, (pagenum-1)*pagesize);
	        ps.setInt(2, pagesize);
	        ResultSet rs = ps.executeQuery();
	        //如果rs.next是true，首先把要赋值的类实例化，那么通过while循环将每个字段元素赋值到相应的类的变量中，
	        while(rs.next()){
	            News news=new News();
	            news.setNewsImages(rs.getString("newsImages"));
	            news.setTitle(rs.getString("title"));
	            news.setContent(rs.getString("content"));
	            //把每次循环news的值，都存放到数组list中；
	            list.add(news);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //最后返回出去
        return list;
    }
	public ArrayList<News> SelectHot(String title) {
		 ArrayList<News> list=new ArrayList<News>();
	 
	     Connection connection = DbConnection.getConnection();
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("select * from news where tag=?");
			ps.setString(1, "hot");
	        ResultSet rs = ps.executeQuery();
	        
	        while(rs.next()){
	        	if(rs.getString("title") != title) {
	        		News news=new News();
		            news.setNewsImages(rs.getString("newsImages"));
		            news.setTitle(rs.getString("title"));
		            news.setContent(rs.getString("content"));
		            //把每次循环news的值，都存放到数组list中；
		            list.add(news);
	        	}
	            
	            
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //最后返回出去
        return list;
	}
	public ArrayList<News> SelectNext(String title,int pagesize) {
		// TODO Auto-generated method stub
		 ArrayList<News> list=new ArrayList<News>();
		 
	     Connection connection = DbConnection.getConnection();
	     int id =0;
	     try {
				PreparedStatement ps = connection.prepareStatement("select * from news where title=?");
				ps.setString(1, title);
		        ResultSet rs = ps.executeQuery();
		        while(rs.next()) {
		        	id = rs.getInt("newsId");
		        	System.out.println(id);
		        }
		        
		        int pageCount =(int)Math.ceil(1.0*id/pagesize);
		        int pagenum = pageCount+1;
		    	System.out.println(pagenum);
				PreparedStatement ps1 = connection.prepareStatement("select * from news limit ?,?");
			    ps1.setInt(1, (pagenum-1)*pagesize);
			    ps1.setInt(2, pagesize);
			    ResultSet rs1 = ps1.executeQuery();
			    //如果rs.next是true，首先把要赋值的类实例化，那么通过while循环将每个字段元素赋值到相应的类的变量中，
			    while(rs1.next()){
			    	News news1=new News();
			        news1.setNewsImages(rs1.getString("newsImages"));
			        news1.setTitle(rs1.getString("title"));
			        news1.setContent(rs1.getString("content"));
			        //把每次循环news的值，都存放到数组list中；
			        list.add(news1);
			     }     
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        //最后返回出去
	        return list;
	}
}
