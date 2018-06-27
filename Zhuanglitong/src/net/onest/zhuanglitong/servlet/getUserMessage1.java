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
 * Servlet implementation class getUserMessage1
 */
@WebServlet("/getUserMessage1")
public class getUserMessage1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUserMessage1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		request.setCharacterEncoding("utf-8");
		//String userId = request.getParameter("userId");
		InputStream is=request.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(is,"utf-8"));
		StringBuffer stringBuffer=new StringBuffer();
		String str=null;
		while((str=reader.readLine())!=null) {
			stringBuffer.append(str);
		}
		String userStr=stringBuffer.toString();
		System.out.println(userStr);
		Gson gson=new Gson();
		String userName=gson.fromJson(userStr, String.class);
		System.out.println(userName);
		UserDao userdao=new UserDao();
		User user=userdao.getUserMessage1(userName);
		String userStr1 = gson.toJson(user);
		System.out.println(userStr1);
		response.getWriter().write(userStr1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
