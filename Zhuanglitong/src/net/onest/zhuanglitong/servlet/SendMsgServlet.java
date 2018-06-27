package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.onest.zhuanglitong.dao.MessageDao;

/**
 * Servlet implementation class SendMsgServlet
 */
@WebServlet("/SendMsgServlet")
public class SendMsgServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMsgServlet() {
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
		String sendName = request.getParameter("sendName");
		String receiveName = request.getParameter("receiveName");
		String date = request.getParameter("date");
		String message = request.getParameter("message");
		MessageDao messageDao = new MessageDao();
		boolean flag = messageDao.sendMsg(sendName,receiveName,date,message);
		if(flag) {
			response.getWriter().append("success");
			System.out.println("send success");
		}else {
			response.getWriter().append("failure");
			System.out.println("send failure");
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
