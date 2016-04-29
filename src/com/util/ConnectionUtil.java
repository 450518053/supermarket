package com.util;

import java.sql.Connection;
import java.sql.DriverManager;


/**   
 * @ClassName: ConnectionUtil  
 * @Description:   数据库连接工具类
 * @author: marx.liao
 * @date 2016-4-28 上午10:18:44  
 *     
 */
public class ConnectionUtil {
	
	public static Connection getDBConnection( ){
		//尝试连接数据库
        try
        {
            //载入MySQL的JDBC驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //获得数据库连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/market?user=root&password=123456");
            
            return conn;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
	}

}
