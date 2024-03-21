package t3_CRUD;
////////////////////////////statement,rs 객체 닫아야함
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;

//import com.mysql.jdbc.Statement;

public class HoewonDAO2 {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String sql = null;
	
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
	public ArrayList<HoewonVO> getList() {
		ArrayList<HoewonVO> vos = new ArrayList<HoewonVO>();  // arraylist에 담는것
		try {
			// DB 연결
			stmt = conn.createStatement();  // 테이블 제어준비 db 접근해서 쿼리를 사용할 수 있게 해줌 // conn의 도움을 받아서
			sql= "select * from hoewon";
			rs = stmt.executeQuery(sql);  // statement객체에 의해서 제어 // sql문=> 변수로 생성 // 실행된 객체를 담는
			
			HoewonVO vo = null;  // 따로 선언
			while(rs.next()) {  // 자료가 있는가 없으면 통과
				vo = new HoewonVO();
				vo.setIdx(rs.getInt("idx"));  // vo.setIdx(0) 0도 가능(select로 가져오면 0부터)
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				
				vos.add(vo); //vo에 담은걸 vos로 넘겨줌
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
		return vos;  // vos를 던져줌
	}
	
	// 개별검색처리
	public HoewonVO getSearch(String name) {  // name 변수
		HoewonVO vo = new HoewonVO();
		
		// 여기선 담아주는 역할만 하면 됨 db연결되어있으니까 statement sql문 사용해서 rs에 담아서 가져옴 
		try {
			// DB 연결
			stmt = conn.createStatement();
			sql= "select * from hoewon where name = '"+name+"'";  // name 변수 따당  // select 있던지 없던지 값을 나에게 줌 그래서 result 사용
			rs = stmt.executeQuery(sql);  // 결과를 rs에 넣음  // 질의한 결과를 넘겨받을때 사용(select절의 경우에만 사용, 나머지는 결과를 넘겨받지 않음(u,d,i)), 
			
			//HoewonVO vo = null; //68에 있으니까 필요없음
			if(rs.next()) {  // 있으면 1개만 꺼냄
				//vo = new HoewonVO();  // 위에 생성함
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));				
				//vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
		return vo;
	}
	
	// 회원자료 수정처리
	//System.out.print("수정할 항목? 1.성명  2.나이  3.성별  4.주소 ==> ");
	public void setUpdate(int idx, int choice, String content) {
		try {
			stmt = conn.createStatement();  //여러개 사용
			sql= "";
			if(choice == 1) {
				sql = "update hoewon set name='"+content+"' where idx=" + idx; // statement 객체가 실행될때 sql
			}
			else if(choice == 2) {
				sql = "update hoewon set age="+Integer.parseInt(content)+" where idx=" + idx;  // 숫자니까 ''지움
			}
			else if(choice == 3) {
				sql = "update hoewon set gender='"+content+"' where idx=" + idx;
			}
			else if(choice == 4) {
				sql = "update hoewon set address='"+content+"' where idx=" + idx;
			}
			stmt.executeUpdate(sql);  // select 외의 경우(대표적 delete, update, insert 수행하고 끝(돌려받든 말든)) 쿼리,업데이트만 2개 기억
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());  // 위랑 같음
		}
	}
	
	// 회원자료 삭제처리
	public void setDelete(String name) {  // 동명이인 없다고 가정
		try {
			stmt = conn.createStatement();
			sql= "delete from hoewon where name='"+name+"'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}

	}

	//회원 등록 처리
	public void setInput(HoewonVO vo) {
		try {
			stmt = conn.createStatement();
			sql= "insert into hoewon values(default,'"+vo.getName()+"',"+vo.getAge()+",'"+vo.getGender()+"','"+vo.getAddress()+"')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}
}
