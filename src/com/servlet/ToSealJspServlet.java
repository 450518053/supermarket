package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SealManagerDao;
import com.pojo.Product;


/**   
 * @ClassName: ToSealJsp  
 * @Description: 转发到销售页面  
 *     
 */
public class ToSealJspServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2714397854208280051L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Product> list = new SealManagerDao().getProduct();
		req.getSession().setAttribute("products", list);
		req.getRequestDispatcher("../powerManage/seal.jsp").forward(req,resp);
	}

}
