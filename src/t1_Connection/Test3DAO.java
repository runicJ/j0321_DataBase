package t1_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test3DAO {
	Connection conn = null;  // 선언  /1
	
	public Test3DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");   // driver class 유무 /2
			
			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String password = "1234";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연결 실패~~~" + e.getMessage());
		}
	}
	
	public void connClose() {
		try {
			conn.close();
			System.out.println("DB 연결 종료!!");
		} catch (SQLException e) {}
	}
}
