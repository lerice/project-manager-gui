/**
 * The basic class for each task in the project.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class Task {
	/**
	 * Name of the task.
	 */
	private String name;
	/**
	 * Starting date of the task.
	 */
	private Date startDate;
	/**
	 * Ending date of the task.
	 */
	private Date endDate;
	/**
	 * Description of the task.
	 */
	private String description;
	/**
	 * Assigned staff member for the task.
	 */
	private Staff staffAssign;
	/**
	 * True or false for completion of the task.
	 */
	private boolean completed;

	/**
	 * Constructor for a task.
	 *
	 * @param name
	 *            The name of the task
	 * @param startDate
	 *            the start date (ddmmyyyy) for the task
	 * @param endDate
	 *            the end date (ddmmyyyy) for the task
	 * @param description
	 *            a description of the task
	 * @param staffAssign
	 *            the employee in charge of this task
	 */
	public Task(String name, int startDate, int endDate, String description,
			Staff staffAssign) {
		//check name is valid (non empty), then set in global variable
		if (name.equals("")) {
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException("Task Name must be filled in");
		}
		this.name = name;

		//check start date is valid (valid date), then set in global variable
		try {
			this.startDate = new Date(startDate);
		} catch (IllegalArgumentException e) {
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException("Start Date is invalid.");
		}

		//check end date is valid (valid date), then set in global variable
		try {
			this.endDate = new Date(endDate);
		} catch (IllegalArgumentException e) {
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException("End Date is invalid.");
		}

		//check start date is before end date
		if (this.startDate.getYYYYMMDDDate() > this.endDate.getYYYYMMDDDate()) {
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException(
					"Start Date must be before End Date.");
		}

		//check description is valid (non empty), then set in global variable
		if (description.equals("")) {
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException(
					"Task Description must be filled in");
		}
		this.description = description;

		//set the given staff into the global variable (no checks needed)
		this.staffAssign = staffAssign;
		//set task completion to false (its a new task)
		completed = false;
	}

	/**
	 * Getter method for the task name.
	 *
	 * @return the name of the task
	 */
	public String getTaskName() {
		return name;
	}

	/**
	 * Getter method for the start date (dd/mm/yyyy).
	 *
	 * @return a string format of the start date
	 */
	public String getStartDate() {
		return startDate.getDate();
	}

	/**
	 * Getter method for the end date (dd/mm/yyyy).
	 *
	 * @return a string format of the end date
	 */
	public String getEndDate() {
		return endDate.getDate();
	}

	/**
	 * Getter method for the start date in Date form.
	 *
	 * @return the object Date for the start date
	 */
	public Date getStartDateDate() {
		return startDate;
	}

	/**
	 * Getter method for the end date in Date form.
	 *
	 * @return the object Date for the end date
	 */
	public Date getEndDateDate() {
		return endDate;
	}

	/**
	 * Getter method for the task description.
	 *
	 * @return the task description
	 */
	public String getTaskDescription() {
		return description;
	}

	/**
	 * Getter method for the assigned staff member.
	 *
	 * @return a reference to the staff member
	 */
	public Staff getStaffAssign() {
		return staffAssign;
	}

	/**
	 * Getter method for the completed boolean variable.
	 *
	 * @return true or false if the task is completed
	 */
	public boolean getCompleted() {
		return completed;
	}

	/**
	 * Setter method for the task name.
	 *
	 * @param name
	 *            the name of the task
	 */
	public void setTaskName(String name) {
		this.name = name;
	}

	/**
	 * Setter method for the start date.
	 *
	 * @param date
	 *            the start date in ddmmyyyy
	 */
	public void setStartDate(int date) {
		try {
			this.startDate = new Date(date);
		} catch (IllegalArgumentException e) {
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException("Start Date is invalid.");
		}
	}

	/**
	 * Setter method for the end date.
	 *
	 * @param date
	 *            the end date in ddmmyyyy
	 */
	public void setEndDate(int date) {
		try {
			this.endDate = new Date(date);
		} catch (IllegalArgumentException e) {
			//throw IllegalArgumentException if non valid entry
			throw new IllegalArgumentException("End Date is invalid.");
		}
	}

	/**
	 * The setter method for the task description.
	 *
	 * @param desc
	 *            the task description
	 */
	public void setTaskDescription(String desc) {
		this.description = desc;
	}

	/**
	 * The setter method for the assigned staff member.
	 *
	 * @param ass
	 *            the staff member
	 */
	public void setStaffAssign(Staff ass) {
		this.staffAssign = ass;
	}

	/**
	 * The setter method for the completed boolean variable.
	 *
	 * @param a
	 *            true or false regarding completion
	 */
	public void setCompleted(boolean a) {
		this.completed = a;
	}
}
