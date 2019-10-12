package image;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.JdbcUtil;

public class ImageDemo {
	// 将图片插入数据库
	public static void readImage2DB() {
		String path = "D:/1.jpg";
		Connection conn = null;
		PreparedStatement ps = null;
		FileInputStream in = null;
		try {
			in = ImageUtil.readImage(path);
			conn = JdbcUtil.getConnection();
			String sql = "insert into pic(pic_name,pic_content) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "ZYL");
			ps.setBinaryStream(2, in, in.available());
			int count = ps.executeUpdate();
			if (count > 0) {
				System.out.println("插入成功");
			} else {
				System.out.println("插入失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeConn(conn);
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 读取数据库中的图片
	public static void readDB2Image() {
		String targetPath = "D:/image/2.jpg";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from pic where pic_name = 'ZYL'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				InputStream in = rs.getBinaryStream("pic_content");
				ImageUtil.readBin2Image(in, targetPath);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.closeConn(conn);
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

	// 测试
	public static void main(String[] args) {
		//readImage2DB();
		readDB2Image();
	}

}
