import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 Test cases for my custom Task class.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class DateTest {
	/**
	 * global variables for commonly used Dates for testing.
	 */
	private Date one, two;

	/**
	 * Set up default projects for testing.
	 *
	 * @throws Exception
	 *             if fails
	 */
	@Before
	public void setUp() throws Exception {
		one = new Date(14061993);
		two = new Date(22111993);
	}

	// NORMAL CASES

	/**
	 * check the default constructor initialises values correctly.
	 */
	@Test
	public void testDate() {
		assertEquals("the date of one should be 14/06/1993", "14/6/1993", one
				.getDate());
		assertEquals("the input date of two should be 22111993", 22111993, two
				.getInputDate());
		assertEquals("the YYYYMMDD date of two should be 19931122", 19931122,
				two.getYYYYMMDDDate());
	}

	/**
	 * check constructor constructs proper values.
	 */
	@Test
	public void testDateConstruct() {
		Date three = new Date(6092011);
		assertEquals("the date of the constructed date should be 6/9/2011",
				"6/9/2011", three.getDate());
		assertEquals(
				"the YYYYMMDD date of the constructed date should be 20110906",
				20110906, three.getYYYYMMDDDate());
	}

	/**
	 * check form date properly forms a human readable date.
	 */
	@Test
	public void testFormDate() {
		String four = Date.formDate(14, 6, 1993);
		assertEquals("the formed date should be 14/6/1993", "14/6/1993", four);
	}

	// BOUNDARY CASES

	/**
	 * check exception is thrown if month is 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDateMonthZero() {
		Date illegal = new Date(14002011);
		// to avoid checkstyle errors of local variable not being read
		String dummy = illegal.getDate();
		dummy += "";
	}

	/**
	 * check exception is thrown if day is 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDateDayZero() {
		Date illegal = new Date(00122011);
		// to avoid checkstyle errors of local variable not being read
		String dummy = illegal.getDate();
		dummy += "";
	}

	// EXCEPTIONAL CASES

	/**
	 * check exception is thrown if date is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDateDayNegative() {
		Date illegal = new Date(-15122011);
		// to avoid checkstyle errors of local variable not being read
		String dummy = illegal.getDate();
		dummy += "";
	}

	/**
	 * check exception is thrown if date is not in ddmmyyyy form.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDateIllegalForm() {
		Date illegal = new Date(1234567890);
		// to avoid checkstyle errors of local variable not being read
		String dummy = illegal.getDate();
		dummy += "";
	}
}
