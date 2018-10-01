package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.SinglyLinkedList;
import main.Student;
import main.StudentListManagement;

class SinglyLinkedListTest {

	@Test
	void testRemoveFirst() {
		// create Linked list
		SinglyLinkedList<Student> list = new SinglyLinkedList<>();
		// add stuff to it
		Student s = new Student("123", "456", "EBG", "ebg@", "StudentID", "Sleep Studies", 4.0);
		list.addFirst(s);
		// print to string
		System.out.println(list.toString());
		
		// remove 
		Student r = list.remove(s);
		assertEquals(r, s);
		
	}
	/**
	 * Test finding the first element
	 */
	@Test
	void testFindFirst() {
		SinglyLinkedList<Student> list = new SinglyLinkedList<>();
		Student s = new Student("123", "456", "EBG", "ebg@", "studentID", "Sleep Studies", 4.0);
		list.addFirst(s);
		
		Student found = list.find(new Student("ssn", "name", "address",
				"email", "studentID", "major", 0));
		assertEquals(s, found);
		System.out.println("Found! " + found.toString());
		
		
	}
	/**
	 * Test finding an element in the list
	 */
	@Test
	void testFind() {
		SinglyLinkedList<Student> list = new SinglyLinkedList<>();
		Student s = new Student("123", "456", "EBG", "ebg@", "studentID", "Sleep Studies", 4.0);
		Student s2 = new Student("123", "456", "EBG", "ebg@", "studentID2", "Sleep Studies", 4.0);
		list.addFirst(s);
		list.addLast(s2);
		
		Student found = list.find(new Student("ssn", "name", "address",
				"email", "studentID2", "major", 0));
		assertEquals(s2, found);
		System.out.println("Found! " + found.toString());
	}
	
	@Test
	void testFindNull() {
		SinglyLinkedList<Student> list = new SinglyLinkedList<>();
		Student s = new Student("123", "456", "EBG", "ebg@", "studentID", "Sleep Studies", 4.0);
		Student s2 = new Student("123", "456", "EBG", "ebg@", "studentID2", "Sleep Studies", 4.0);
		list.addFirst(s);
		//list.addLast(s2); // don't add to the list
		
		Student found = list.find(s2);
		assertNull(found);
	}
	
	/**
	 * Test the static method that creates a dummy student with given ID
	 */
	@Test
	void testStudentWithID() {
		SinglyLinkedList<Student> list = new SinglyLinkedList<>();
		Student s = new Student("123", "456", "EBG", "ebg@", "studentID", "Sleep Studies", 4.0);
		list.addFirst(s);
		
		Student test = StudentListManagement.studentWithID("studentID");
		assertEquals(s, test);
	}

}
