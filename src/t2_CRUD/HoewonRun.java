package t2_CRUD;

import t2_CRUD.HoewonDAO3;

public class HoewonRun {  // View
	public static void main(String[] args) {
		//HoewonDAO dao = new HoewonDAO();	
		//HoewonDAO2 dao = new HoewonDAO2();	
		HoewonDAO3 dao = new HoewonDAO3();	
		//HoewonService service = new HoewonService();  // 일반적인 작업, 코테할때는 따로 안만듬.
		
		// hoewon자료 전체조회
		dao.getList();
		
		dao.connClose();
	}
}
