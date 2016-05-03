package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProductManagerDao;
import com.google.gson.Gson;
import com.json.pojo.JsonData;
import com.pojo.Product;

/**                    
 * @Filename ProductServlet.java
 *
 * @Description 商品展示
 *
 * @author tcc 2016年5月3日
 *
 * @email 450518053@qq.com
 *
 */
public class ProductServlet extends HttpServlet {
	
	/** Comment for <code>serialVersionUID</code> */
	private static final long				serialVersionUID	= 829455565661482388L;
	
	private static final ProductManagerDao	prodDAO				= new ProductManagerDao();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
																		IOException {
		resp.setCharacterEncoding("UTF-8"); //设置字符集
		PrintWriter out = resp.getWriter();
		String prodName = req.getParameter("prod_name");
		Product prodParam = new Product();
		prodParam.setProdName(prodName);
		int rows = Integer.parseInt(req.getParameter("rows"));
		int page = Integer.parseInt(req.getParameter("page"));
		JsonData json = new JsonData();
		json.setTotal(prodDAO.getCount(prodParam));
		json.setRows(prodDAO.getProds(prodParam, rows, page));
		Gson gson = new Gson();
		out.println(gson.toJson(json));
		out.flush();
		out.close();
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
																		IOException {
		super.doPost(req, resp);
	}
	
}
