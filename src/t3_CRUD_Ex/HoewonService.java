package t3_CRUD_Ex;

import java.util.ArrayList;

public class HoewonService {

	// 회원등록
	public void setInput() {
		
	}
	
	// 전체조회
	public void getList() {
		HoewonDAO dao = new HoewonDAO();
		
		ArrayList<HoewonVO> vos = dao.getList();
		HoewonVO vo = new HoewonVO();
		
		System.out.println("=====================================");
		System.out.println("번호\t성명\t나이\t성별\t주소");
		System.out.println("=====================================");
		
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);
			System.out.print((i+1) + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getAge() + "\t");
			System.out.print(vo.getGender() + "\t");
			System.out.print(vo.getAddress() + "\n");			
		}
		System.out.println("=====================================");
		System.out.println("\t\t총 : "+vos.size()+" 건");
		
		dao.connClose();
	}
	
	// 개별 검색처리
	public void getSearch(String name) {
		HoewonDAO dao = new HoewonDAO();
		HoewonVO vo = dao.getSearch(name);
		
		System.out.print(name + " (으)로 검색된 자료는? ");
		if(vo.getName() != null) {
			System.out.println("성명 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
		}
		else System.out.println("해당 "+name+"의 자료가 존재하지 않습니다.");
	}

	public void setUpdate(String name) {
		
	}

	public void setDelete(String name) {
		
	}

}
