package com.neosoft.demo.student;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.common.collect.Sets;
import com.neosoft.demo.student.controller.StudentController;
import com.neosoft.demo.student.model.Project;
import com.neosoft.demo.student.model.Student;
import com.neosoft.demo.student.service.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	StudentService studentService;
	HashSet<Project> pro = new HashSet<Project>();
	
	Student student = new Student(1,"Ajay", "Thakur", "test@gmail.com", "0987654321", Sets.newHashSet(
			new Project(1, "Java Project", 10)));
	List<Student> mockUser = Arrays.asList(student);
	
	String exampleStudentJson = "{\"firstName\":\"Rajat\",\"lastName\":\"Mishra\",\"mobileNo\":\"00000000\""
			+ ",\"email\":\"abctest@gmail.com\"}";
	

	@Test
	public void retrieveAllStudentTest() throws Exception {
		Mockito.when(studentService.getAllStudent()).thenReturn(mockUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(exampleStudentJson, result.getResponse()
				.getContentAsString(), false);

	}
	
	@Test
	public void retrieveStudentByIdTest() throws Exception {
		Map<String, Object> mock = new HashMap<String, Object>();
		mock.put("data", student);
		Mockito.when(studentService.getStudentById(Mockito.anyInt())).thenReturn(mock);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student//1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(exampleStudentJson, result.getResponse()
				.getContentAsString(), false);

	}
	
	@Test
	public void addStudentTest() throws Exception {
		Map<String, Object> mock = new HashMap<String, Object>();
		mock.put("data", student);
		Mockito.when(studentService.createUpdate(Mockito.any(Student.class))).thenReturn(mock);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/student")
				.accept(MediaType.APPLICATION_JSON).content(exampleStudentJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/add/",
				response.getHeader(HttpHeaders.LOCATION));

	}
	
}
