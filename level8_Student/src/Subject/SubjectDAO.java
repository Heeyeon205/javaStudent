package Subject;

import java.util.ArrayList;
import Student.StudentDAO;
import Utils.Utils;

public class SubjectDAO {
	private ArrayList<Subject> subList;

	public SubjectDAO() {
		subList = new ArrayList<>();
	}

	public ArrayList<Subject> getSubList() {
		return subList;
	}

	public void setSubList(ArrayList<Subject> subList) {
		this.subList = subList;
	}
	
	public boolean hasSubData() {
		if (subList.size() == 0) {
			System.out.println("과목 데이터가 없습니다.");
			return false;
		}
		return true;
	}
	
	public boolean isDupSubName(int hakbun, String name) {
		for (int i = 0; i < subList.size(); i++) {
			if (subList.get(i).getStuNo() == hakbun && subList.get(i).getSubName().equals(name)) {
				System.out.println("이미 해당 과목이 있습니다.");
				return true;
			}
		}
		return false;
	}

	public void createSub(String msg, StudentDAO stuDAO) {
		if (!stuDAO.hasStuData()) return;
		System.out.println(msg);
		int hakbun = Utils.getInstance().getNum("학번: ", 1001, 1099);
		if (stuDAO.getStuListNo(hakbun) == null) {
			System.out.println("학번을 확인해주세요.");
			return;
		}
		String name = Utils.getInstance().getString("과목: ");
		if (isDupSubName(hakbun, name)) return;
		int rdScore = Utils.getInstance().getRdScore();
		subList.add(new Subject(hakbun, name, rdScore));
		System.out.printf("%d/%s/%d 추가 완료!\n", hakbun, name, rdScore);
	}
	
	public boolean hasSubName(int hakbun, String name) {
		for (int i = 0; i < subList.size(); i++) {
			if (subList.get(i).getStuNo() == hakbun && subList.get(i).getSubName().equals(name)) {
				return true;
			}
		}
		System.out.println("해당 과목을 찾을 수 없습니다.");
		return false;
	}
	
	public int getSubIdx(int stuNo) {
		for (int i = 0; i < subList.size(); i++) {
			if (subList.get(i).getStuNo() == stuNo) {
				return i;
			}
		}
		return -1;
	}

	public void deleteSub(String msg, StudentDAO stuDAO) {
		if (!hasSubData()) return;
		System.out.println(msg);
		int hakbun = Utils.getInstance().getNum("학번: ", 1001, 1099);
		if (stuDAO.getStuListNo(hakbun) == null) {
			System.out.println("학번을 확인해주세요.");
			return;
		}
		String name = Utils.getInstance().getString("과목: ");
		if (!hasSubName(hakbun, name))
			return;
		int idx = getSubIdx(hakbun);
		subList.remove(idx);
		System.out.printf("%d번 학생 %s 삭제 완료\n", hakbun, name);
	}

	public int getSubSize() {
		return subList.size();
	}

	public void deleteSubByStuNo(int stuNo) {
		for (int i = 0; i < subList.size(); i++) {
			if (subList.get(i).getStuNo() == stuNo) {
				subList.remove(i);
			}
		}
		System.out.println("과목 전체 삭제 완료");
	}

	public double getAvg(int stuNo) {
		double total = 0.0;
		int cnt = 0;
		for (Subject sub : subList) {
			if (sub.getStuNo() == stuNo) {
				total += sub.getScore();
				cnt++;
			}
		}
		return total == 0.0 ?  -1 : total / cnt;
	}

	public void getSubScore(int stuNo) {
		for (Subject sub : subList) {
			if (sub.getStuNo() == stuNo) {
				System.out.print(sub + " ");
			}
		}
	}
	
	public boolean hasSubDataToStu(String name) {
		for (Subject sub : subList) {
			if (sub.getSubName().equals(name)) {
				return true;
			}
		}
		System.out.println("해당 과목 데이터가 없습니다.");
		return false;
	}

	public void printSubToStuName(String msg, StudentDAO stuDAO) {
	    if (!stuDAO.hasStuData()) return;
	    String subName = Utils.getInstance().getString("과목 이름: ");
	    if (!hasSubDataToStu(subName)) return;
	    
	    ArrayList<Subject> nameList = new ArrayList<>();
	    for (Subject sub : subList) {
	        if (sub.getSubName().equals(subName)) {
	        	nameList.add(sub);
	        }
	    }
	    
	    for (int i = 0; i < nameList.size() - 1; i++) {
	        for (int j = 0; j < nameList.size() - 1 - i; j++) {
	            String name1 = stuDAO.getStuListNo(nameList.get(j).getStuNo()).getName();
	            String name2 = stuDAO.getStuListNo(nameList.get(j + 1).getStuNo()).getName();
	            if (name1.compareTo(name2) > 0) {
	                Subject temp = nameList.get(j);
	                nameList.set(j, nameList.get(j + 1));
	                nameList.set(j + 1, temp);
	            }
	        }
	    }
	    
	    System.out.println(msg);
	    for (Subject sub : nameList) {
	        String studentName = stuDAO.getStuListNo(sub.getStuNo()).getName();
	        System.out.printf("%s 점수: %d점\n", studentName, sub.getScore());
	    }
	    System.out.println("------------------------");
	}

}
