package ayWord;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.poi.openxml4j.util.ZipSecureFile.ThresholdInputStream;

import database.JdbcUtil;

public class TestConnect {

	public void run() {
		System.out.println(this.getClass().getResource(""));
		System.out.println(this.getClass().getResource("/"));
		System.out.println(this.getClass().getClassLoader().getResource(""));
		System.out.println(this.getClass().getResource("config.properties"));
		InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("config.properties");
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String driverClass = props.getProperty("driverClass");
		System.out.println(driverClass);
	}

	public static void main(String ars[]) {

		Connection connection = JdbcUtil.getConnection();
		System.out.print("连接成功！");
		try {
			//connection.close();
			//System.out.print("连接关闭");
			Statement st=connection.createStatement();         
            ResultSet rs=st.executeQuery("select * from pic");
            if(rs.next()){
                System.out.println("查询到");
                String name = rs.getString("pic_name");
                System.out.println(name);
            }else {
                System.out.println("查询不到");
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		 
	}
}
