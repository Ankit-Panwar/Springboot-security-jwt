package com.neosoft.demo.student.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.demo.student.model.Student;
import com.neosoft.demo.student.service.StudentService;

@RestController
@RequestMapping("/api/")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	
	@GetMapping(value = "student", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllStudents(){
		return ResponseEntity.ok(studentService.getAllStudent());
	}
	
	@GetMapping(value = "student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getByStudentId(@PathVariable("studentId") Integer studentId){
		return ResponseEntity.ok(studentService.getAllStudent()
				.stream()
				.filter(student -> studentId.equals(student.getId()))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist")));
	}
	
	@PostMapping(value = "student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addStudent(HttpServletRequest request, @RequestBody Student student){
		return ResponseEntity.ok(this.studentService.createUpdate(student));
	}

}
