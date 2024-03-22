package t5_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;  // 보안 // 웹으로 넘어온 것은 무조건 문자로 // ?(뭐가들어올지모름)에는 ''이 생략된 것으로 봄. // sql문을 실행할때 넣는(statement 객체) 것이 아니라 생성할때 넣음 // sql문이 먼저써야 pstmt 생성이됨. 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SungjukDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	SungjukVO vo = null;
	String sql = "";  // 여기까지 기본(db만들고 VO 만들고 DAO 만들러옴)

	public SungjukDAO() {
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패~~" + e.getMessage());
		}
	}
	
	// conn객체 Close
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	// pstmt객체 Close
	public void pstmtClose() {
		try {
			if(pstmt != null) pstmt.close();			
		} catch (Exception e) {}
	}
	
	// rs객체 Close
	public void rsClose() {
		try {  // try 먼저 생성
			if(rs != null) rs.close();  // rs는 pstmt 열어야 사용 같이 닫기
			pstmtClose();
		} catch (Exception e) {}
	}

	// 성적자료 입력처리
	public int setSungjukInput(SungjukVO vo) {
		int res = 0;
		try {  // try ctrl space 예외처리 먼저
			sql = "insert into sungjuk values (default,?,?,?,?)";  // 타입을 맞춰줘야함 // default를 제외한건 숫자든 문자든 ?(''가 있다고 생각)
			pstmt = conn.prepareStatement(sql);  // 생성할때 sql 넣음(statement는 실행할때 넣었음)
			pstmt.setString(1, vo.getName()); //실행 전에 ?에 타입 알려줌  // 1은 첫번째 물음표
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			res = pstmt.executeUpdate();  // 정상처리가 되었는지 확인하려고 res에 담음.  //stmt는 여기에 넣었음  //result변수
		} catch (SQLException e) {  // sql문 오류 생각 //sql문이 없어서 빨간줄
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();  // insert // 여기까지 적고 try 안 입력
		}
		return res;  // return 0 리턴타입에 초기값으로 기본적으로 생성
	}
	
	// 성명조회(개별검색/동명이인검색) 처리
	public SungjukVO getSungjukSearch(String name) {
		SungjukVO vo = new SungjukVO();  // 안써도 되긴함	
		try {
			sql = "select * from sungjuk where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();  // 있으니까 들어온것
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));  // vo에 담는 것  // toString이 있어서 null값x
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("Kor"));
				vo.setEng(rs.getInt("Eng"));
				vo.setMat(rs.getInt("Mat"));
			}
			else vo = null;  // 이렇게 안하면 vo.get~~이렇게 해야함
		} catch (SQLException e) {  // 복사해옴
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}
	
	// 전체 자료 검색 처리
	public ArrayList<SungjukVO> getSungjukList() {
		ArrayList<SungjukVO> vos = new ArrayList<SungjukVO>();
		try {
			sql = "select * from sungjuk order by name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {  // 여러건, if는 1건
				vo = new SungjukVO(); // 담을 때는 매번 new로 생성.
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("Kor"));
				vo.setEng(rs.getInt("Eng"));
				vo.setMat(rs.getInt("Mat"));
				
				vos.add(vo); // vos 담고 더이상 자료가 없으면 넘어감.
			}
		} catch (SQLException e) {  // 복사해옴
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 성적자료 수정하기
	public int setSungjukUpdate(SungjukVO vo) {
		int res = 0;
		try {
			sql = "update sungjuk set name=?, kor=?, eng=?, mat=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			pstmt.setInt(5, vo.getIdx());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	public int setSungjukDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from sungjuk where idx=?";
			pstmt = conn.prepareCall(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}
