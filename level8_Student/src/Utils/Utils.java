package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import Student.Student;
import Student.StudentDAO;
import Subject.Subject;
import Subject.SubjectDAO;

public class Utils {
	private static Scanner sc = new Scanner(System.in);
	private static Random rd = new Random();
	private static final String CUR_PATH = System.getProperty("user.dir") + "/src/" + Utils.class.getPackageName()+ "/";
	private static Utils instance;
	
	private Utils() {}
	public static Utils getInstance() {
		if(instance == null) instance = new Utils();
		return instance;
	}
	
	public int getNum(String msg, int min, int max) {
		while (true) {
			System.out.print(msg);
			try {
				int num = sc.nextInt();
				sc.nextLine();
				if (num < min || num >= max) {
					System.out.println("입력 범위 오류입니다.");
					continue;
				}
				return num;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력 가능합니다.");
				sc.nextLine();
				continue;
			}
		}
	}

	public String getString(String msg) {
		System.out.print(msg);
		String str = sc.nextLine();
		return str;
	}

	public int getRdScore() {
		int rdScore = rd.nextInt(51) + 50;
		return rdScore;
	}

	public void saveStuData(String msg, StudentDAO stuDAO) {
		if (!stuDAO.hasStuData()) return;
		String stuFilePath = CUR_PATH + msg;
		try (FileWriter fw = new FileWriter(stuFilePath);) {
			for (Student stu : stuDAO.getStuList()) {
				fw.write(stu.getStuId() + "/" + stu.getName() + "/" + stu.getStuNo() + "\n");
			}
			System.out.println("Student 파일 저장 완료!\n" + stuFilePath);
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("오류 발생");
		}
	}

	public void saveSubData(String msg, SubjectDAO subDAO) {
		if (!subDAO.hasSubData()) return;
		String subFilePath = CUR_PATH + msg;
		try (FileWriter fw = new FileWriter(subFilePath);) {
			for (Subject sub : subDAO.getSubList()) {
				fw.write(sub.getStuNo() + "/" + sub.getSubName() + "/" + sub.getScore() + "\n");
			}
			System.out.println("Subject 파일 저장 완료!\n" + subFilePath);
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("오류 발생");
		}
	}

	public void loadStuData(String msg, StudentDAO stuDAO) {
		String stuFilePath = CUR_PATH + msg;
		try (FileReader fr = new FileReader(stuFilePath);
				BufferedReader br = new BufferedReader(fr);){
			stuDAO.getStuList().clear();
			int maxHakbun = 1000;
			String line = "";
			while((line = br.readLine()) != null) {
				String[] temp = line.split("/");
				int curHakbun = Integer.parseInt(temp[2]);
				stuDAO.getStuList().add(new Student(temp[0], temp[1], curHakbun));
				maxHakbun = Math.max(maxHakbun, curHakbun);
			}
			stuDAO.setHakbun(maxHakbun+1);
			System.out.println("Student 파일 불러오기 완료.\n" + stuFilePath);
		} catch (FileNotFoundException e) {
			System.out.println("Student에 저장된 파일이 없습니다.");
		} catch (IOException e1) {
			System.out.println("오류 발생");
		}
	}

	public void loadSubData(String msg, SubjectDAO subDAO) {
		String subFilePath = CUR_PATH + msg;
		try (FileReader fr = new FileReader(subFilePath);
				BufferedReader br = new BufferedReader(fr);){
			subDAO.getSubList().clear();
			String line = "";
			while((line = br.readLine()) != null) {
				String[] temp = line.split("/");
				subDAO.getSubList().add(new Subject(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2])));
			}
			System.out.println("Subject 파일 불러오기 완료.\n" + subFilePath);
		} catch (FileNotFoundException e) {
			System.out.println("Subject에 저장된 파일이 없습니다.");
		} catch (IOException e1) {
			System.out.println("오류 발생");
		}
	}
}
