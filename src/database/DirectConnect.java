package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DirectConnect {

	public static void main(String arsg[]){
		 Connection conn=null;
	        Statement st=null;
	        ResultSet rs=null;
	        try {
	            //1、装载驱动
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        try {
	            //2、链接数据库，使用com.mysql.jdbc.Connection包会出错
	           	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybase?user=root&password=123456");
	            //3、创建连接语句
	            st=conn.createStatement();
	            //4、执行ＳＱＬ语句获得结果集
	            rs=st.executeQuery("select * from pic");
	}
	        catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
}
	}
