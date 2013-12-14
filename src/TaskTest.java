import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 Test cases for my Task class.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class TaskTest {
	/**
	 * global variables for commonly used Tasks for testing.
	 */
	private Task reqana, codeA;
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
		bob = new Staff(0001, "Bob", "Manager");
		alice = new Staff(0002, "Alice", "Developer");
		charlie = new Staff(0003, "Charlie", "Intern");
		reqana = new Task("Requirements Analysis", 1092011, 1102011,
				"Analysing the requirements", bob);
		codeA = new Task("Code (A)", 1102011, 1112011,
				"First half of the code", charlie);
	}

	// NORMAL CASES

	/**
	 * check the constructor initialises values correctly.
	 */
	@Test
	public void testTask() {
		assertEquals("the name of the task should be Requirements Analysis",
				"Requirements Analysis", reqana.getTaskName());
		assertEquals("the staff member assigned to this task should be bob",
				bob, reqana.getStaffAssign());
		assertEquals("the start date of task Code (A) should be 1/10/2011",
				"1/10/2011", codeA.getStartDate());
		assertEquals("the end date of task Code (A) should be 1/11/2011",
				"1/11/2011", codeA.getEndDate());
	}

	/**
	 * check constructor constructs proper values.
	 */
	@Test
	public void testTaskConstruct() {
		Task testing = new Task("Testing", 15102011, 1112011,
				"Testing of the software", bob);
		assertEquals("the name of the constructed task should be Testing",
				"Testing", testing.getTaskName());
		assertEquals(
				"the start date of the constructed task should be 15/10/2011",
				"15/10/2011", testing.getStartDate());
		assertEquals(
				"the end date of the constructed task should be 1/11/2011",
				"1/11/2011", testing.getEndDate());
		assertEquals("the description should be Testing of the software",
				"Testing of the software", testing.getTaskDescription());
		assertEquals("the staff member assigned should be bob", bob, testing
				.getStaffAssign());
		assertEquals("the boolean completed variable should be false", false,
				testing.getCompleted());
	}

	// BOUNDARY CASES

	/**
	 * check exception is thrown if task name is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTaskEmptyName() {
		Task illegal = new Task("", 14062011, 15062011, "empty task name",
				charlie);
		illegal.setTaskName("laundry");
	}

	/**
	 * check exception is thrown if task description is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTaskEmptyDescription() {
		Task illegal = new Task("Laundry", 14062011, 15062011, "", charlie);
		illegal.setTaskDescription("laundry");
	}

	// EXCEPTIONAL CASES

	/**
	 * check exception is thrown if start date is illegal.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTaskConstructIllegalStartDate() {
		Task illegal = new Task("Illegal task", 30022011, 15032011,
				"Illegal start date", charlie);
		illegal.setStartDate(1032011);
	}

	/**
	 * check exception is thrown if start date is set to an illegal date.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTaskSetIllegalStartDate() {
		Task illegal = new Task("Initially legal task", 15122011, 1012012,
				"Initially legal task", alice);
		illegal.setStartDate(15132011);
	}

	/**
	 * check exception is thrown if end date is illegal.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTaskConstructIllegalEndDate() {
		Task illegal = new Task("Illegal task", 01022011, 30022011,
				"Illegal end date", charlie);
		illegal.setEndDate(1032011);
	}

	/**
	 * check exception is thrown if end date is set to an illegal date.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTaskSetIllegalEndDate() {
		Task illegal = new Task("Initially legal task", 15122011, 1012012,
				"Initially legal task", alice);
		illegal.setEndDate(15132011);
	}

	/**
	 * check exception is thrown if start date is after end date.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTaskEndDateBeforeStartDate() {
		Task illegal = new Task("Illegal task", 14062011, 13062011,
				"Dates are mixed up", charlie);
		illegal.setEndDate(17062011);
	}
}
