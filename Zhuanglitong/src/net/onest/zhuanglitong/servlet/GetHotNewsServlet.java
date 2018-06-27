package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.onest.zhuanglitong.bean.News;
import net.onest.zhuanglitong.dao.NewsDao;

/**
 * 得到热门推荐列表
 */
@WebServlet("/GetHotNewsServlet")
public class GetHotNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHotNewsServlet() {
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
		String title = request.getParameter("title");
		System.out.println("test11");
		NewsDao newsDao = new NewsDao();
		ArrayList<News> list = newsDao.SelectHot(title);
		Gson gson = new Gson();
		String newsListStr = gson.toJson(list);
		System.out.println("test111");
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
