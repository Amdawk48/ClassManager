package orange.amd.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyClass {
	private String name;
	private List<Student> students;
	private double average;
	
	public MyClass(String name, List<Student> students) {
		super();
		this.name = name;
		this.students = students;
		this.average = this.getClassAverage();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public List<Student> getCalculatedStudents() {
		return (List<Student>) students.stream()
				.filter(st -> {
					return st.getGrade()>0.0 && !Double.isNaN(st.getGrade()); 
				})
				.collect(Collectors.toList());
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public double getClassAverage(){
		double sum = 0.0;
		List<Student> validStudents = getCalculatedStudents();
		for(Student student : validStudents) {
			sum += student.getGrade();
		}
		return sum/validStudents.size();
	}
	
	public String getMissingStudents() {
		ArrayList<String> names = new ArrayList<String>();
		students.stream()
			.forEach(st -> {
				if(st.getGrade()<=0.0 || Double.isNaN(st.getGrade())) {
					names.add(st.getName());
				}
			});
				
		return String.join(", ", names);
	}
	
	public String displayClassInfo() {
		String classInfo = "";
		classInfo += "--------------------------------"+this.getName()+"--------------------------------" + "\n";
		classInfo += "Total Number of Students: " + this.getStudents().size() + "\n";
		classInfo += "Number of Students for Calculating Class Average: " + this.getCalculatedStudents().size() + "\n";
		classInfo += "Class Average: " + String.format("%.2f", this.average) + "\n";
		classInfo += "Students Missing Grades: \n		" + this.getMissingStudents() + "\n";

		return classInfo;
	}

}
