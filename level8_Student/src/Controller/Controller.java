package Controller;

import Student.StudentDAO;
import Subject.SubjectDAO;
import Utils.Utils;

public class Controller {
	private StudentDAO stuDAO;
	private SubjectDAO subDAO;
	
	private void init() {
		stuDAO = new StudentDAO();
		subDAO = new SubjectDAO();
	}
	
	private void printMainMenu() {
		Utils utils = Utils.getInstance();
		while(true) {
		System.out.println("[1] 학생 추가"); // 학생(1001) 자동 증가: id 중복 불가
		System.out.println("[2] 학생 삭제"); // 학생 id입력 후 삭제, 과목도 같이 삭제
		System.out.println("[3] 학생 과목 추가"); // 학번 입력 후 점수 50~100 랜덤, 과목 이름 중복 불가
		System.out.println("[4] 학생 과목 삭제"); // 학번 입력 후 과목 이름 받아서 해당 과목에서 학생 1명 삭제
		System.out.println("[5] 전체 학생 목록"); // 점수 내림차순 정렬
		System.out.println("[6] 한 과목 학생 목록"); // 과목이름 입력 받아서 해당 과목 학생 이름과 과목 점수 출력 이름 오름차순 출력
		System.out.println("[7] 파일 저장");
		System.out.println("[8] 파일 로드");
		System.out.println("[0] 프로그램 종료");
		int sel = Utils.getInstance().getNum("메뉴 선택: ", 0, 9);
		if(sel == 1) {
			stuDAO.createStu("[1] 학생 추가");
		}else if(sel == 2) {
			stuDAO.deleteStu("[2] 학생 삭제", subDAO);
		}else if(sel == 3) {
			subDAO.createSub("[3] 학생 과목 추가", stuDAO);
		}else if(sel == 4) {
			subDAO.deleteSub("[4] 학생 과목 삭제", stuDAO);
		}else if(sel == 5) {
			stuDAO.printStuAll("[5] 전체 학생 목록", subDAO);
		}else if(sel == 6) {
			subDAO.printSubToStuName("[6] 한 과목 학생 목록", stuDAO);
		}else if(sel == 7) {
			utils.saveStuData("Student.txt", stuDAO);
			utils.saveSubData("Subject.txt", subDAO);
		}else if(sel == 8) {
			utils.loadStuData("Student.txt", stuDAO);
			utils.loadSubData("Subject.txt", subDAO);
		}else if(sel == 0) {
			System.out.println("[프로그램 종료]");
			break;
		}
		}
	}
	
	public void run() {
		init();
		printMainMenu();
	}
}
