package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.onest.zhuanglitong.bean.User;
import net.onest.zhuanglitong.dao.FriendDao;

/**
 * Servlet implementation class FindNewFriendServlet
 */
@WebServlet("/FindNewFriendServlet")
public class FindNewFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindNewFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String loginName = request.getParameter("loginName");
		String searchName = request.getParameter("searchName");
		System.out.println("--------"+loginName+","+searchName);
		FriendDao friendDao = new FriendDao();
		List<User> someUserList = friendDao.findNewFriend(loginName,searchName);
//		if(someUserList.size() != 0) {
//			System.out.println(someUserList.get(0).getUserName()+"servlet");
//		}
		Gson gson = new Gson();
		String someUserListStr = gson.toJson(someUserList);
		System.out.println(someUserListStr);
		response.getWriter().append(someUserListStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
