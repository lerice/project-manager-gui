import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Graphical User Interface to implement a new Task.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class TaskGUI extends JFrame {
	/**
	 * this variable is purely to remove the CheckStyle warning. has no current
	 * influence on the class itself.
	 */
	static final long serialVersionUID = 0L;

	/**
	 * global variables for labels.
	 */
	private JLabel nameLabel, startDateLabel, endDateLabel, descriptionLabel,
			staffAssignLabel;
	/**
	 * global variables for text fields.
	 */
	private JTextField nameText, startDateText, endDateText;
	/**
	 * global variable for text areas.
	 */
	private JTextArea descriptionText;
	/**
	 * global variable for combo boxes.
	 */
	private JComboBox staffAssignComboBox;
	/**
	 * global variables for check boxes.
	 */
	private JCheckBox completedButton;
	/**
	 * global variables for panels.
	 */
	private JPanel contentPane;
	/**
	 * global variables for buttons.
	 */
	private JButton saveButton, cancelButton;
	/**
	 * global variables for the error message.
	 */
	private String errorMessage;

	/**
	 * global variable for the projectGUI it is using.
	 */
	private ProjectGUI currentProjectGUI;

	/**
	 * Constructor.
	 *
	 * @param projectgui
	 *            the projectGUI it is using
	 */
	public TaskGUI(ProjectGUI projectgui) {
		// create the JFrame
		super();
		// set the projectGUI to edit
		currentProjectGUI = projectgui;
		// initialise all components
		initializeComponent();
		// reveal the JFrame
		this.setVisible(true);
	}

	/**
	 * Initialise the components.
	 */
	private void initializeComponent() {
		// create labels
		nameLabel = new JLabel();
		startDateLabel = new JLabel();
		endDateLabel = new JLabel();
		descriptionLabel = new JLabel();
		staffAssignLabel = new JLabel();

		// create check box
		completedButton = new JCheckBox();
		completedButton.setText(" Task Completed?");
		completedButton.setEnabled(false);
		completedButton.setBackground(new Color(208, 212, 224));

		// set label text
		nameLabel.setText("Task Name:");
		startDateLabel.setText("Start Date:");
		endDateLabel.setText("End Date:");
		descriptionLabel.setText("Description:");
		staffAssignLabel.setText("Assigned Employee:");

		// create text fields
		nameText = new JTextField();
		startDateText = new JTextField();
		startDateText.setText("ddmmyyyy");
		endDateText = new JTextField();
		endDateText.setText("ddmmyyyy");

		// create text areas
		descriptionText = new JTextArea();
		descriptionText.setLineWrap(true);

		// create the combo box
		staffAssignComboBox = new JComboBox();
		int length = currentProjectGUI.getProject().getNumberofStaff();
		// iterate through the staff array and add to the combo box
		for (int i = 0; i < length; i++) {
			staffAssignComboBox.addItem(currentProjectGUI.getProject()
					.getCollectionStaff().get(i).getStaffName());
		}

		// set buttons
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(true);
		saveButton = new JButton("Save");
		saveButton.setEnabled(true);

		// set action listeners
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cancelButtonMouseClicked(e);
			}
		});

		saveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				saveButtonMouseClicked(e);
			}
		});

		// set the panel
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(208, 212, 224));
		addComponent(contentPane, nameLabel, 10, 10, 120, 25);
		addComponent(contentPane, startDateLabel, 10, 40, 120, 25);
		addComponent(contentPane, endDateLabel, 10, 70, 120, 25);
		addComponent(contentPane, descriptionLabel, 10, 115, 120, 25);
		addComponent(contentPane, staffAssignLabel, 10, 160, 120, 25);

		addComponent(contentPane, nameText, 140, 10, 120, 25);
		addComponent(contentPane, startDateText, 140, 40, 120, 25);
		addComponent(contentPane, endDateText, 140, 70, 120, 25);

		addComponent(contentPane, descriptionText, 140, 100, 120, 55);

		addComponent(contentPane, staffAssignComboBox, 140, 160, 120, 25);

		addComponent(contentPane, completedButton, 75, 190, 150, 25);

		addComponent(contentPane, cancelButton, 10, 220, 80, 25);
		addComponent(contentPane, saveButton, 180, 220, 80, 25);

		// set the JFrame
		this.setTitle("New Task");
		this.setLocation(new Point(200, 150));
		this.setSize(new Dimension(285, 290));
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Handler method for when the cancel button is clicked.
	 *
	 * @param e
	 *            the mouseEvent
	 */
	public void cancelButtonMouseClicked(MouseEvent e) {
		if (cancelButton.isEnabled()) {
			this.dispose();
		}
	}

	/**
	 * Handler method for when the save button is clicked.
	 *
	 * @param e
	 *            the mouseEvent
	 */
	public void saveButtonMouseClicked(MouseEvent e) {
		if (saveButton.isEnabled()) {
			if (checkEmptyDetails()) {
				try {
					// convert the text field input into an string
					String name = nameText.getText();
					// convert the text field input into an int
					int startDate = java.lang.Integer.parseInt(startDateText
							.getText());
					// convert the text field input into an int
					int endDate = java.lang.Integer.parseInt(endDateText
							.getText());
					String description = descriptionText.getText();
					// find the staff via the combobox
					Staff c = currentProjectGUI.getProject()
							.getCollectionStaff().get(
									currentProjectGUI.getProject().findStaff(
											staffAssignComboBox
													.getSelectedItem()
													.toString()));
					// create the new task (try to)
					Task a = new Task(name, startDate, endDate, description, c);
					// add the task into the array
					currentProjectGUI.getProject().addTask(a);
					// update the table, indicating if the task is overdue
					if (a.getEndDateDate().getYYYYMMDDDate() < currentProjectGUI
							.getCurrentDate().getYYYYMMDDDate()) {
						currentProjectGUI.getTaskTableModel().addRow(
								new Object[] { name, a.getStartDate(),
										a.getEndDate() + "*", description,
										c.getStaffName(), false });
					} else {
						currentProjectGUI.getTaskTableModel().addRow(
								new Object[] { name, a.getStartDate(),
										a.getEndDate(), description,
										c.getStaffName(), false });
					}
					// dispose this JFrame
					this.dispose();
				} catch (IllegalArgumentException f) {
					// if creation of the task is illegal, show error
					JOptionPane.showMessageDialog(null, f.getMessage(),
							"Error Message", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// if details are empty, show error
				JOptionPane.showMessageDialog(null, errorMessage,
						"Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Helper method to do checks on the input details.
	 *
	 * @return true if all checks passed
	 */
	private boolean checkEmptyDetails() {
		boolean error = true;
		// update an error message regarding empty fields
		errorMessage = "Fill in these fields to continue OR enter a valid\n";
		if (nameText.getText().equals("")) {
			errorMessage += "- Name\n";
			error = false;
		}
		if (!isIntNumber(startDateText.getText())) {
			errorMessage += "- Start Date\n";
			error = false;
		} else {
			int startDate = java.lang.Integer.parseInt(startDateText.getText());
			if (startDate < 1000000 || startDate > 99999999) {
				errorMessage += "- Start Date\n";
				error = false;
			}
		}
		if (!isIntNumber(endDateText.getText())) {
			errorMessage += "- End Date\n";
			error = false;
		} else {
			int endDate = java.lang.Integer.parseInt(endDateText.getText());
			if (endDate < 1000000 || endDate > 99999999) {
				errorMessage += "- End Date\n";
				error = false;
			}
		}
		if (descriptionText.getText().equals("")) {
			errorMessage += "- Description\n";
			error = false;
		}
		return error;
	}

	/**
	 * Helper method to determine if a string is a valid int.
	 *
	 * @param num
	 *            the string
	 * @return true if it is a valid int
	 */
	public boolean isIntNumber(String num) {
		try {
			Long.parseLong(num);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
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
}
