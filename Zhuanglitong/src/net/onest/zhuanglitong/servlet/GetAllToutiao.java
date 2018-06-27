package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.onest.zhuanglitong.bean.Place;
import net.onest.zhuanglitong.bean.TouTiao;
import net.onest.zhuanglitong.dao.PlaceDao;
import net.onest.zhuanglitong.dao.ToutiaoDao;

/**
 * Servlet implementation class GetAllToutiao
 */
@WebServlet("/GetAllToutiao")
public class GetAllToutiao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllToutiao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		ToutiaoDao toutiaoDao = new ToutiaoDao();
		ArrayList<TouTiao> list = toutiaoDao.findAll();
		Gson gson = new Gson();
		String toutiaoListStr = gson.toJson(list);
		System.out.println("toutiao");
		response.getWriter().append(toutiaoListStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
