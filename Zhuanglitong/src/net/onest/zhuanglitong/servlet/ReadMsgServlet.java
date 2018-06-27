package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.onest.zhuanglitong.dao.MessageDao;

/**
 * Servlet implementation class ReadMsgServlet
 */
@WebServlet("/ReadMsgServlet")
public class ReadMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String sendName = request.getParameter("name");
		String receiveName = request.getParameter("nameReceive");
		System.out.println(sendName);
		System.out.println(receiveName);
		MessageDao messageDao = new MessageDao();
		Boolean flag = messageDao.readMsg(sendName, receiveName);
		if(flag) {
			response.getWriter().append("success");
			System.out.println("read success");
		}else {
			response.getWriter().append("failure");
			System.out.println("read failure");
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
