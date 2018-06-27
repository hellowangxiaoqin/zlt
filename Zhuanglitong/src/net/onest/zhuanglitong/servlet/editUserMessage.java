package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.onest.zhuanglitong.bean.User;
import net.onest.zhuanglitong.dao.UserDao;
/**
 * Servlet implementation class editUserMessage
 */
@WebServlet("/editUserMessage")
public class editUserMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editUserMessage() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String image = request.getParameter("imgurl");
        String  userId=request.getParameter("userId");
		String detail=request.getParameter("name_more");
		String address=request.getParameter("city_more");
		String birth=request.getParameter("birth_more");
		String sex=request.getParameter("sex");
		System.out.println(sex);
		String phone=request.getParameter("phone");	
		User user=new User();
		user.setAddress(address);
		user.setBirth(birth);
		user.setDetail(detail);
		user.setUserName(username);
		user.setImage(image);
		user.setPassword(password);
		user.setPhone(phone);
		user.setSex(sex);
		user.setUserId( Integer.valueOf(userId).intValue());
		System.out.println(user);
		UserDao userDao=new UserDao();
		userDao.editUserMessage(user);
		boolean flag =userDao.editUserMessage(user);
		if(flag) {
			response.getWriter().append("success");
		}else {
			response.getWriter().append("failure");
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
