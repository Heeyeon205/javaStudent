	package Student;

public class Student {
		private int stuNo;
		private String name;
		private String stuId;
		private double avg;
		Student(){
			
		}
		public Student(String stuId, String name, int stuNo){
			this.stuId = stuId;
			this.name = name;
			this.stuNo = stuNo;
		}
		@Override
		public String toString() {
			return String.format("[%d번] id: %s, 이름: %s", stuNo, stuId, name);
		}
		public int getStuNo() {
			return stuNo;
		}
		public void setStuNo(int stuNo) {
			this.stuNo = stuNo;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getStuId() {
			return stuId;
		}
		public void setStuId(String stuId) {
			this.stuId = stuId;
		}
		public double getAvg() {
			return avg;
		}
		public void setAvg(double avg) {
			this.avg = avg;
		}
	}
