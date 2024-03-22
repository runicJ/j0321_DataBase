package t5_CRUD;

import java.util.Scanner;

public class SungjukRun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		SungjukService service = new SungjukService();  // DAO 하고 여기로 와서 service 생성 후 돌아옴
		
		int choice = 0;
		boolean run = true;
		String name = "";
		
		while(run) {
			System.out.println("\n\t** 성  적  표 **");
			System.out.print("메뉴선택? 1:입력  2:전체조회  3:개별조회  4:수정  5:삭제  0:종료 ==> ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					service.setSungjukInput();  // 밑에까지 다쓰고 create해서 service로 감
					break;
				case 2:
					service.getSungjukList();
					break;
				case 3:
					service.getSungjukSearch();
					break;
				case 4:
					service.setSungjukUpdate();
					break;
				case 5:
					service.setSungjukDelete();
					break;
				default:
					run = false;  // 프로그램 짤때 항상 탈출구를 먼저 생성
			}
		}
		System.out.println("============================================================");
		System.out.println("작업끝!");
		
		sc.close();
	}
}
