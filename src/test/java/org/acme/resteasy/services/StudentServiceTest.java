package org.acme.resteasy.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.acme.resteasy.Exceptions.NoDataFoundException;
import org.acme.resteasy.filters.StudentFilterBean;
import org.acme.resteasy.models.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

class StudentServiceTest {
	
	@Spy
	private StudentService studentService;
	
	@BeforeEach
	void initEach() {
		studentService = new StudentService();
	}
	
	@Test
	public void getAllStudentsNoNullExceptionTest()
	{
		assertNotNull(studentService.getAllStudents());
	}
	
	@Test
	public void getAllStudentsByEndYearNoDataFoundExceptionTest()
	{
		int year=2021;
		assertThrows(NoDataFoundException.class, () -> studentService.getAllStudentsFromYear(year),
				"Getting students who are enrolled in a year above 2021 should throw a NoDataFoundException");
	}
	
	@Test
	public void getAllStudentsByEndYearTest()
	{
		int endYear=2015;
		assertAll(()->studentService.getAllStudentsFromYear(endYear));
	}
	
	@Test
	public void getAllStudentsByStartYearNoDataFoundExceptionTest()
	{
		int startYear=1800;
		int endYear=2019;
		assertThrows(NoDataFoundException.class, () -> studentService.getAllStudentsFromYearRange(startYear,endYear),
				"Getting students who are enrolled in a year before 1900 should throw a NoDataFoundException");
	}
	
	@Test
	public void getAllStudentsByStartYearTest()
	{
		int startYear=1950;
		int endYear=2019;
		assertAll(()->studentService.getAllStudentsFromYearRange(startYear,endYear));
	}
	
	@Test
	public void removeNonExistantStudentThrowsNoDataFoundTest()
	{
		Student student = new Student();
        student.setName("Maya Sami");
        student.setEnrollmentYear("2015");
        student.setMajor("Computer Engineering");
        assertThrows(NoDataFoundException.class,()->studentService.removeStudent(student.getId()),
        		"Removing a non-existent student should throw a NoDataFoundException.");
	}
	
	@Test
	public void updateNonExistantStudentTestThrowsNoDataFoundTest()
	{
		Student student = new Student();
        assertThrows(NoDataFoundException.class,()->studentService.updateStudent(student),
        		"Removing a non-existent student should throw a NoDataFoundException.");
	}
	
	@Test
	public void getAllStudentsTest() {
		studentService=spy(new StudentService());
		Student student = new Student(15,"Karen Omar","2015","Computer Engineering");
		List<Student> students = new ArrayList<>();
		students.add(student);
		doReturn(students).when(studentService).getAllStudents();
		assertEquals(students,studentService.getAllStudents());
		}
	
	@Test
	public void getAllStudentsFromYearTest() {
		studentService=spy(new StudentService());
		Student student1 = new Student(15,"Karen Omar","2015","Computer Engineering");
		Student student2 = new Student(16,"Othman Omar","2017","Computer Engineering");
		List<Student> students = new ArrayList<>();
		students.add(student1);
		StudentFilterBean filter = new StudentFilterBean();
		filter.setYear(2015);
		doReturn(students).when(studentService).getAllStudentsFromYear(filter.getYear());
		assertEquals(students,studentService.getAllStudentsFromYear(filter.getYear()));
		}
	
	@Test
	public void getAllStudentsFromYearRangeTest() {
		studentService=spy(new StudentService());
		Student student1 = new Student(15,"Karen Omar","2015","Computer Engineering");
		Student student2 = new Student(16,"Othman Omar","2017","Computer Engineering");
		List<Student> students = new ArrayList<>();
		students.add(student1);
		students.add(student2);
		StudentFilterBean filter = new StudentFilterBean();
		filter.setStartYear(2015);
		filter.setEndYear(2017);
		doReturn(students).when(studentService).getAllStudentsFromYearRange(filter.getStartYear(), filter.getEndYear());
		assertEquals(students,studentService.getAllStudentsFromYearRange(filter.getStartYear(), filter.getEndYear()));
		}
	
	

}
