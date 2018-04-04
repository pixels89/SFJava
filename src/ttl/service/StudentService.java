package ttl.service;

import java.util.ArrayList;
import java.util.List;

import ttl.domain.Student;
import ttl.domain.Student.StudentBuilder;

public class StudentService {

	private List<Student> students = new ArrayList<>();
	
	private static int nextId = 0;
	
	public StudentService() {
		Student s = new StudentBuilder().id(1).name("Joe").status(Student.Status.FULL_TIME).build();
		insertStudent(s);

		s = new StudentBuilder().id(1).name("Seema").status(Student.Status.FULL_TIME).build();
		insertStudent(s);

		s = new StudentBuilder().id(1).name("Manoj").status(Student.Status.PART_TIME).build();
		insertStudent(s);
	}

	public Student insertStudent(Student student ) {
		int id = nextId++;

		Student newStudent = new StudentBuilder().id(id).name(student.getName()).status(student.getStatus()).build();
		
		students.add(newStudent);
	
		return newStudent;
	}


	public Student getStudent(int id) {
		for(Student student : students) {
			if(student.getId() == id) {
				return student;
			}
		}
		return null;
	}
	
	
	public List<Student> getAllStudents() {
		return students;
	}
	
}
