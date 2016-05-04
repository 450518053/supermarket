package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SealManagerDao;
import com.pojo.Commodity;
import com.pojo.Product;

/**   
 * @ClassName: SealProductServlet  
 * @Description:  产品销售 
 *     
 */
public class SealProductServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3439714044361054599L;
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean flag1 = false;
		boolean flag2 = false;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		double totalPrice = 0;
		int totalNum = 0;
		for(int i=1;i<=Integer.valueOf(req.getParameter("size"));i++){
			totalNum += Integer.valueOf(req.getParameter("num"+i));
			totalPrice +=Double.valueOf(req.getParameter("totle"+i));
		}
		
		int order_no = new SealManagerDao().insertSellOrder(totalNum, totalPrice);
		flag1 = true;
		
		for(int i=1;i<=Integer.valueOf(req.getParameter("size"));i++){
			Product pro = new SealManagerDao().selectProductById(Integer.valueOf(req.getParameter("prodNo"+i)));
			Commodity com = new SealManagerDao().selectCommodityById(Integer.valueOf(req.getParameter("commoNo"+i).trim()));
			new SealManagerDao().insertOrderItem(pro, com, Integer.valueOf(req.getParameter("num"+i)), Double.valueOf(req.getParameter("totle"+i)), order_no);
		}
		flag2 = true;
		if(flag1&&flag2){
			out.println("success");
		}else{
			out.println("false");
		}
		out.flush();
		out.close();
	}

}
