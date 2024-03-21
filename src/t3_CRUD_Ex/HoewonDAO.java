package t3_CRUD_Ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
		}
	}
	
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}

	public ArrayList<HoewonVO> getList() {
		ArrayList<HoewonVO> vos = new ArrayList<HoewonVO>();
	  Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "select * from hoewon";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				HoewonVO vo = new HoewonVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
		
		return vos;
	}

	public HoewonVO getSearch(String name) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from hoewon where name='"+name+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			HoewonVO vo = new HoewonVO();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
