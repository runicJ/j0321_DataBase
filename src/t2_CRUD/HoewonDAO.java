package t2_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

//import com.mysql.jdbc.Statement;

public class HoewonDAO {
	private Connection conn = null;
	
	
	public HoewonDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String password = "1234";
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패~~");
		}
	}
	
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	// 전체 회원 조회
	public void getList() {
		try {
			Statement stmt = conn.createStatement();  // 테이블 제어준비  // conn의 도움을 받아서
			String sql = "Select * from hoewon";
			ResultSet rs = stmt.executeQuery(sql);  // statement 객체에 의해서 제어 // sql문 => 변수로 생성
			
			rs.next();  // Bof| | | |Eof  rs로 다음으로 넘어감
			System.out.println("번호 : " + rs.getInt("idx"));  // 내용이 있으면 꺼내는 걸로  // 타입이 중요 타입으로 접근(테이블의 필드명) // 실행을 해야 접속을 해서 내용을 찾아옴 
			System.out.println("이름 : " + rs.getString("name"));
			System.out.println("나이 : " + rs.getInt("age"));
			System.out.println("성별 : " + rs.getString("gender"));
			System.out.println("주소 : " + rs.getString("address"));
			System.out.println();
			
			rs.next();
			System.out.println("번호 : " + rs.getInt("idx"));
			System.out.println("이름 : " + rs.getString("name"));
			System.out.println("나이 : " + rs.getInt("age"));
			System.out.println("성별 : " + rs.getString("gender"));
			System.out.println("주소 : " + rs.getString("address"));
			System.out.println();
			
			rs.next();
			System.out.println("번호 : " + rs.getInt("idx"));
			System.out.println("이름 : " + rs.getString("name"));
			System.out.println("나이 : " + rs.getInt("age"));
			System.out.println("성별 : " + rs.getString("gender"));
			System.out.println("주소 : " + rs.getString("address"));
			System.out.println();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}
}
