package net.onest.zhuanglitong.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.onest.zhuanglitong.bean.User;
import net.onest.zhuanglitong.dao.UserDao;

/**
 * Servlet implementation class getUserMessage
 */
@WebServlet("/getUserMessage")
public class getUserMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUserMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("接收到客户端的请求");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//String userId = request.getParameter("userId");
		InputStream is=request.getInputStream();
		//字符流
		BufferedReader reader=new BufferedReader(new InputStreamReader(is,"utf-8"));
		StringBuffer stringBuffer=new StringBuffer();
		String str=null;
		while((str=reader.readLine())!=null) {
			stringBuffer.append(str);
		}
		String userStr=stringBuffer.toString();
		System.out.println(userStr);
		Gson gson=new Gson();
		String userid=gson.fromJson(userStr, String.class);
		int user_id=Integer.parseInt(userid);
		System.out.println(user_id);
		UserDao userdao=new UserDao();
		//通过用户id来获取用户名的用户信息
		User user=userdao.getUserMessage(user_id);
		
		//将用户信息传入到客户端
		String userStr1 = gson.toJson(user);
		System.out.println("传入客户端的用户信息："+userStr1);
		response.getWriter().write(userStr1);
//		System.out.println(userStr1);
//		String userStr = gson.toJson(user);
//		System.out.println(userStr);
//		response.getWriter().append(userStr);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
