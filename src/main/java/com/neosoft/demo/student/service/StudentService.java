package com.neosoft.demo.student.service;

import java.util.List;
import java.util.Map;

import com.neosoft.demo.student.model.Student;

public interface StudentService {

	public abstract List<Student> getAllStudent();
	
	public abstract Map<String, Object> getStudentById(int studentId);

	public abstract Map<String, Object> createUpdate(Student student);

}
