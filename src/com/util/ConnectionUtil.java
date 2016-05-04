package com.util;

import java.sql.Connection;
import java.sql.DriverManager;


/**   
 * @ClassName: ConnectionUtil  
 * @Description:   数据库连接工具类
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
            Connection conn = DriverManager.getConnection("jdbc:mysql://114.55.87.106/supermarket?useUnicode=true&characterEncoding=UTF-8&user=root&password=123456");
            
            return conn;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
	}

}
