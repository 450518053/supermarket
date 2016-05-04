package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProductManagerDao;
import com.dao.UserMangerDao;
import com.google.gson.Gson;
import com.json.pojo.JsonData;
import com.pojo.Product;
import com.pojo.UUserLogin;

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
		String prodNo = req.getParameter("prodNo");
		System.out.println(prodDAO.getById(prodNo));
		req.getSession().setAttribute("bean", prodDAO.getById(prodNo));
		req.getRequestDispatcher("../powerManage/UpdateProduct.jsp").forward(req,resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
																		IOException {
		resp.setCharacterEncoding("UTF-8"); //设置字符集
		String type = req.getParameter("type");
		String prodName = req.getParameter("prodName");
		String prodNo = req.getParameter("prodNo");
		String commoNo = req.getParameter("commoNo");
		Product prodParam = null;
		PrintWriter out = null;
		switch (Integer.valueOf(type)) {
			case 1://查询
				out = resp.getWriter();
				prodParam = new Product();
				prodParam.setProdName(prodName);
				prodParam.setProdNo(prodNo);
				prodParam.setCommoNo(commoNo);
				int rows = Integer.parseInt(req.getParameter("rows"));
				int page = Integer.parseInt(req.getParameter("page"));
				JsonData json = new JsonData();
				json.setTotal(prodDAO.getCount(prodParam));
				json.setRows(prodDAO.getProds(prodParam, rows, page));
				Gson gson = new Gson();
				out.println(gson.toJson(json));
				out.flush();
				out.close();
				break;
			case 2://新增、修改	提交
				String prop = req.getParameter("prop");
				double marketPrice = Double.valueOf(req.getParameter("marketPrice"));
				double sellPrice =  Double.valueOf(req.getParameter("sellPrice"));
				int salesVolume = Integer.valueOf(req.getParameter("salesVolume"));
				out = resp.getWriter();
				prodParam = new Product();
				prodParam.setProdName(prodName);
				prodParam.setProdNo(prodNo);
				prodParam.setCommoNo(commoNo);
				prodParam.setMarketPrice(marketPrice);
				prodParam.setProp(prop);
				prodParam.setSellPrice(sellPrice);
				prodParam.setSalesVolume(salesVolume);
				out.println(prodDAO.update(prodParam)?"success":"error");
				out.flush();
				out.close();
				break;
			default:
				break;
		}
		
	}
	
}
