package net.onest.zhuanglitong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.onest.zhuanglitong.bean.News;
import net.onest.zhuanglitong.common.DbConnection;



public class NewsDao {
	//���������������ȡ��ҳ���ģ�������Ļ�ȡÿҳ����û����ϵ��
    public int getPage(int pagesize){
    	Connection connection = DbConnection.getConnection();
    	int pageCount = 0;
    	//��coun(*)��ȡ��ǰ���������ע�ⷵ�ص�ֻ��һ�����ݣ�������������
    	PreparedStatement ps;
		try {
			ps = connection.prepareStatement("select count(*) from news");
			ResultSet rs = ps.executeQuery();
	        //��ôrs.next()��Ȼ��ָ����һ��
	        rs.next();
	        //��Ϊ������������Ȼ�Ǹ�����������Ҫ����1.0���С����Ȼ����Math.ceil��ȡ ���ڻ��ߵ��� ��ǰ���� ��С����ֵ��
	        pageCount=(int)Math.ceil(1.0*rs.getInt(1)/pagesize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return pageCount;
    }
  //��������������pagenum�ǵڼ�ҳ��pagesize��ÿҳ������
    public ArrayList<News> Select(int pagenum, int pagesize){
        //����һ����ǰҪ��ȡ��������͵����飻
        ArrayList<News> list=new ArrayList<News>();
        //mysql�ķ�ҳ��䣻
        Connection connection = DbConnection.getConnection();
        /**
         * select * from ����  limit  (pagenum-1)*pagesize, pagesize;

			pagenum�ǵ�ǰ�ڼ�ҳ��pagesize��ÿҳ��������

			��ô��pagenum-1��*pagesize�ͱ�ʾ���� ��Щ������������ʾ pagesize  ����
         */
        
		try {
			PreparedStatement ps = connection.prepareStatement("select * from news limit ?,?");
	        ps.setInt(1, (pagenum-1)*pagesize);
	        ps.setInt(2, pagesize);
	        ResultSet rs = ps.executeQuery();
	        //���rs.next��true�����Ȱ�Ҫ��ֵ����ʵ��������ôͨ��whileѭ����ÿ���ֶ�Ԫ�ظ�ֵ����Ӧ����ı����У�
	        while(rs.next()){
	            News news=new News();
	            news.setNewsImages(rs.getString("newsImages"));
	            news.setTitle(rs.getString("title"));
	            news.setContent(rs.getString("content"));
	            //��ÿ��ѭ��news��ֵ������ŵ�����list�У�
	            list.add(news);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //��󷵻س�ȥ
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
		            //��ÿ��ѭ��news��ֵ������ŵ�����list�У�
		            list.add(news);
	        	}
	            
	            
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //��󷵻س�ȥ
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
			    //���rs.next��true�����Ȱ�Ҫ��ֵ����ʵ��������ôͨ��whileѭ����ÿ���ֶ�Ԫ�ظ�ֵ����Ӧ����ı����У�
			    while(rs1.next()){
			    	News news1=new News();
			        news1.setNewsImages(rs1.getString("newsImages"));
			        news1.setTitle(rs1.getString("title"));
			        news1.setContent(rs1.getString("content"));
			        //��ÿ��ѭ��news��ֵ������ŵ�����list�У�
			        list.add(news1);
			     }     
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        //��󷵻س�ȥ
	        return list;
	}
}
