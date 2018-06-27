package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.onest.zhuanglitong.dao.UserDao;

/**
 * Servlet implementation class SaveIdentity
 */
@WebServlet("/SaveIdentity")
public class SaveIdentity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveIdentity() {
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
		String realName = request.getParameter("realName");
		System.out.println(realName);
		String number = request.getParameter("number");
		System.out.println(number);
		String userId = request.getParameter("userId");
		System.out.println(userId);
		int userId1 = Integer.valueOf(userId);
		UserDao userDao = new UserDao();
		boolean flag = userDao.saveIdentity(realName, number,userId1);
		
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
