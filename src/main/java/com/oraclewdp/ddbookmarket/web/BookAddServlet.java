package com.oraclewdp.ddbookmarket.web;

import com.oraclewdp.ddbookmarket.biz.BookBiz;
import com.oraclewdp.ddbookmarket.biz.impl.BookBizImpl;
import com.oraclewdp.ddbookmarket.model.Book;
import com.oraclewdp.ddbookmarket.util.MyBeanUtils;
import com.oraclewdp.ddbookmarket.util.StringEscapeUtils;

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
@WebServlet("/bookAdd")
@MultipartConfig
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
			part.write(request.getServletContext().getRealPath("/upload/"+newFile));
			}
		}
		book.setPhoto(newFile);
		book.setDescri(StringEscapeUtils.htmlEncode(book.getDescri()));
		BookBiz bookBiz=new BookBizImpl();
		boolean ret=bookBiz.save(book);
		if (ret) {
			response.sendRedirect("bookList");
		} else {
			request.setAttribute("book", book);
			request.getRequestDispatcher("/bookAdd.jsp").forward(request, response);;
		}
	}

}
