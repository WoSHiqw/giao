package com.oraclewdp.ddbookmarket.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.oraclewdp.ddbookmarket.biz.SmallTypeBiz;
import com.oraclewdp.ddbookmarket.biz.impl.SmallTypeBizImpl;
import com.oraclewdp.ddbookmarket.model.SmallType;

/**
 * Servlet implementation class FindAllSmallTypeServlet
 */
@WebServlet("/findAllSmallType")
public class FindAllSmallTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllSmallTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strBid=request.getParameter("bid");
		int bid=Integer.parseInt(strBid);
		String callBack=request.getParameter("callback");
		SmallTypeBiz smallTypeBiz=new SmallTypeBizImpl();
		List<SmallType> ls=smallTypeBiz.findAllByBid(bid);
		 response.setContentType("text/javascript;charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 JSONArray jsonArray=new JSONArray(ls);
		 out.println(callBack+"("+jsonArray.toString()+")");
		 out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
