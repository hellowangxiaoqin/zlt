package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.onest.zhuanglitong.dao.UserDao;

/**
 * Servlet implementation class StoreEmail
 */
@WebServlet("/StoreEmail")
public class StoreEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreEmail() {
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
		String email = request.getParameter("email");
		System.out.println(email);
		String userId = request.getParameter("userId");
		System.out.println(userId);
		int userId1 = Integer.valueOf(userId);
		UserDao userDao = new UserDao();
		boolean flag = userDao.storeEmail(email, userId1);
		
		if(flag) {
			response.getWriter().append("³É¹¦");
		}else {
			response.getWriter().append("Ê§°Ü");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
