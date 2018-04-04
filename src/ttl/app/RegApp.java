package ttl.app;

import java.util.List;

import ttl.domain.Student;
import ttl.domain.Student.Status;
import ttl.service.StudentService;

public class RegApp {

	public static void main(String[] args) {
		
		StudentService service = new StudentService(); 
		
		List<Student> students = service.getAllStudents();
		
		System.out.println(students);
		
		Student s = service.getStudent(1);
		System.out.println(s);
		
		s = new Student("Rachel", Status.SLEEPING);
		
		Student newStudent = service.insertStudent(s);
		
		System.out.println("new Student: " + newStudent);
	}
}
