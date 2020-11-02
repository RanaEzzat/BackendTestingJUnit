package org.acme.resteasy.resources;

import static org.junit.jupiter.api.Assertions.*;

import org.acme.resteasy.filters.StudentFilterBean;
import org.acme.resteasy.models.Student;
import org.acme.resteasy.services.StudentService;
import org.acme.resteasy.stubsmocksdummies.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;


class StudentResourceTest {
	
@Spy
private StudentResource studentResource;
@Spy
StudentService studentService;

	@BeforeEach
	void initEach() {
		studentResource=spy(new StudentResource());
		studentService=mock(studentResource.studentService.getClass());
	}
	
	

	@Test
	public void getAllStudentsTest() {
		studentResource=spy(new StudentResource());
		(studentResource).getStudents();
		Mockito.verify(studentResource).getStudents();
		assertEquals(14,(studentResource).getStudents().size());
		
	}
	
	/*@Test
	public void addStudentsTest() {
		Student student = new Student(15,"Karen Omar","2015","Computer Engineering");
		Response r=(studentResource).addStudent(student,null);
		System.out.println(r.getStatus());
		assertEquals(14,(studentResource).getStudents().size());
		
	}*/
	
	@Test
	public void getAllStudentsFromYearTest() {
		(studentResource).getStudents();
		StudentFilterBean filter = new StudentFilterBean();
		filter.setYear(2015);
		List<Student>students=studentService.getAllStudentsFromYear(filter.getYear());
		doReturn(students).when(studentResource).getStudents(filter);
		studentResource.getStudents(filter);
		Mockito.verify(studentResource).getStudents(filter);
		Mockito.verify(studentService).getAllStudentsFromYear(filter.getYear());
		assertEquals(students,studentResource.getStudents(filter));	
	}
	
	@Test
	public void getAllStudentsFromStartToEndYearTest() {
		(studentResource).getStudents();
		StudentFilterBean filter = new StudentFilterBean();
		filter.setStartYear(2015);
		filter.setEndYear(2020);

		List<Student>students=studentService.getAllStudentsFromYearRange(filter.getStartYear(),filter.getEndYear());
		doReturn(students).when(studentResource).getStudents(filter);
		studentResource.getStudents(filter);
		Mockito.verify(studentResource).getStudents(filter);
		Mockito.verify(studentService).getAllStudentsFromYearRange(filter.getStartYear(),filter.getEndYear());
		assertEquals(students,studentResource.getStudents(filter));	
	}
	
	@Test
	public void getAllStudentsPaginationTest() {
		(studentResource).getStudents();
		StudentFilterBean filter = new StudentFilterBean();
		filter.setStart(0);
		filter.setSize(2);

		List<Student>students=studentService.getStudentsPaginated(filter.getStart(), filter.getSize());
		doReturn(students).when(studentResource).getStudents(filter);
		studentResource.getStudents(filter);
		Mockito.verify(studentResource).getStudents(filter);
		Mockito.verify(studentService).getStudentsPaginated(filter.getStart(), filter.getSize());
		assertEquals(students,studentResource.getStudents(filter));	
	}



	
}
