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

import com.oraclewdp.ddbookmarket.biz.BigTypeBiz;
import com.oraclewdp.ddbookmarket.biz.impl.bigTypeBizImpl;
import com.oraclewdp.ddbookmarket.model.BigType;

@WebServlet("/findAllBigType")
public class FindAllBigTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FindAllBigTypeServlet() {
        super();

    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String callBack=request.getParameter("callback");
		 BigTypeBiz bigTypeBiz=new bigTypeBizImpl();
		 List<BigType> ls=bigTypeBiz.findAllBigType();
		 //告诉发送的是js
		 response.setContentType("text/javascript;charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 JSONArray jsonArray=new JSONArray(ls);
		 out.println(callBack+"("+jsonArray.toString()+")");
		 out.flush();
	}

}
