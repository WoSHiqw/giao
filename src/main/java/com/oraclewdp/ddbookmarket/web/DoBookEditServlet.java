package com.oraclewdp.ddbookmarket.web;

import com.oraclewdp.ddbookmarket.biz.BookBiz;
import com.oraclewdp.ddbookmarket.biz.impl.BookBizImpl;
import com.oraclewdp.ddbookmarket.model.Book;
import com.oraclewdp.ddbookmarket.util.MyBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

/**
 * Servlet implementation class BookAddServlet
 */
@WebServlet("/doBookEdit")
@MultipartConfig
public class DoBookEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DoBookEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book=new Book();
		MyBeanUtils.populate(book, request.getParameterMap(),"yyyy-MM-dd");
		String newFile=null;
		Part part=request.getPart("photo");
		if(part.getHeader("Content-Disposition").contains("; filename=")){
			//判断是否上传文件要先判断null后判断空字符串
			if(part.getSubmittedFileName()!=null&&!part.getSubmittedFileName().equals("")){
			String ext=part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf(".")+1);
			newFile=UUID.randomUUID()+"."+ext;
			part.write(request.getServletContext().getRealPath("upload/"+newFile));
			}
		}
		book.setPhoto(newFile);
		BookBiz bookBiz=new BookBizImpl();
		boolean ret=bookBiz.update(book);
		if (ret) {
			response.sendRedirect("bookList");
		} else {
			request.setAttribute("bid",request.getParameter("bid"));
			request.setAttribute("book",book);
			request.getRequestDispatcher("/bookEdit.jsp").forward(request, response);
		}
	}

}
