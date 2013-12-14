/**
 * Staff to represent a person who works on a project.
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class Staff {
	/**
	 * Unique integer number assigned to each employee.
	 */
	private int employeeNumber;
	/**
	 * Name of the employee.
	 */
	private String name;
	/**
	 * Role of the employee.
	 */
	private String role;

	/**
	 * Constructor for a new staff member.
	 *
	 * @param employeeNumber int the employee number
	 * @param name String the employees name
	 * @param role String the employees role
	 */
	public Staff(int employeeNumber, String name, String role) {
		//check valid employee number, then set in global variable
		if (employeeNumber < 1) {
			String error = "Employee number must be greater than 1";
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException(error);
		}
		this.employeeNumber = employeeNumber;

		//check valid name (capitalised first letter), then set in name
		if (!name.matches("[A-Z][a-z-]*$")) {
			String error = "Name must have first letter capatilised";
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException(error);
		}
		this.name = name;

		//check valid role (non empty), then set in global variable
		if (role.equals("")) {
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException("Role must not be empty");
		}
		this.role = role;
	}

	/**
	 * Getter method for the employee number.
	 *
	 * @return the employees number.
	 */
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * Getter method for his/her name.
	 *
	 * @return String the employees name
	 */
	public String getStaffName() {
		return name;
	}

	/**
	 * Getter method for his/her role.
	 *
	 * @return String the employees role
	 */
	public String getStaffRole() {
		return role;
	}

	/**
	 * Setter method for the employees number.
	 *
	 * @param number the employee number
	 */
	public void setEmployeeNumber(int number) {
		employeeNumber = number;
	}

	/**
	 * Setter method for the employees name.
	 *
	 * @param name the employees name
	 */
	public void setStaffName(String name) {
		this.name = name;
	}

	/**
	 * Setter method for the employees role.
	 *
	 * @param role the employees role.
	 */
	public void setStaffRole(String role) {
		this.role = role;
	}
}
