package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.onest.zhuanglitong.dao.UserDao;

/**
 * Servlet implementation class ChangePhone
 */
@WebServlet("/ChangePhone")
public class ChangePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePhone() {
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
		String phone = request.getParameter("phone");
		System.out.println(phone);
		String userId = request.getParameter("userId");
		System.out.println(userId);
		int userId1 = Integer.valueOf(userId);
		UserDao userDao = new UserDao();
		boolean flag = userDao.changePhone(phone, userId1);
		
		if(flag) {
			response.getWriter().append("�ɹ�");
		}else {
			response.getWriter().append("ʧ��");
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
