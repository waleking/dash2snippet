package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private String errMessage;// 错误信息
	private Connection connection;
	public String url=""; 
	
	public DBConn(){
		
	}

	public DBConn(String url) {
		this.url=url;
		try {
			//url = "jdbc:sqlite:/Volumes/MacintoshHD/Users/huangwaleking/Library/Application Support/Dash/library.dash";
			// 创建连接
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);
			System.out.println("connect to sqlite");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public void close() {
		try {
			if (this.connection != null)
				this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		//DBConn db = new DBConn();
		//db.getConnection();
	}
}
