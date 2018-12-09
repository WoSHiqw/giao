package com.oraclewdp.ddbookmarket.web;

import com.oraclewdp.ddbookmarket.biz.BigTypeBiz;
import com.oraclewdp.ddbookmarket.biz.impl.bigTypeBizImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/bigTypeadd")
public class BigTypeAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BigTypeAddServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//这样写代码重复，写在filter里
		/*if (request.getSession().getAttribute("hasLogined")==null||!(boolean)request.getSession().getAttribute("hasLogined")){
			response.sendRedirect("login.jsp");
			return;
		}*/
    	String name=request.getParameter("name");
		BigTypeBiz bigTypeBiz=new bigTypeBizImpl();
		boolean ret=bigTypeBiz.save(name);
		if (ret) {
			response.sendRedirect("min.jsp");
		} else {
			request.setAttribute("name", name);
			request.getRequestDispatcher("/bigTypeadd.jsp").forward(request, response);
		}
	}

}
