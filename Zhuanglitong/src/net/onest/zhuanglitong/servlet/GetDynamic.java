package net.onest.zhuanglitong.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.onest.zhuanglitong.bean.Dynamic;
import net.onest.zhuanglitong.dao.DynamicDao;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetDynamic
 */
@WebServlet("/GetDynamic")
public class GetDynamic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDynamic() {
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
		DynamicDao dynamicDao=new DynamicDao();
		List<Dynamic> dynamic=dynamicDao.selectAll();
		if(dynamic==null) {
			System.out.println("null");
		}else {
			System.out.println(dynamic.size());
		}
		Gson gson=new Gson();
		String dynamiclist=gson.toJson(dynamic);
		response.getWriter().append(dynamiclist);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
