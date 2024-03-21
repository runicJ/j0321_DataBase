package t3_CRUD_Ex;

import java.util.Scanner;

public class HoewonRun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HoewonService service = new HoewonService();
		
		boolean run = true;
		String name = "";
		
		while(run) {
			System.out.print("1.회원등록  2.전체조회  3.개별조회  4.회원수정  5.회원삭제  0.종료 ==> ");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					service.setInput();
					break;
				case 2:
					service.getList();
					break;
				case 3:
					System.out.print("조회하실 회원의 이름을 입력하세요? ");
					name = sc.next();
					service.getSearch(name);
					break;
				case 4:
					System.out.print("수정하실 회원의 이름을 입력하세요? ");
					name = sc.next();
					service.setUpdate(name);
					break;
				case 5:
					System.out.print("삭제하실 회원의 이름을 입력하세요? ");
					name = sc.next();
					service.setDelete(name);
					break;
				default:
					run = false;
			}
		}
		System.out.println("수고하셨습니다.");
		
		sc.close();
	}
}
