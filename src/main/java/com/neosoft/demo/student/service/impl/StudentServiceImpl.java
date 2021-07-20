package com.neosoft.demo.student.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.demo.student.model.Student;
import com.neosoft.demo.student.repository.StudentRepository;
import com.neosoft.demo.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	public Map<String, Object> createUpdate(Student student) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", student.getId() == 0 ? "Student Added Sucessfully":"Student Updated Sucessfully");
		student = this.studentRepository.save(student);
		response.put("data", student);
		return response;
	}

	@Override
	public Map<String, Object> getStudentById(int studentId) {
		Map<String, Object> response = new HashMap<String, Object>();
		Student student = this.studentRepository.getById(studentId);
		if(student == null)
			response.put("message", "Student with Id : " + studentId + " doesn't exist");
		else
			response.put("data", student);
		return response;
	}

}
