package t3_CRUD;

import java.util.Scanner;

import t2_CRUD.HoewonDAO3;

public class HoewonRun {  // View
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HoewonService service = new HoewonService();
		
		boolean run = true;
		String name = "";
		
		while(run) {
			System.out.print("1.회원등록  2.전체조회  3.개별조회  4.수정  5.삭제  0.종료 ==> ");
			int choice = sc.nextInt();
			
//			db 살펴봄 테이블에 뭐가 있나 VO나 DTO 살펴봄 파악
//			사용하는 DAO 찾으려면 RUN부터 출발 ctrl + spqce
			
			switch(choice) {
				case 1:
					service.setInput();  // hoewon자료 등록
					break;
				case 2:
					service.getList();  // hoewon자료 등록
					break;
				case 3:
					System.out.print("검색할 성명을 입력하세요? ");  //service에 하면 sc 또 생성해야 해서 여기에 만듬 //동명이인 있으면 첫번째 자료만 가져올수 있음
					name = sc.next(); // 변수를 DB에서 찾아옴
					service.getSearch(name);  // hoewon자료 개별조회 // name으로 찾아와
					break;
				case 4:
					System.out.print("수정할 성명을 입력하세요? ");
					name = sc.next();
					service.setUpdate(name);  // hoewon자료 수정  // get으로 해도됨 set이 일관적
					break;
				case 5:
					System.out.print("삭제할 성명을 입력하세요? ");
					name = sc.next();
					service.setDelete(name);  // hoewon자료 삭제
					break;
				default:
					run = false;
			}
		}
		System.out.println("수고하셨습니다.");
		
		sc.close();
	}
}
