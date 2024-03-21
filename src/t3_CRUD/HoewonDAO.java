package t3_CRUD;

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
	public HoewonVO getList() {
		HoewonVO vo = new HoewonVO();
		try {
			// DB 연결
			Statement stmt = conn.createStatement();  // 테이블 제어준비 db 접근해서 쿼리를 사용할 수 있게 해줌 // conn의 도움을 받아서
			String sql = "Select * from hoewon";
			ResultSet rs = stmt.executeQuery(sql);  // statement객체에 의해서 제어 // sql문=> 변수로 생성 // 실행된 객체를 담는

			if(rs.next()) {  // 자료가 있는가 없으면 통과
				vo.setIdx(rs.getInt("idx"));  // vo.setIdx(0) 0도 가능(select로 가져오면 0부터)
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
		return vo;
	}

}
