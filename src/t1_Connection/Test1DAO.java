package t1_Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

// DAO : Database Access Object
// 데이터베이스 관련된 사항 담기
public class Test1DAO {  // DAO<--VO/DTO-->DB  // Main | Service | VO/DTO | DAOs

	// 생성할 때 1번 체크하기
	public Test1DAO() {

		try {
			// 1. JDBC 드라이버 검색
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 검색 성공!!!");

			// 2. DB서버에 연결(연동) 후 데이터베이스 사용준비 작업
			String usl = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String password = "1234";
			DriverManager.getConnection(usl, user, password);
			System.out.println("데이터베이스 연결 성공!!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~~");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패~~~"); // url 확인
		} finally {
			System.out.println("작업끝!!");
		}
	}

}
