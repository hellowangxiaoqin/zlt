package net.onest.zhuanglitong.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import net.onest.zhuanglitong.bean.UserSign;
import net.onest.zhuanglitong.dao.SignDao;

/**
 * Servlet implementation class getSignMessage
 */
@WebServlet("/getSignMessage")
public class getSignMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSignMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("接收到客户端的请求");
		request.setCharacterEncoding("utf-8");
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
		String userName=gson.fromJson(userStr, String.class);
		SignDao signDao=new SignDao();
		//通过用户名来获取用户的签到信息
	    List<UserSign> list=new ArrayList<UserSign>();
	    list=signDao.getSignMessage(userName);
		//将用户信息传入到客户端
		String signStr = gson.toJson(list);
		response.getWriter().append(signStr);
		System.out.println(signStr);
	
	
	
	
	
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
