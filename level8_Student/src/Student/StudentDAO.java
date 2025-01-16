package Student;

import java.util.ArrayList;
import Subject.SubjectDAO;
import Utils.Utils;

public class StudentDAO {
	private ArrayList<Student> stuList;
	private int hakbun;

	public StudentDAO() {
		stuList = new ArrayList<>();
		hakbun = 1001;
	}
	public int getHakbun() {
		return hakbun;
	}
	public void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}
	public void setStuList(ArrayList<Student> stuList) {
		this.stuList = stuList;
	}

	public boolean isDupId(String id) {
		for(Student stu : stuList) {
			if(stu.getStuId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasStuData() {
		if (stuList.size() == 0) {
			System.out.println("학생 데이터가 없습니다.");
			return false;
		}
		return true;
	}

	public void createStu(String msg) {
		System.out.println(msg);
		String id = Utils.getInstance().getString("ID: ");
		if (isDupId(id)) {
			System.out.println("중복된 id입니다.");
			return;
		}
		String name = Utils.getInstance().getString("이름: ");
		stuList.add(new Student(id, name, hakbun));
		System.out.printf("%d/%s/%s 추가 완료!\n", hakbun, id, name);
		hakbun++;
	}
	
	public String getStuId(String msg) {
		String id = Utils.getInstance().getString(msg);
		for (int i = 0; i < stuList.size(); i++) {
			if (stuList.get(i).getStuId().equals(id)) {
				return id;
			}
		}
		System.out.println("없는 id입니다.");
		return null;
	}
	
	public int getStuIdx(String id) {
		for (int i = 0; i < stuList.size(); i++) {
			if (stuList.get(i).getStuId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public void deleteStu(String msg, SubjectDAO subDAO) {
		if (!hasStuData())
			return;
		System.out.println(msg);
		String id = getStuId("id: ");
		if (id == null)
			return;
		int idx = getStuIdx(id);
		int stuNo = stuList.get(idx).getStuNo();
		subDAO.deleteSubByStuNo(stuNo);
		stuList.remove(idx);
		System.out.printf("%s님 정보 삭제 완료\n", id);
	}

	public ArrayList<Student> getStuList() {
		return stuList;
	}

	public Student getStuListNo(int stuNo) {
		for (Student student : stuList) {
			if (student.getStuNo() == stuNo) {
				return student;
			}
		}
		return null;
	}

	public void printStuAll(String msg, SubjectDAO subDAO) {
		if (!hasStuData())
			return;
		System.out.println(msg);
		avgSort(subDAO);
		for (Student stu : stuList) {
			System.out.println(stu);
			subDAO.getSubScore(stu.getStuNo());
			if (stu.getAvg() > 0) {
				System.out.println("평균: " + stu.getAvg());
			} else {
				System.out.println("[No Subject Data]");
			}
			System.out.println("---------------------------");
		}
	}
	
	public void avgSort(SubjectDAO subDAO) {
		for (Student stu : stuList) {
			double avg = subDAO.getAvg(stu.getStuNo());
			stu.setAvg(avg);
		}
		for (int i = 0; i < stuList.size(); i++) {
			for (int j = 0; j < stuList.size() - 1; j++) {
				if (stuList.get(j).getAvg() < stuList.get(j + 1).getAvg()) {
					Student temp = stuList.get(j);
					stuList.set(j, stuList.get(j + 1));
					stuList.set(j + 1, temp);
				}
			}
		}
	}
}


