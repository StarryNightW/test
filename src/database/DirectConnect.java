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
	            //1��װ������
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        try {
	            //2���������ݿ⣬ʹ��com.mysql.jdbc.Connection�������
	           	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybase?user=root&password=123456");
	            //3�������������
	            st=conn.createStatement();
	            //4��ִ�Уӣѣ�����ý����
	            rs=st.executeQuery("select * from pic");
	}
	        catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
}
	}
