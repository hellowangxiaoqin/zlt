package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.google.gson.Gson;

import net.onest.zhuanglitong.bean.News;
import net.onest.zhuanglitong.dao.NewsDao;


/**
 * 得到新闻的一个列表
 */
@WebServlet("/GetPageNewsServlet")
public class GetPageNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPageNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int pagesize = 3;
		
//		String pagenumm = request.getParameter("pagenum");
//		int pagenum = Integer.parseInt(pagenumm);
		int pagenum = 1;
		System.out.println("test");
		NewsDao newsDao = new NewsDao();
		List<News> list = newsDao.Select(pagenum, pagesize);
		Gson gson = new Gson();
		String newsListStr = gson.toJson(list);
		System.out.println("test1");
		response.getWriter().append(newsListStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
