package net.onest.zhuanglitong.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import net.onest.zhuanglitong.bean.UserSign;
import net.onest.zhuanglitong.dao.SignDao;

/**
 * Servlet implementation class insertSignMessage
 */
public class insertSignMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertSignMessage() {
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
		String signStr=stringBuffer.toString();
		Gson gson=new Gson();
		UserSign sign=gson.fromJson(signStr,UserSign.class);
		SignDao signDao=new SignDao();
		System.out.println(sign.getDate());
	    boolean f=signDao.setSignMessage(sign.getUserName(), sign.getUserId(),sign.getDate());
	    System.out.println(f);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
