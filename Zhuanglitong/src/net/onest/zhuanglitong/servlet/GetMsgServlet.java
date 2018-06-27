package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.onest.zhuanglitong.bean.ConMessage;
import net.onest.zhuanglitong.bean.User;
import net.onest.zhuanglitong.dao.FriendDao;
import net.onest.zhuanglitong.dao.MessageDao;

/**
 * Servlet implementation class GetMsgServlet
 */
@WebServlet("/GetMsgServlet")
public class GetMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String sendName = request.getParameter("sendName");
		String receiveName = request.getParameter("receiveName");
		System.out.println(sendName);
		System.out.println(receiveName);
		MessageDao messageDao = new MessageDao();
		List<ConMessage> msgList = messageDao.getMsg(sendName, receiveName);
		if(msgList.size()!=0) {
			System.out.println(msgList.get(0).getName()+"servlet");
		}
		
		Gson gson = new Gson();
		String msgListStr = gson.toJson(msgList);
		System.out.println(msgListStr);
		response.getWriter().append(msgListStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
