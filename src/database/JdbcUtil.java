package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.management.RuntimeErrorException;

import org.apache.xml.resolver.apps.resolver;


public class JdbcUtil {

	 private static String driverClass;
	 private static String url;
	 private static String user;
	 private static String password;
	 static{
		 try{
			 InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("config.properties");
			 Properties props = new Properties();
			 driverClass = props.getProperty("driverClass");
			 url = props.getProperty("url");
			 user = props.getProperty("user");
			 password = props.getProperty("password");
			 in.close();
		 } catch(IOException e){
			 throw new ExceptionInInitializerError("��ȡ���ݿ������ļ���Ϣʧ��");
		 }
		 
		 try {
			Class.forName(driverClass);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError("��������ʧ��");
		}
		 
	 }
	 
	 public static Connection getConnection(){
		 try{
			 Connection conn = DriverManager.getConnection(url,user,password);
			 return conn;
		 } catch(Exception e){
			 throw new RuntimeException("�������ݿ��url���û����������,�������������ļ�");
		 }
	 }
	 
	 public static void release(ResultSet rs,Statement stmt,Connection conn){
		 if(rs!= null){
			 try{
				 rs.close();
			 } catch(SQLException e){
				 e.printStackTrace();
			 }
			 rs  = null;
		 }
		 if(stmt != null){
			 try{
				 stmt.close();
			 } catch(SQLException e){
				 e.printStackTrace();
			 }
			 stmt = null;
		 }
		 if (conn != null){
			 try{
				 conn.close();
			 } catch(SQLException e){
				 e.printStackTrace();
			 }
			 conn = null;
			 
		 }
	 }
}
