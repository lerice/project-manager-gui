import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 Test cases for my Task class.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class StaffTest {
	/**
	 * global variables for commonly used Staff for testing.
	 */
	private Staff bob, charlie;

	/**
	 * Set up default projects for testing.
	 *
	 * @throws Exception
	 *             if fails
	 */
	@Before
	public void setUp() throws Exception {
		bob = new Staff(0001, "Bob", "Manager");
		charlie = new Staff(0003, "Charlie", "Intern");
	}

	// NORMAL cases

	/**
	 * check the constructor initialises values correctly.
	 */
	@Test
	public void testStaff() {
		assertEquals("the name of the staff member bob should be Bob", "Bob",
				bob.getStaffName());
		assertEquals("the employee number assigned to bob should be 1", 1, bob
				.getEmployeeNumber());
		assertEquals("the role of charlie should be Intern", "Intern", charlie
				.getStaffRole());
	}

	/**
	 * check constructor constructs proper values.
	 */
	@Test
	public void testStaffConstruct() {
		Staff alice = new Staff(0002, "Alice", "Developer");
		assertEquals("the name of the constructed staff should be Alice",
				"Alice", alice.getStaffName());
		assertEquals(
				"the employee number of the constructed staff should be 2", 2,
				alice.getEmployeeNumber());
		assertEquals(
				"the staff role of the constructed staff should be Developer",
				"Developer", alice.getStaffRole());
	}

	// BOUNDARY CASES

	/**
	 * check exception is thrown if employee number is 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStaffZeroEmployeeNumber() {
		Staff illegal = new Staff(0, "John", "my employee number is zero");
		//dummy code to avoid CheckStyle complaints of unused variables
		illegal.setEmployeeNumber(0007);
	}

	/**
	 * check exception is thrown if staff name is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStaffEmptyStaffName() {
		Staff illegal = new Staff(0005, "", "Has no name");
		//dummy code to avoid CheckStyle complaints of unused variables
		illegal.setStaffRole("cleaner");
	}

	/**
	 * check exception is thrown if staff role is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStaffEmptyStaffRole() {
		Staff illegal = new Staff(0005, "John", "");
		//dummy code to avoid CheckStyle complaints of unused variables
		illegal.setStaffRole("cleaner");
	}

	// EXCEPTIONAL CASES

	/**
	 * check exception is thrown if staff name isn't capitalised.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStaffIllegalStaffName() {
		Staff illegal = new Staff(0004, "illegal-name", "my name is unproper");
		//dummy code to avoid CheckStyle complaints of unused variables
		illegal.setStaffName("John");
	}

	/**
	 * check exception is thrown if staff name contains odd characters.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStaffIllegalStaffName2() {
		Staff illegal = new Staff(0004, "$h4Un", "my name is unproper");
		//dummy code to avoid CheckStyle complaints of unused variables
		illegal.setStaffName("John");
	}

	/**
	 * check exception is thrown if employee number is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStaffNegativeEmployeeNumber() {
		Staff illegal = new Staff(-7, "John", "my employee number is negative");
		//dummy code to avoid CheckStyle complaints of unused variables
		illegal.setEmployeeNumber(0005);
	}
}