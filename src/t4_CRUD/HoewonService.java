package t4_CRUD;

import java.util.ArrayList;
import java.util.Scanner;

public class HoewonService {
	Scanner sc = new Scanner(System.in);  // 필드기에 닫을 필요x
	
	// 전체조회
	public void getList() {
		//HoewonDAO dao = new HoewonDAO();  // DB 열기
		HoewonDAO2 dao = new HoewonDAO2();  // 생성
		
		ArrayList<HoewonVO> vos = dao.getList();  // 일시킴 // 전체 자료 받아옴 ArrayList로 받아야 세부항복 HoewonVO // vo는 하나만 받아옴 // return type HoewonVO  // 받으려면 void x
		HoewonVO vo = new HoewonVO(); // vo 준비 // 생성을 1번만 하려고 for문 밖에 vo 생성
		
		System.out.println("============================================");
		System.out.println("번호\t성명\t나이\t성별\t주소");
		System.out.println("============================================");
		//while(rs.next()) {
		for(int i=0; i<vos.size(); i++) {  // vos 건수만큼 반복문을 돌림 // vos에 들어오는 자료를 가져옴  // 갯수를 알 수 있음 .size()  // 출력할때는 vo그릇이 하나만 있어도됨 재활용가능.(넣을때는 그릇이 여러개 있어야함.)
			vo = vos.get(i);  // 꺼내서 vo(그릇)에 담아서 출력
			System.out.print((i+1) + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getAge() + "\t");
			System.out.print(vo.getGender() + "\t");
			System.out.print(vo.getAddress() + "\n");
		}
		System.out.println("============================================");
		System.out.println("\t\t총 : "+vos.size()+" 건");
		
		dao.connClose();  // DB 닫기
	}

	// 개별 검색처리
	public void getSearch(String name) {  // void 여기서 처리하고 끝나서 O
		HoewonDAO2 dao = new HoewonDAO2();  // 여기에 만들면 sc 또 만듬
		
		HoewonVO vo = dao.getSearch(name);  // dao 너가 앞에서 받은 name으로 찾아줘  // 동명이인 처리 안한다고 했으니까 1건인 vos가 아니라 vo
		
		System.out.println(name + " (으)로 검색된 자료?");
		if(vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx());  // 자료가 있으면 출력
			System.out.println("성명 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
		}
		else {
			System.out.println("검색한 자료가 없습니다.");
		}
		
		dao.connClose();
	}
	
	// 회원정보 수정
	public void setUpdate(String name) {
		HoewonDAO2 dao = new HoewonDAO2();  // db객체 생성
		
		HoewonVO vo = dao.getSearch(name);  // 조회
		
		System.out.println(name + " (으)로 검색된 자료?");  // 조회된 자료를 보여주고 수정할 내용을 받거나, 그냥 수정(View가 아니라 현재는 console에서 작동하기에)
		if(vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx());
			System.out.println("성명 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
			System.out.println("------------------------------");
			System.out.print("수정할 항목? 1.성명  2.나이  3.성별  4.주소 ==> ");  // 5번하고 입력해도 에러x 값이 변하지 않음 원본값 업데이트  // 없는 것은 그냥 스킵 => 수정한 처리결과 0
			int choice = sc.nextInt();
			System.out.print("수정할 내용? ");
			String content = sc.next();  // 나이는 숫자로 바꿔서 처리
			
			if(choice == 1) vo.setName(content);
			else if(choice == 2) vo.setAge(Integer.parseInt(content));
			else if(choice == 3) vo.setGender(content);
			else if(choice == 4) vo.setAddress(content);  // 관계없는게 들어오면 그냥 나감 // 아니면 반복문 돌려야
			
			//dao.setUpdate(vo.getIdx(), choice, content);  // dao에서 수정(choice, content, idx(유일값) 보내야 함)  // 리턴값x void(위의 확인이 없다면 int 변수=로 리턴값 줌)
			int res = dao.setUpdate(vo);  // res 숫자로 넘어옴. // 정석대로 하면 처리하고 밑에처리 하지만 값이 뻔하면 밑에 먼저 처리 // 내용을 vo에 담았으니 vo를 넘기면 됨
			if(res != 0) System.out.println("===> 자료가 수정되었습니다.");  // return값을 준다면 비교 != 0 이 아니면 수정된건 ==0은 값이 없는 것
			else System.out.println("===> 자료가 수정되지 않았습니다.");
		}
		else {
			System.out.println("검색한 자료가 없습니다.");
		}
		
		dao.connClose();
	}
	
	// 회원자료 삭제처리
	public void setDelete(String name) {
		HoewonDAO2 dao = new HoewonDAO2();
		
		HoewonVO vo = dao.getSearch(name);
		
		System.out.println(name + " (으)로 검색된 자료?");
		if(vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx());
			System.out.println("성명 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
			System.out.println("------------------------------");
			System.out.print("삭제하시겠습니까?(y/n) ==> ");
			String choice = sc.next();
			if(choice.toUpperCase().equals("Y")) { // upper썼기에 y로 쓰면 무한 루프
				dao.setDelete(name);
				System.out.println("===> 삭제 성공!!");
			}
			else {
				System.out.println("===> 삭제 취소!!");
			}
		}
		else {
			System.out.println("검색한 자료가 없습니다.");
		}
		
		dao.connClose();
	}

	// 회원자료 등록처리
	public void setInput() {
		HoewonDAO2 dao = new HoewonDAO2();
		
		String name, gender, address;
		int age;
		
		System.out.println("==> 회원 정보 등록하기");
		System.out.print("성명 : ");
		name = sc.next();
		System.out.print("나이 : ");
		age = sc.nextInt();
		System.out.print("성별 : ");
		gender = sc.next();
		System.out.print("주소 : ");
		address = sc.next();
		
		HoewonVO vo = new HoewonVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setAddress(address);
		
		dao.setInput(vo);
		System.out.println("회원 등록 완료!!!");
		
		dao.connClose();
	}
}
