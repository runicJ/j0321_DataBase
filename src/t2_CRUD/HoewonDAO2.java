package t2_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

//import com.mysql.jdbc.Statement;

public class HoewonDAO2 {
	private Connection conn = null;  // url, user, passwor 관리 db 연동
	
	
	public HoewonDAO2() {
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
			Statement stmt = conn.createStatement();  // 테이블 사용하기 위한 명령어를 관리, 준비  // conn의 도움에 의해서 state객체가 만들어져야함 // 원하는 데이터 붙들어옴.
			String sql = "Select * from hoewon";  // 검색해서 가져와서 보여주는
			ResultSet rs = stmt.executeQuery(sql);  // select 명령 spl 쿼리를 실행해라 // statement객체에 의해서 제어 // sql문=> 변수로 생성  // 검색해서 가져와서 담는 그릇  // 반환할 내용이 없으면 rs 필요x  // 저장, 삭제 하고 끝나면 rs객체가 필요없음.  // select 사용할때 필요 아니면 필요없음.
			
			while(rs.next()) {  // 자료가 있으면 돌아가도록
				System.out.println("번호 : " + rs.getInt("idx"));
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("나이 : " + rs.getInt("age"));
				System.out.println("성별 : " + rs.getString("gender"));
				System.out.println("주소 : " + rs.getString("address"));
				System.out.println();
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}
}
