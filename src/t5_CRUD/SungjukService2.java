package t5_CRUD;

import java.util.ArrayList;
import java.util.Scanner;

public class SungjukService2 {
	Scanner sc = new Scanner(System.in);
	SungjukDAO dao = new SungjukDAO();
	SungjukVO vo = null;  // 사용할때 생성하면 되니까 선언만 해둠 //생성x
	
	int res;
	String ans="N";
	
	// 성적 입력
	public void setSungjukInput() {
		//vo = new SungjukVO();
		
		while(true) {
			System.out.println("\n** 성적 입력처리 **");
			String name = "";
			int kor=0, eng=0, mat=0;  //기본타입변수는 미리 넣어주는게 좋음(참조는 초기값 자동)
			while(true) {
				System.out.print("성명 : "); name = sc.next();
				// 동명이인 처리...
				vo = dao.getSungjukSearch(name);  // SungjukVO 안써도 값을 가져오면서 생성됨
				if(vo == null) break;  // 그 이름이 없으면 가입가능.  // vo에 null을 담아서 넣었던 Name이 사라짐(변수 따로 생성)
				else System.out.println("같은 이름이 존재합니다. 다시 입력하세요.");
			}
			System.out.print("국어 : "); kor = sc.nextInt();  // null이면 while문 빠져나와서 점수 입력
			System.out.print("영어 : "); eng = sc.nextInt();
			System.out.print("수학 : "); mat = sc.nextInt();
			
			vo = new SungjukVO(); // 위의 vo는 선언만 되어있음 생성을 해야함
			vo.setName(name);
			vo.setKor(kor);
			vo.setEng(eng);
			vo.setMat(mat);
						
			int res = dao.setSungjukInput(vo);  // 입력되면 1이 넘어옴, 입력x 0
			
			if(res != 0) System.out.println("성적자료가 등록되었습니다.");
			else System.out.println("성적자료 등록 실패~~");  // 여기까지 하고 22번 생성 만들러감
			
			System.out.print("계속하시겠습니까?(y/n) => ");
			ans = sc.next();
			if(!ans.toUpperCase().equals("Y")) break;
		}
		//sc.close(); //NoSuchElementException 에러남
	}
	//sc.close(); // 하면 안됨  // 필드로 뺀건 안해도됨 메소드로 한것만  // dao도 마찬가지 // 한번밖에 작업을 못함 // 두번째 값을 넣음ㄴ 에ㅓㄹ=
	
	// 회원 전체 검색
	public void getSungjukList() {
		ArrayList<SungjukVO> vos = dao.getSungjukList();
		
		System.out.println("\n\t*** 성 적 리 스 트 ***");
		System.out.println("============================================================");
		System.out.println("번호\t성명\t국어\t영어\t수학\t총점\t평균\t학점");
		System.out.println("------------------------------------------------------------");  //repeat써도됨(11이상)
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);  // 위에 선언함, vo에 그냥 담으면 됨. // 밑의 vo와 같음
			calculator(vo);  // vo안에 국어, 영어, 수학 담겨있으니 같이 넘김
			System.out.print(" " + (i+1) + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getKor() + "\t");
			System.out.print(vo.getEng() + "\t");
			System.out.print(vo.getMat() + "\t");
			System.out.print(vo.getTot() + "\t");
			System.out.print(String.format("%.1f", vo.getAvg()) + "\t");
			System.out.print(vo.getGrade() + "\n");
		}
		System.out.println("------------------------------------------------------------");  //repeat써도됨(11이상)
		System.out.println("\t총 인원수 : " + vos.size() + "명");
		System.out.println("============================================================");
	}
	
	// 계산(총점/평균/학점)
	private void calculator(SungjukVO vo) {
		vo.setTot(vo.getKor()+vo.getEng()+vo.getMat());
		vo.setAvg(vo.getTot() / 3.0);  // 정수,실수 연산은 실수
		if(vo.getAvg() >= 90) vo.setGrade('A');
		else if(vo.getAvg() >= 80) vo.setGrade('B');
		else if(vo.getAvg() >= 70) vo.setGrade('C');
		else if(vo.getAvg() >= 60) vo.setGrade('D');
		else vo.setGrade('F');
	}

	// 개별자료 검색
	public void getSungjukSearch() {
		while(true) {
			System.out.print("\n조회할 성명을 입력하세요. ");  // 반복되니까 메소드로 따로 빼서 불러옴  // vo가 다 같으면 상관x 하나라도 새로 생성된게 있으면 안됨. 잘 생각해서 메소드로 빼야함
			String name = sc.next();
			
			vo = dao.getSungjukSearch(name); //리턴타입에 넘겨주는 값만, 생성해도 되고 생성안해도 가능
			
			if(vo != null) {
				calculator(vo);  // 호출할때는 타입 필요x
				System.out.println("\n고유번호 : " + vo.getIdx());
				System.out.println("성명 : " + vo.getName());
				System.out.println("국어 : " + vo.getKor());
				System.out.println("영어 : " + vo.getEng());
				System.out.println("수학 : " + vo.getMat());
				System.out.println("총점 : " + vo.getTot());
				System.out.println("평균 : " + vo.getAvg());
				System.out.println("학점 : " + vo.getGrade());
			}
			else System.out.println("검색하신 "+name+" 님은 없습니다.");
			
			System.out.print("계속하시겠습니까?(y/n) => ");
			ans = sc.next();
			if(!ans.toUpperCase().equals("Y")) break;
		}
	}

	// 성적 수정하기
	public void setSungjukUpdate() {
		
	}
}
