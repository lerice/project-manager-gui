import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.util.Calendar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Graphical User Interface for my Project Manager.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class ProjectGUI extends JFrame {
	/**
	 * this variable is purely to remove the CheckStyle warning. has no current
	 * influence on the class itself.
	 */
	static final long serialVersionUID = 0L;

	/**
	 * global variables for labels.
	 */
	private JLabel projectNameLabel, staffLabel, staffNameLabel,
			staffNumberLabel, staffRoleLabel, taskLabel, managerLabel,
			managerNameLabel, copyright, currentDateLabel, overdueLabel;
	/**
	 * global variables for panels.
	 */
	private JPanel contentPane;
	/**
	 * global variables for buttons.
	 */
	private JButton allTaskButton, deleteTaskButton, editStaffButton,
			editTaskButton, saveButton, exitButton, loadButton, newStaffButton,
			newTaskButton, removeStaffButton, editProjectNameButton,
			sortButton;
	/**
	 * global variables for tables.
	 */
	private JTable staffTable, taskTable;
	/**
	 * global variables for scroll panes for tables.
	 */
	private JScrollPane staffTableScrollPane, taskTableScrollPane;
	/**
	 * global variables for table models.
	 */
	private DefaultTableModel staffTableModel, taskTableModel;

	/**
	 * global variable for the current project to display.
	 */
	private Project currentProject;
	/**
	 * global variable for the current selected staff in the staff table.
	 */
	private int currentSelectedStaff = -1;
	/**
	 * global variable for the current selected task in the whole task table.
	 */
	private int currentSelectedTask = -1;
	/**
	 * global variable for the current selected task in the current task table.
	 */
	private int currentSelectedTaskTable = -1;
	/**
	 * global variable for the current employee number.
	 */
	private int currentEmployeeNumber = 1;
	/**
	 * global variable for the current date in my custom Date format.
	 */
	private Date currentDate;

	/**
	 * Constructor.
	 *
	 * @param project
	 *            the project to implement
	 */
	public ProjectGUI(Project project) {
		// create the JFrame
		super("ProjectGUI");
		// set the global variable to the current project for displaying
		currentProject = project;
		// initialise all components
		initializeComponent();
		// reveal the JFrame
		this.setVisible(true);
	}

	/***************************************************************************
	 * GETTER AND SETTER METHODS USED FOR OTHER GUI CLASSES INTERACTIONS *
	 **************************************************************************/

	/**
	 * Getter method for the current displayed project.
	 *
	 * @return the project for editing
	 */
	public Project getProject() {
		return currentProject;
	}

	/**
	 * Getter method for the current selected staff index.
	 *
	 * @return the index of the current selected staff
	 */
	public int getCurrentSelectedStaff() {
		return currentSelectedStaff;
	}

	/**
	 * Getter method for the current selected task index in the current table.
	 *
	 * @return the index of the current selected task
	 */
	public int getCurrentSelectedTaskTable() {
		return currentSelectedTaskTable;
	}

	/**
	 * Getter method for the current selected task index in the whole table.
	 *
	 * @return the index of the current selected task
	 */
	public int getCurrentEmployeeNumber() {
		return currentEmployeeNumber;
	}

	/**
	 * Increment the current employee number by one.
	 */
	public void increaseCurrentEmployeeNumber() {
		currentEmployeeNumber++;
	}

	/**
	 * Getter method for the staff table model.
	 *
	 * @return the staff table model.
	 */
	public DefaultTableModel getStaffTableModel() {
		return staffTableModel;
	}

	/**
	 * Getter method for the task table model.
	 *
	 * @return the task table model.
	 */
	public DefaultTableModel getTaskTableModel() {
		return taskTableModel;
	}

	/**
	 * Getter method for the currentDate.
	 *
	 * @return the current date in my custom Date format.
	 */
	public Date getCurrentDate() {
		return currentDate;
	}

	/**
	 * Setter method for the project name label.
	 *
	 * @param name
	 *            the name of the project
	 */
	public void setProjectNameLabel(String name) {
		projectNameLabel.setText(name);
	}

	/**
	 * Setter method for the manager name label.
	 *
	 * @param name
	 *            the name of the manager
	 */
	public void setManagerNameLabel(String name) {
		managerNameLabel.setText(name);
	}

	/**
	 * Setter method for enabling the new task button.
	 *
	 * @param b
	 *            true or false
	 */
	public void setNewTaskButton(boolean b) {
		newTaskButton.setEnabled(b);
	}

	/**
	 * Initialise the components.
	 */
	private void initializeComponent() {
		// create labels
		projectNameLabel = new JLabel();
		managerLabel = new JLabel();
		managerNameLabel = new JLabel();
		staffRoleLabel = new JLabel();
		staffNumberLabel = new JLabel();
		staffNameLabel = new JLabel();
		taskLabel = new JLabel();
		staffLabel = new JLabel();
		currentDateLabel = new JLabel();
		overdueLabel = new JLabel();

		// create buttons
		newTaskButton = new JButton();
		deleteTaskButton = new JButton();
		editTaskButton = new JButton();
		allTaskButton = new JButton();
		newStaffButton = new JButton();
		removeStaffButton = new JButton();
		editStaffButton = new JButton();
		exitButton = new JButton();
		saveButton = new JButton();
		loadButton = new JButton();
		editProjectNameButton = new JButton();
		sortButton = new JButton();

		// use helper methods for creating tables (long method lengths)
		makeStaffTable();
		makeTaskTable();

		// use helper methods for setting the button action handlers
		setButtons();

		// set todays current date using the Calendar import
		int currentDay = Calendar.getInstance().get(Calendar.DATE);
		// January starts from 0 apparently
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		currentDate = new Date(currentDay * 1000000 + currentMonth * 10000
				+ currentYear);

		// set custom labels text (with fonts and sizes)
		projectNameLabel.setFont(new Font("Traditional Arabic", 1, 30));
		projectNameLabel.setText(currentProject.getProjectName());
		copyright = new JLabel();
		copyright.setText("© Eric Tan 2011");
		copyright.setFont(new Font("Arial", 1, 9));
		overdueLabel.setText("* indicates task is overdue");
		overdueLabel.setFont(new Font("Arial", 1, 9));

		// set other labels with default text
		managerLabel.setText("Manager:");
		managerNameLabel.setText("N/A");
		staffLabel.setText("Staff:");
		staffRoleLabel.setText("Role:");
		staffNumberLabel.setText("Number:");
		staffNameLabel.setText("Name:");
		taskLabel.setText("Tasks:");
		currentDateLabel.setText("Today's date is: " + currentDate.getDate());

		// set the panel
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(208, 212, 224));

		// add all components into the single panel (easy design)
		addComponent(contentPane, projectNameLabel, 280, 20, 300, 50);
		addComponent(contentPane, managerLabel, 10, 45, 80, 15);
		addComponent(contentPane, managerNameLabel, 80, 45, 200, 15);
		addComponent(contentPane, staffLabel, 10, 90, 40, 15);
		addComponent(contentPane, taskLabel, 190, 90, 50, 15);
		addComponent(contentPane, staffNumberLabel, 10, 330, 100, 15);
		addComponent(contentPane, staffNameLabel, 10, 350, 100, 15);
		addComponent(contentPane, staffRoleLabel, 10, 370, 100, 15);
		addComponent(contentPane, copyright, 365, 386, 70, 10);
		addComponent(contentPane, currentDateLabel, 630, 70, 160, 25);
		addComponent(contentPane, overdueLabel, 515, 350, 120, 10);

		addComponent(contentPane, staffTableScrollPane, 10, 110, 160, 210);
		addComponent(contentPane, taskTableScrollPane, 190, 110, 510, 240);

		addComponent(contentPane, editProjectNameButton, 680, 40, 100, 25);
		addComponent(contentPane, loadButton, 710, 10, 70, 25);
		addComponent(contentPane, newTaskButton, 710, 100, 70, 25);
		addComponent(contentPane, deleteTaskButton, 710, 130, 70, 25);
		addComponent(contentPane, editTaskButton, 710, 160, 70, 25);
		addComponent(contentPane, sortButton, 710, 190, 70, 25);
		addComponent(contentPane, allTaskButton, 710, 220, 70, 25);
		addComponent(contentPane, newStaffButton, 190, 360, 100, 25);
		addComponent(contentPane, removeStaffButton, 300, 360, 110, 25);
		addComponent(contentPane, editStaffButton, 420, 360, 100, 25);
		addComponent(contentPane, saveButton, 630, 360, 70, 25);
		addComponent(contentPane, exitButton, 710, 360, 70, 25);

		// set JFrame specificities
		this.setTitle("Project Manager");
		this.setLocation(new Point(50, 50));
		this.setSize(new Dimension(800, 430));
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Helper method to handle the initial implementation of action listeners
	 * for each of the buttons in the GUI. Used to decrease the method length of
	 * initialise Components, as to avoid CheckStyle complaints.
	 */
	private void setButtons() {
		loadButton.setText("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loadButtonActionPerformed(evt);
			}
		});

		newTaskButton.setText("New");
		newTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newTaskButtonActionPerformed(evt);
			}
		});
		newTaskButton.setEnabled(false);

		deleteTaskButton.setText("Delete");
		deleteTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteTaskButtonActionPerformed(evt);
			}
		});
		deleteTaskButton.setEnabled(false);

		editTaskButton.setText("Edit");
		editTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editTaskButtonActionPerformed(evt);
			}
		});
		editTaskButton.setEnabled(false);

		allTaskButton.setText("All");
		allTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				allTaskButtonActionPerformed(evt);
			}
		});

		newStaffButton.setText("New Staff");
		newStaffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newStaffButtonActionPerformed(evt);
			}
		});

		removeStaffButton.setText("Remove Staff");
		removeStaffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				removeStaffButtonActionPerformed(evt);
			}
		});
		removeStaffButton.setEnabled(false);

		editStaffButton.setText("Edit Staff");
		editStaffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editStaffButtonActionPerformed(evt);
			}
		});
		editStaffButton.setEnabled(false);

		exitButton.setText("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				exitButtonActionPerformed(evt);
			}
		});

		saveButton.setText("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});

		editProjectNameButton.setText("Edit Name");
		editProjectNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editProjectNameButtonActionPerformed(evt);
			}
		});

		sortButton.setText("Sort");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				sortButtonActionPerformed(evt);
			}
		});
	}

	/**
	 * Helper method to a task to the current task table.
	 *
	 * @param a
	 *            the task to add
	 */
	private void addTasksToTable(Task a) {
		// check if the task is overdue
		if (a.getEndDateDate().getYYYYMMDDDate() < currentDate
				.getYYYYMMDDDate()
				&& !a.getCompleted()) {
			// if yes, add the task in and indicate it is overdue
			taskTableModel.addRow(new Object[] { a.getTaskName(),
					a.getStartDate(), a.getEndDate() + "*",
					a.getTaskDescription(), a.getStaffAssign().getStaffName(),
					a.getCompleted() });
		} else {
			// else, add the task in normally
			taskTableModel.addRow(new Object[] { a.getTaskName(),
					a.getStartDate(), a.getEndDate(), a.getTaskDescription(),
					a.getStaffAssign().getStaffName(), a.getCompleted() });
		}
	}

	/**
	 * Helper method to handle the initial construction of the staff table. Used
	 * to decrease the method length of initialiseComponents, as to avoid
	 * CheckStyle complaints.
	 */
	private void makeStaffTable() {
		// set the table model data, for the table to use
		staffTableModel = new DefaultTableModel();
		staffTableModel.addColumn("Name");
		staffTable = new JTable(staffTableModel) {
			/**
			 * this variable is purely to remove the CheckStyle warning. has no
			 * current influence on the class itself.
			 */
			static final long serialVersionUID = 0L;

			// disallow editing in all cells
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		// set table specificities
		staffTable.getTableHeader().setReorderingAllowed(false);
		staffTable.getColumnModel().getSelectionModel().setSelectionMode(
				javax.swing.ListSelectionModel.SINGLE_SELECTION);
		staffTable.getColumnModel().getColumn(0).setResizable(false);

		// set a selection listener for the table
		ListSelectionModel staffSelectionModel = staffTable.getSelectionModel();
		staffSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		staffSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						// when a new staff is selected, task is unselected
						// disable edit and delete buttons (for a selected task)
						editTaskButton.setEnabled(false);
						deleteTaskButton.setEnabled(false);

						// set the current selected staff in the global variable
						currentSelectedStaff = staffTable.getSelectedRows()[0];

						// set the staff labels, below the table
						String name = (String) staffTable.getValueAt(
								currentSelectedStaff, 0);
						Staff staff = currentProject.getCollectionStaff().get(
								currentProject.findStaff(name));
						removeStaffButton.setEnabled(true);
						staffNameLabel.setText("Name: " + staff.getStaffName());
						staffRoleLabel.setText("Role: " + staff.getStaffRole());
						staffNumberLabel.setText("Number: "
								+ staff.getEmployeeNumber());

						// as a staff is selected, allow editing
						editStaffButton.setEnabled(true);

						// update the task table according to the selected staff
						contentPane.remove(taskTableScrollPane);

						taskTableModel = new DefaultTableModel();
						taskTableModel.addColumn("Task Name");
						taskTableModel.addColumn("Start Date");
						taskTableModel.addColumn("End Date");
						taskTableModel.addColumn("Description");
						taskTableModel.addColumn("Staff Member");
						taskTableModel.addColumn("Completion");

						int numTask = currentProject.getNumberofTasks();
						for (int i = 0; i < numTask; i++) {
							Task a = currentProject.getCollectionTasks().get(i);
							// only add the tasks that belong to the staff
							if (a.getStaffAssign()
									.getStaffName().equals(name)) {
								addTasksToTable(a);
							}
						}

						taskTable = new JTable(taskTableModel) {
							/**
							 * this variable is purely to remove the CheckStyle
							 * warning. has no current influence on the class
							 * itself.
							 */
							static final long serialVersionUID = 0L;

							// disable cell edits
							public boolean isCellEditable(int rowIndex,
									int vColIndex) {
								return false;
							}

							public Class<?> getColumnClass(int columnIndex) {
								if (columnIndex <= 4) {
									return String.class;
								} else {
									return Boolean.class;
								}
							}
						};
						// set all table specificities
						taskTable.setColumnSelectionAllowed(false);
						taskTable.setRowSelectionAllowed(true);
						taskTable.getTableHeader().setReorderingAllowed(false);
						taskTable.getColumnModel().getSelectionModel()
								.setSelectionMode(
										ListSelectionModel.SINGLE_SELECTION);

						ListSelectionModel taskSelectionModel = taskTable
								.getSelectionModel();
						taskSelectionModel
								.setSelectionMode(ListSelectionModel
										.SINGLE_INTERVAL_SELECTION);
						taskSelectionModel
								.addListSelectionListener(new
										ListSelectionListener() {
									public void valueChanged(
											ListSelectionEvent e) {
										currentSelectedTaskTable = taskTable
												.getSelectedRows()[0];
										int a = currentSelectedTaskTable;
										String taskName = (String) taskTable
												.getValueAt(a, 0);
										currentSelectedTask = currentProject
												.findTask(taskName);
										editTaskButton.setEnabled(true);
										deleteTaskButton.setEnabled(true);
									}
								});

						taskTableScrollPane = new JScrollPane(taskTable);

						// update the task table
						addComponent(contentPane, taskTableScrollPane, 190,
								110, 510, 240);
					}
				});
		// set the table scroll pane (allow scrolling)
		staffTableScrollPane = new JScrollPane(staffTable);
	}

	/**
	 * Helper method to handle the initial construction of the task table. Used
	 * to decrease the method length of initialiseComponents, as to avoid
	 * CheckStyle complaints.
	 */
	private void makeTaskTable() {
		// set the table model data for the table to use
		taskTableModel = new DefaultTableModel();
		taskTableModel.addColumn("Task Name");
		taskTableModel.addColumn("Start Date");
		taskTableModel.addColumn("End Date");
		taskTableModel.addColumn("Description");
		taskTableModel.addColumn("Staff Member");
		taskTableModel.addColumn("Completion");
		taskTable = new JTable(taskTableModel) {
			/**
			 * this variable is purely to remove the CheckStyle warning. has no
			 * current influence on the class itself.
			 */
			static final long serialVersionUID = 0L;

			// disable edits
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex <= 4) {
					return String.class;
				} else {
					return Boolean.class;
				}
			}
		};
		// set table specificities
		taskTable.setColumnSelectionAllowed(false);
		taskTable.setRowSelectionAllowed(true);
		taskTable.getTableHeader().setReorderingAllowed(false);
		taskTable.getColumnModel().getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		// set table selection listener
		ListSelectionModel taskSelectionModel = taskTable.getSelectionModel();
		taskSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		taskSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						currentSelectedTask = taskTable.getSelectedRows()[0];
						currentSelectedTaskTable = currentSelectedTask;

						deleteTaskButton.setEnabled(true);
						editTaskButton.setEnabled(true);
					}
				});

		taskTableScrollPane = new JScrollPane(taskTable);
	}

	/**
	 * Handler for when the exit button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void exitButtonActionPerformed(ActionEvent evt) {
		if (exitButton.isEnabled()) {
			this.dispose();
		}
	}

	/**
	 * Handler for when the new staff button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void newStaffButtonActionPerformed(ActionEvent evt) {
		if (newStaffButton.isEnabled()) {
			new StaffGUI(this);
		}
	}

	/**
	 * Handler for when the remove staff button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void removeStaffButtonActionPerformed(ActionEvent evt) {
		contentPane.remove(staffTableScrollPane);
		// remove the staff from the array
		currentProject.removeStaff(currentSelectedStaff);

		// update the table
		staffTableModel = new DefaultTableModel();
		staffTableModel.addColumn("Name");

		int numStaff = currentProject.getNumberofStaff();
		for (int i = 0; i < numStaff; i++) {
			staffTableModel.addRow(new String[] { currentProject
					.getCollectionStaff().get(i).getStaffName() });
		}

		staffTable = new JTable(staffTableModel) {
			/**
			 * this variable is purely to remove the CheckStyle warning. has no
			 * current influence on the class itself.
			 */
			static final long serialVersionUID = 0L;

			// disable cell edits
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		// set table specificities
		staffTable.getTableHeader().setReorderingAllowed(false);
		staffTable.getColumnModel().getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		staffTable.getColumnModel().getColumn(0).setResizable(false);
		// set table selection listener
		ListSelectionModel staffSelectionModel = staffTable.getSelectionModel();
		staffSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		staffSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						editTaskButton.setEnabled(false);
						deleteTaskButton.setEnabled(false);

						currentSelectedStaff = staffTable.getSelectedRows()[0];

						String name = (String) staffTable.getValueAt(
								currentSelectedStaff, 0);
						Staff staff = currentProject.getCollectionStaff().get(
								currentProject.findStaff(name));
						removeStaffButton.setEnabled(true);
						staffNameLabel.setText("Name: " + staff.getStaffName());
						staffRoleLabel.setText("Role: " + staff.getStaffRole());
						staffNumberLabel.setText("Number: "
								+ staff.getEmployeeNumber());

						editStaffButton.setEnabled(true);

						contentPane.remove(taskTableScrollPane);

						taskTableModel = new DefaultTableModel();
						taskTableModel.addColumn("Task Name");
						taskTableModel.addColumn("Start Date");
						taskTableModel.addColumn("End Date");
						taskTableModel.addColumn("Description");
						taskTableModel.addColumn("Staff Member");
						taskTableModel.addColumn("Completion");

						int numTask = currentProject.getNumberofTasks();
						for (int i = 0; i < numTask; i++) {
							Task a = currentProject.getCollectionTasks().get(i);
							if (a.getStaffAssign()
									.getStaffName().equals(name)) {
								addTasksToTable(a);
							}
						}

						taskTable = new JTable(taskTableModel) {
							/**
							 * this variable is purely to remove the CheckStyle
							 * warning. has no current influence on the class
							 * itself.
							 */
							static final long serialVersionUID = 0L;

							public boolean isCellEditable(int rowIndex,
									int vColIndex) {
								return false;
							}

							public Class<?> getColumnClass(int columnIndex) {
								if (columnIndex <= 4) {
									return String.class;
								} else {
									return Boolean.class;
								}
							}
						};
						taskTable.setColumnSelectionAllowed(false);
						taskTable.setRowSelectionAllowed(true);
						taskTable.getTableHeader().setReorderingAllowed(false);
						taskTable.getColumnModel().getSelectionModel()
								.setSelectionMode(
										ListSelectionModel.SINGLE_SELECTION);

						ListSelectionModel taskSelectionModel = taskTable
								.getSelectionModel();
						taskSelectionModel
								.setSelectionMode(ListSelectionModel
										.SINGLE_INTERVAL_SELECTION);
						taskSelectionModel
								.addListSelectionListener(new
										ListSelectionListener() {
									public void valueChanged(
											ListSelectionEvent e) {
										currentSelectedTaskTable = taskTable
												.getSelectedRows()[0];
										int a = currentSelectedTaskTable;
										String taskName = (String) taskTable
												.getValueAt(a, 0);
										currentSelectedTask = currentProject
												.findTask(taskName);
										editTaskButton.setEnabled(true);
										deleteTaskButton.setEnabled(true);
									}
								});

						taskTableScrollPane = new JScrollPane(taskTable);
						// update the table
						addComponent(contentPane, taskTableScrollPane, 190,
								110, 510, 240);
					}
				});
		// set the scroll pane (enable scrolling)
		staffTableScrollPane = new JScrollPane(staffTable);
		// since staff was removed, unselect any staff
		editStaffButton.setEnabled(false);
		removeStaffButton.setEnabled(false);
		// if there is no staff left, no tasks can be made
		if (currentProject.getNumberofStaff() == 0) {
			newTaskButton.setEnabled(false);
		}
		// update the staff table
		addComponent(contentPane, staffTableScrollPane, 10, 110, 160, 210);
	}

	/**
	 * Handler for when the new Task button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void newTaskButtonActionPerformed(ActionEvent evt) {
		if (newTaskButton.isEnabled()) {
			new TaskGUI(this);
		}
	}

	/**
	 * Handler for when the delete task button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void deleteTaskButtonActionPerformed(ActionEvent evt) {
		contentPane.remove(taskTableScrollPane);
		// remove the task from the array
		currentProject.removeTask(currentSelectedTask);
		// set the table model data for the table to use
		taskTableModel = new DefaultTableModel();
		taskTableModel.addColumn("Task Name");
		taskTableModel.addColumn("Start Date");
		taskTableModel.addColumn("End Date");
		taskTableModel.addColumn("Description");
		taskTableModel.addColumn("Staff Member");
		taskTableModel.addColumn("Completion");

		int numTask = currentProject.getNumberofTasks();
		for (int i = 0; i < numTask; i++) {
			Task a = currentProject.getCollectionTasks().get(i);
			addTasksToTable(a);
		}

		taskTable = new JTable(taskTableModel) {
			/**
			 * this variable is purely to remove the CheckStyle warning. has no
			 * current influence on the class itself.
			 */
			static final long serialVersionUID = 0L;

			// disable cell edits
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex <= 4) {
					return String.class;
				} else {
					return Boolean.class;
				}
			}
		};
		// set table specificities
		taskTable.setColumnSelectionAllowed(false);
		taskTable.setRowSelectionAllowed(true);
		taskTable.getTableHeader().setReorderingAllowed(false);
		taskTable.getColumnModel().getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel taskSelectionModel = taskTable.getSelectionModel();
		taskSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		taskSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = taskTable.getSelectedRows()[0];

						currentSelectedTask = selectedRow;
						currentSelectedTaskTable = currentSelectedTask;
						editTaskButton.setEnabled(true);
						deleteTaskButton.setEnabled(true);
					}
				});
		// set the scroll pane (allow scrolling)
		taskTableScrollPane = new JScrollPane(taskTable);
		// as task was deleted, deselect all tasks
		editTaskButton.setEnabled(false);
		deleteTaskButton.setEnabled(false);
		// update the table
		addComponent(contentPane, taskTableScrollPane, 190, 110, 510, 240);
	}

	/**
	 * Handler for when the edit task button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void editTaskButtonActionPerformed(ActionEvent evt) {
		if (editTaskButton.isEnabled()) {
			new TaskEditGUI(this, currentProject.getCollectionTasks().get(
					currentSelectedTask));
		}
	}

	/**
	 * Handler for when the edit project name button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void editProjectNameButtonActionPerformed(ActionEvent evt) {
		if (editProjectNameButton.isEnabled()) {
			new ProjectNameGUI(this);
		}
	}

	/**
	 * Handler for when the sort button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void sortButtonActionPerformed(ActionEvent evt) {
		if (sortButton.isEnabled()) {
			new TaskSortGUI(this);
		}
	}

	/**
	 * Public method for showing all the tasks in the task table. Useful for
	 * helping in the sorting methods.
	 */
	public void showAllTask() {
		contentPane.remove(taskTableScrollPane);
		// showing all tasks, deselect current task
		editTaskButton.setEnabled(false);
		deleteTaskButton.setEnabled(false);

		// create table model data for table to use
		taskTableModel = new DefaultTableModel();
		taskTableModel.addColumn("Task Name");
		taskTableModel.addColumn("Start Date");
		taskTableModel.addColumn("End Date");
		taskTableModel.addColumn("Description");
		taskTableModel.addColumn("Staff Member");
		taskTableModel.addColumn("Completion");

		int numTask = currentProject.getNumberofTasks();
		for (int i = 0; i < numTask; i++) {
			Task a = currentProject.getCollectionTasks().get(i);
			addTasksToTable(a);
		}

		taskTable = new JTable(taskTableModel) {
			/**
			 * this variable is purely to remove the CheckStyle warning. has no
			 * current influence on the class itself.
			 */
			static final long serialVersionUID = 0L;

			// disallow cell edits
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex <= 4) {
					return String.class;
				} else {
					return Boolean.class;
				}
			}
		};
		// set table specificities
		taskTable.setColumnSelectionAllowed(false);
		taskTable.setRowSelectionAllowed(true);
		taskTable.getTableHeader().setReorderingAllowed(false);
		taskTable.getColumnModel().getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		// set table selection listener
		ListSelectionModel taskSelectionModel = taskTable.getSelectionModel();
		taskSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		taskSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = taskTable.getSelectedRows()[0];

						currentSelectedTask = selectedRow;
						currentSelectedTaskTable = currentSelectedTask;

						editTaskButton.setEnabled(true);
						deleteTaskButton.setEnabled(true);
					}
				});
		// set the table scroll pane (allow scrolling)
		taskTableScrollPane = new JScrollPane(taskTable);
		// update the table
		addComponent(contentPane, taskTableScrollPane, 190, 110, 510, 240);
	}

	/**
	 * Handler for when the all task button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void allTaskButtonActionPerformed(ActionEvent evt) {
		showAllTask();
	}

	/**
	 * Handler for when the load button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void loadButtonActionPerformed(ActionEvent evt) {
		Project newProject = new Project();
		new ProjectGUI(newProject);
	}

	/**
	 * Handler for when the save button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void saveButtonActionPerformed(ActionEvent evt) {
		// dummy code, save implementation not completed ):
		JOptionPane.showMessageDialog(null, "Save Completed.", "Saving...",
				JOptionPane.DEFAULT_OPTION);
	}

	/**
	 * Handler for when the edit staff button is pressed.
	 *
	 * @param evt
	 *            the mouse click
	 */
	private void editStaffButtonActionPerformed(ActionEvent evt) {
		new StaffEditGUI(this, currentProject.getCollectionStaff().get(
				currentSelectedStaff));
	}

	/**
	 * Add Component Without a Layout Manager (Absolute Positioning). Taken from
	 * lab05 of CITS1220
	 *
	 * @param container
	 *            the panel
	 * @param c
	 *            the component
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
	}

	/**
	 * The main function.
	 *
	 * @param args
	 *            nothing
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		Project a = new Project();
		new ProjectGUI(a);
	}
}
