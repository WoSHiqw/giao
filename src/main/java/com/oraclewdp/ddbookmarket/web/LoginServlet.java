package com.oraclewdp.ddbookmarket.web;

import com.oraclewdp.ddbookmarket.biz.AdminBiz;
import com.oraclewdp.ddbookmarket.biz.impl.AdminBizImpl;
import com.oraclewdp.ddbookmarket.model.Admin;
import com.oraclewdp.ddbookmarket.util.MyBeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Admin admin=new Admin();
		MyBeanUtils.populate(admin, request.getParameterMap());
        String vcode=request.getParameter("vcode");
        String serverVcode = (String) request.getSession().getAttribute("validateCode");
        /*if (!serverVcode.equalsIgnoreCase(vcode)){
            request.setAttribute("msg","验证码错误");
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }*/
        Map<String,String> errors=new HashMap<>();
        if (!serverVcode.equalsIgnoreCase(vcode)){
        errors.put("vcode","验证码错误");
       }
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator=validatorFactory.getValidator();
        Set<ConstraintViolation<Admin>> validaResult=validator.validate(admin);
        if (validaResult.size()>0){
            for (ConstraintViolation<Admin> cv: validaResult) {
                errors.put(cv.getPropertyPath().toString(),cv.getMessage());
            }
        }
        if (errors.size()>0){
            request.setAttribute("errors",errors);
            request.setAttribute("admin",admin);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
		AdminBiz adminBiz =new AdminBizImpl();
		boolean ret = adminBiz.findByNameAndPwd(admin);
		if (ret) {
			request.getSession().setAttribute("hasLogined",true);
			request.getRequestDispatcher("min.jsp").forward(request, response);
		} else {
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}