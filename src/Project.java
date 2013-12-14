import java.util.ArrayList;

/**
 * A class to represent a project.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class Project {
	/**
	 * The name of the task.
	 */
	private String name;
	/**
	 * A reference to the project manager.
	 */
	private Staff projectManager;
	/**
	 * An ArrayList of collection of staff working on the project.
	 */
	private ArrayList<Staff> collectionStaff;
	/**
	 * An ArrayList of collection of tasks in the project.
	 */
	private ArrayList<Task> collectionTasks;

	/**
	 * Constructor for a project.
	 *
	 * @param name
	 *            the project name
	 * @param projectManager
	 *            the staff member in charge
	 * @param a
	 *            the array of staff
	 * @param b
	 *            the array of tasks
	 */
	public Project(String name, Staff projectManager, ArrayList<Staff> a,
			ArrayList<Task> b) {
		this.name = name;
		this.projectManager = projectManager;
		this.collectionStaff = a;
		this.collectionTasks = b;
	}

	/**
	 * Easy constructor with default settings.
	 */
	public Project() {
		this.name = "New Project";
		this.collectionStaff = new ArrayList<Staff>();
		this.collectionTasks = new ArrayList<Task>();
	}

	/**
	 * Getter method for the project name.
	 *
	 * @return the projects name
	 */
	public String getProjectName() {
		return name;
	}

	/**
	 * Getter method for the project manager.
	 *
	 * @return a reference to the project manager
	 */
	public Staff getProjectManager() {
		return projectManager;
	}

	/**
	 * Getter method for the arrayList of staff.
	 *
	 * @return the arrayList of staff
	 */
	public ArrayList<Staff> getCollectionStaff() {
		return collectionStaff;
	}

	/**
	 * Getter method for the arrayList of tasks.
	 *
	 * @return the arrayList of tasks
	 */
	public ArrayList<Task> getCollectionTasks() {
		return collectionTasks;
	}

	/**
	 * Setter method for the project name.
	 *
	 * @param a
	 *            the project name
	 */
	public void setProjectName(String a) {
		name = a;
	}

	/**
	 * Setter method for the project manager. Other setter methods will not be
	 * needed.
	 *
	 * @param a
	 *            the project manager
	 */
	public void setProjectManager(Staff a) {
		projectManager = a;
	}

	/**
	 * Add a new staff member to the end of the list.
	 *
	 * @param a
	 *            the staff member
	 */
	public void addStaff(Staff a) {
		collectionStaff.add(a);
	}

	/**
	 * Remove a staff member from the list.
	 *
	 * @param staffName
	 *            the name of the staff member (assume the staff member exists,
	 *            if not do nothing)
	 */
	public void removeStaff(String staffName) {
		//get the length of the staff array once (efficient code)
		int length = getNumberofStaff();
		//iterate through the array
		for (int i = 0; i < length; i++) {
			//if the current staff member matches the parameter name
			if (collectionStaff.get(i).getStaffName() == staffName) {
				//delete the staff member
				collectionStaff.remove(i);
				//exit loop, end method
				break;
			}
		}
	}

	/**
	 * Remove a task from the list.
	 *
	 * @param index
	 *            the index of the staff (assuming the staff index exists)
	 */
	public void removeStaff(int index) {
		collectionStaff.remove(index);
	}

	/**
	 * Add a new task to the end of the list.
	 *
	 * @param a
	 *            the task
	 */
	public void addTask(Task a) {
		collectionTasks.add(a);
	}

	/**
	 * Remove a task from the list.
	 *
	 * @param taskName
	 *            the name of the task (assume the task exists, if not do
	 *            nothing)
	 */
	public void removeTask(String taskName) {
		//get the length of the task array once (efficient code)
		int length = getNumberofTasks();
		//iterate through the array
		for (int i = 0; i < length; i++) {
			//if the current task matches the parameter name
			if (collectionTasks.get(i).getTaskName() == taskName) {
				//remove the task
				collectionTasks.remove(i);
				//exit loop, end method
				break;
			}
		}
	}

	/**
	 * Remove a task from the list.
	 *
	 * @param index
	 *            the index of the task (assuming the task index exists)
	 */
	public void removeTask(int index) {
		collectionTasks.remove(index);
	}

	/**
	 * Method to return the index of a staff member given their name.
	 *
	 * @param name
	 *            the name of the employee
	 * @return the index in the array, -1 if not found.
	 */
	public int findStaff(String name) {
		//get the length of the staff array once (efficient code)
		int length = getNumberofStaff();
		//iterate through the array
		for (int i = 0; i < length; i++) {
			//if the current staff member matches parameter name
			if (collectionStaff.get(i).getStaffName() == name) {
				//return its index
				return i;
			}
		}
		//if the staff was not found, return -1 (error)
		return -1;
	}

	/**
	 * Method to return the index of a task given its name.
	 *
	 * @param name
	 *            the name of the task
	 * @return the index in the array, -1 if not found.
	 */
	public int findTask(String name) {
		//get the length of the task array once (efficient code)
		int length = getNumberofTasks();
		//iterate through the array
		for (int i = 0; i < length; i++) {
			//if the current task name matches parameter name
			if (collectionTasks.get(i).getTaskName() == name) {
				//return its index
				return i;
			}
		}
		//if the task was not found, return -1 (error)
		return -1;
	}

	/**
	 * Report the number of staff currently registered.
	 *
	 * @return int for number of staff members
	 */
	public int getNumberofStaff() {
		return collectionStaff.size();
	}

	/**
	 * Report the number of tasks.
	 *
	 * @return int for number of tasks
	 */
	public int getNumberofTasks() {
		return collectionTasks.size();
	}

	/**
	 * Sorts the collection of tasks by start dates using an insertion sort
	 * algorithm.
	 */
	public void sortByStartDate() {
		//get the length of the task array once (efficient code)
		int length = getNumberofTasks();
		//iterate through the array, starting from 1
		for (int pass = 1; pass < length; pass++) {
			//store the current task in a temporary variable
			Task tmp = collectionTasks.get(pass);
			//store the position
			int pos = pass - 1;
			//iterate through the array, using an insertion sort algorithm.
			//according to task start date
			while (pos >= 0
					&& collectionTasks.get(pos).getStartDateDate()
							.getYYYYMMDDDate() > tmp.getStartDateDate()
							.getYYYYMMDDDate()) {
				collectionTasks.set(pos + 1, collectionTasks.get(pos));
				pos--;
			}
			collectionTasks.set(pos + 1, tmp);
		}
	}

	/**
	 * Sorts the collection of tasks by end dates using an insertion sort
	 * algorithm.
	 */
	public void sortByEndDate() {
		//get the length of the task array once (efficient code)
		int length = getNumberofTasks();
		//iterate through the array, starting from 1
		for (int pass = 1; pass < length; pass++) {
			//store the current task in a temporary variable
			Task tmp = collectionTasks.get(pass);
			//store the position
			int pos = pass - 1;
			//iterate through the array, using an insertion sort algorithm.
			//according to task end date
			while (pos >= 0
					&& collectionTasks.get(pos).getEndDateDate()
							.getYYYYMMDDDate() > tmp.getEndDateDate()
							.getYYYYMMDDDate()) {
				collectionTasks.set(pos + 1, collectionTasks.get(pos));
				pos--;
			}
			collectionTasks.set(pos + 1, tmp);
		}
	}

	/**
	 * Sorts the collection of tasks by staff member names using an insertion
	 * sort algorithm.
	 */
	public void sortByStaffAssign() {
		//get the length of the task array once (efficient code)
		int length = getNumberofTasks();
		//iterate through the array, starting from 1
		for (int pass = 1; pass < length; pass++) {
			//store the current task in a temporary variable
			Task tmp = collectionTasks.get(pass);
			//store the position
			int pos = pass - 1;
			//iterate through the array, using an insertion sort algorithm.
			//according the increasing staff name
			while (pos >= 0	&& collectionTasks.get(pos).getStaffAssign()
					.getStaffName().compareTo(tmp.getStaffAssign()
							.getStaffName()) > 0) {
				collectionTasks.set(pos + 1, collectionTasks.get(pos));
				pos--;
			}
			collectionTasks.set(pos + 1, tmp);
		}
	}
}
