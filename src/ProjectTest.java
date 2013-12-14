import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 Test cases for my Project class.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class ProjectTest {
	/**
	 * global variables for commonly used Projects for testing.
	 */
	private Project genericSoftware, projempty;
	/**
	 * global variables for commonly used Staff for testing.
	 */
	private Staff bob, alice, charlie;

	/**
	 * Set up default projects for testing.
	 *
	 * @throws Exception
	 *             if fails
	 */
	@Before
	public void setUp() throws Exception {
		//set up the 3 staff members
		bob = new Staff(0001, "Bob", "Manager");
		alice = new Staff(0002, "Alice", "Developer");
		charlie = new Staff(0003, "Charlie", "Intern");
		//set the staff array
		ArrayList<Staff> staff = new ArrayList<Staff>();
		//add the staff into the array
		staff.add(bob);
		staff.add(alice);
		staff.add(charlie);
		//st up the 6 tasks
		Task reqana = new Task("Requirements Analysis", 1092011, 1102011,
				"Analysing the requirements", bob);
		Task sysdes = new Task("System Design", 15092011, 15102011,
				"Designing of the System", alice);
		Task codeA = new Task("Code (A)", 1102011, 1112011,
				"First half of the code", charlie);
		Task codeB = new Task("Code (B)", 1102011, 1112011,
				"Second half of the code", alice);
		Task test = new Task("Testing", 15102011, 1112011,
				"Testing of the software", bob);
		Task docu = new Task("Documentation", 15092011, 1112011,
				"Documentation of the software", charlie);
		//set the task array
		ArrayList<Task> task = new ArrayList<Task>();
		//add the task into the array
		task.add(reqana);
		task.add(sysdes);
		task.add(codeA);
		task.add(codeB);
		task.add(test);
		task.add(docu);
		//set the project for generic software (acceptance test)
		genericSoftware = new Project("Generic Software", bob, staff, task);
		ArrayList<Staff> emptystaff = new ArrayList<Staff>();
		ArrayList<Task> emptytask = new ArrayList<Task>();
		//set an empty project (empty task and staff arrays above)
		projempty = new Project("Empty", bob, emptystaff, emptytask);
	}

	// NORMAL CASES

	/**
	 * check the constructor initialises values correctly.
	 */
	@Test
	public void testProject() {
		assertEquals("the name of the project should be Generic Software",
				"Generic Software", genericSoftware.getProjectName());
		assertEquals("the project manager should be bob", bob, genericSoftware
				.getProjectManager());
	}

	/**
	 * check the default constructor has correct values.
	 */
	@Test
	public void testDefaultProject() {
		Project defaultProject = new Project();
		assertEquals("the name of a default project should be New Project",
				"New Project", defaultProject.getProjectName());
		assertEquals("default project's staff collection size should be 0",
				0, defaultProject.getNumberofStaff());
	}

	/**
	 * test correct number of staff.
	 */
	@Test
	public void testGetNumberofStaff() {
		assertEquals("the number of staff in projempty should be 0", 0,
				projempty.getNumberofStaff());
		assertEquals("the number of staff in genericSoftware should be 3", 3,
				genericSoftware.getNumberofStaff());
	}

	/**
	 * test correct number of tasks.
	 */
	@Test
	public void testGetNumberofTasks() {
		assertEquals("the number of tasks in projempty should be 0", 0,
				projempty.getNumberofTasks());
		assertEquals("the number of staff in genericSoftware should be 6", 6,
				genericSoftware.getNumberofTasks());
	}

	/**
	 * test correct appending of new staff.
	 */
	@Test
	public void testAppendStaff() {
		Staff eric = new Staff(0004, "Eric", "Genius");
		genericSoftware.addStaff(eric);
		assertEquals("after appending, number of staff should increase by 1",
				4, genericSoftware.getNumberofStaff());
		assertEquals("the index of the new employee eric should be 3", 3,
				genericSoftware.findStaff("Eric"));
	}

	/**
	 * test correct appending of new tasks.
	 */
	@Test
	public void testAppendTask() {
		Task codeC = new Task("Code (C)", 1112011, 15112011,
				"Third part of the code", bob);
		genericSoftware.addTask(codeC);
		assertEquals("after appending, number of tasks should increase by 1",
				7, genericSoftware.getNumberofTasks());
		projempty.addTask(codeC);
		assertEquals("after appending, number of tasks should increase by 1",
				1, projempty.getNumberofTasks());
	}

	/**
	 * check correct index of found staff.
	 */
	@Test
	public void testFindStaff() {
		assertEquals("employee charlie should be at index 2", 2,
				genericSoftware.findStaff("Charlie"));
		assertEquals("employee bob should be at index 0", 0, genericSoftware
				.findStaff("Bob"));
	}

	/**
	 * check correct index of found task.
	 */
	@Test
	public void testFindTask() {
		assertEquals("task Requirements Anlysis should be at index 0", 0,
				genericSoftware.findTask("Requirements Analysis"));
		assertEquals("task Code (B) should be at index 3", 3, genericSoftware
				.findTask("Code (B)"));
	}

	/**
	 * check removing of staff works correctly, moving next staff left one
	 * index.
	 */
	@Test
	public void testRemoveStaff() {
		genericSoftware.removeStaff("Alice");
		assertEquals("Remove reduces number of staff from 3 to 2", 2,
				genericSoftware.getNumberofStaff());
		assertEquals("After remove, charlie should be at index 1", 1,
				genericSoftware.findStaff("Charlie"));
	}

	/**
	 * check removing of staff index works correctly, moving next staff left one
	 * index.
	 */
	@Test
	public void testRemoveStaffIndex() {
		genericSoftware.removeStaff(1);
		assertEquals("Remove reduces number of staff from 3 to 2", 2,
				genericSoftware.getNumberofStaff());
		assertEquals("After remove, charlie should be at index 1", 1,
				genericSoftware.findStaff("Charlie"));
	}

	/**
	 * check removing of tasks works correctly, moving next tasks left one
	 * index.
	 */
	@Test
	public void testRemoveTask() {
		genericSoftware.removeTask("Testing");
		assertEquals("Remove reduces number of tasks from 6 to 5", 5,
				genericSoftware.getNumberofTasks());
		genericSoftware.removeTask("Code (B)");
		assertEquals("Remove reduces number of tasks from 5 to 4", 4,
				genericSoftware.getNumberofTasks());
	}

	/**
	 * check removing of task index works correctly, moving next tasks left one
	 * index.
	 */
	@Test
	public void testRemoveTaskIndex() {
		genericSoftware.removeTask(4);
		assertEquals("Remove reduces number of tasks from 6 to 5", 5,
				genericSoftware.getNumberofTasks());
		genericSoftware.removeTask(3);
		assertEquals("Remove reduces number of tasks from 5 to 4", 4,
				genericSoftware.getNumberofTasks());
	}

	/**
	 * check sorting by start date correctly organises the tasks.
	 */
	@Test
	public void testSortByStartDate() {
		genericSoftware.sortByStartDate();
		assertEquals("tasks should now be sorted by start date",
				"Requirements Analysis", genericSoftware.getCollectionTasks()
						.get(0).getTaskName());
		assertEquals("tasks should now be sorted by start date",
				"System Design", genericSoftware.getCollectionTasks().get(1)
						.getTaskName());
		assertEquals("tasks should now be sorted by start date",
				"Documentation", genericSoftware.getCollectionTasks().get(2)
						.getTaskName());
		assertEquals("tasks should now be sorted by start date", "Code (A)",
				genericSoftware.getCollectionTasks().get(3).getTaskName());
		assertEquals("tasks should now be sorted by start date", "Code (B)",
				genericSoftware.getCollectionTasks().get(4).getTaskName());
		assertEquals("tasks should now be sorted by start date", "Testing",
				genericSoftware.getCollectionTasks().get(5).getTaskName());
	}

	/**
	 * check sorting by end date correctly organises the tasks.
	 */
	@Test
	public void testSortByEndDate() {
		genericSoftware.sortByEndDate();
		assertEquals("tasks should now be sorted by end date",
				"Requirements Analysis", genericSoftware.getCollectionTasks()
						.get(0).getTaskName());
		assertEquals("tasks should now be sorted by end date", "System Design",
				genericSoftware.getCollectionTasks().get(1).getTaskName());
		assertEquals("tasks should now be sorted by end date", "Code (A)",
				genericSoftware.getCollectionTasks().get(2).getTaskName());
		assertEquals("tasks should now be sorted by end date", "Code (B)",
				genericSoftware.getCollectionTasks().get(3).getTaskName());
		assertEquals("tasks should now be sorted by end date", "Testing",
				genericSoftware.getCollectionTasks().get(4).getTaskName());
		assertEquals("tasks should now be sorted by end date", "Documentation",
				genericSoftware.getCollectionTasks().get(5).getTaskName());
	}

	/**
	 * check sorting by staff member names correctly organises the tasks.
	 */
	@Test
	public void testSortByStaffAssign() {
		genericSoftware.sortByStaffAssign();
		assertEquals("tasks should now be sorted by staff member names",
				"System Design", genericSoftware.getCollectionTasks().get(0)
						.getTaskName());
		assertEquals("tasks should now be sorted by staff member names",
				"Code (B)", genericSoftware.getCollectionTasks().get(1)
						.getTaskName());
		assertEquals("tasks should now be sorted by staff member names",
				"Requirements Analysis", genericSoftware.getCollectionTasks()
						.get(2).getTaskName());
		assertEquals("tasks should now be sorted by staff member names",
				"Testing", genericSoftware.getCollectionTasks().get(3)
						.getTaskName());
		assertEquals("tasks should now be sorted by staff member names",
				"Code (A)", genericSoftware.getCollectionTasks().get(4)
						.getTaskName());
		assertEquals("tasks should now be sorted by staff member names",
				"Documentation", genericSoftware.getCollectionTasks().get(5)
						.getTaskName());
	}

	// BOUNDARY CASES

	/**
	 * check -1 returned if staff not found.
	 */
	@Test
	public void testFindInvalidStaff() {
		assertEquals("non-existant employee john should be at index -1", -1,
				genericSoftware.findStaff("John"));
	}

	/**
	 * check -1 returned if task not found.
	 */
	@Test
	public void testFindInvalidTask() {
		assertEquals("non-existant task brainstorm should be at index -1", -1,
				genericSoftware.findTask("Brainstorm"));
	}

	// EXCEPTIONAL CASES



	/**
	 * check exceptional case when trying to remove non existent staff.
	 */
	@Test
	public void testRemoveInvalidStaff() {
		genericSoftware.removeStaff("John");
		assertEquals("Remove should fail for non-existing staff", 3,
				genericSoftware.getNumberofStaff());
	}

	/**
	 * check exceptional case when trying to remove non-existent tasks.
	 */
	@Test
	public void testRemoveInvalidTask() {
		genericSoftware.removeStaff("Code (H)");
		assertEquals("Remove should fail for non-existing task", 6,
				genericSoftware.getNumberofTasks());
	}
}
