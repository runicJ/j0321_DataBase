package t1_Connection;

// import java.sql.*;

import java.sql.Connection;  // com.mysql.jdbc 로 사용하면 안됨
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test2DAO {
	Connection conn = null; // {}안에 있으면 그 안에서만 사용. 필드로 빼서 쓰자.
	
	public Test2DAO() {
		try {
			// 1. JDBC 드라이버 검색
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 검색 성공!!!");

			// 2. DB서버에 연결(연동) 후 데이터베이스 사용준비 작업
			String usl = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String password = "1234";
			conn = DriverManager.getConnection(usl, user, password);
			System.out.println("데이터베이스 연결 성공!!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~~");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패~~~"); // url 확인
		} finally {
//			try {
//				System.out.println("데이터베이스 연결 끊기 성공!!!");  // 여기에 넣으면 실행x
//				conn.close();
//			} catch (SQLException e) {}  // 닫는데 에러 생길 일은 거의 없음.
				connClose();  // 메소드 부름.
			System.out.println("작업끝!!");
		}
	}
	
	// conn객체 close
	public void connClose() {  // 메소드로 따로 뺌
		try {
			conn.close();
			System.out.println("데이터베이스 연결 끊기 성공!!!");
		} catch (SQLException e) {}
	}
}

// input|output(io)에 관한것 강제로 닫아야 함. Scanner같은것  => 생성자에서 닫으면 안됨. 닫는 메소드를 만들어만 놓음.