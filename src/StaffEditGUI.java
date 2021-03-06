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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Graphical User Interface to edit a Staff Member.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class StaffEditGUI extends JFrame {
	/**
	 * this variable is purely to remove the CheckStyle warning. has no current
	 * influence on the class itself.
	 */
	static final long serialVersionUID = 0L;

	/**
	 * global variables for labels.
	 */
	private JLabel numberLabel, nameLabel, roleLabel;
	/**
	 * global variables for text fields.
	 */
	private JTextField numberText, nameText, roleText;
	/**
	 * global variables for check boxes.
	 */
	private JCheckBox makeManagerButton;
	/**
	 * global variables for panels.
	 */
	private JPanel contentPane;
	/**
	 * global variables for buttons.
	 */
	private JButton saveButton, cancelButton;
	/**
	 * global variable for the error message.
	 */
	private String errorMessage;

	/**
	 * global variable for the projectGUI it is using.
	 */
	private ProjectGUI currentProjectGUI;
	/**
	 * global variable for the current staff it is editing.
	 */
	private Staff currentStaff;

	/**
	 * Constructor.
	 *
	 * @param projectgui
	 *            the ProjectGUI to be used
	 * @param staff
	 *            the staff member to edit
	 */
	public StaffEditGUI(ProjectGUI projectgui, Staff staff) {
		// set the JFrame
		super();
		// set the projectGUI it is editing
		currentProjectGUI = projectgui;
		try {
			// attempt to set the parameter staff in the global variable
			currentStaff = staff;
			// initialise components
			initializeComponent();
			// reveal the JFrame
			this.setVisible(true);
		} catch (IllegalArgumentException e) {
			// if the staff could not be set, throw exception
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initialise the components.
	 */
	private void initializeComponent() {
		// make labels
		numberLabel = new JLabel();
		nameLabel = new JLabel();
		roleLabel = new JLabel();

		// make check boxes
		makeManagerButton = new JCheckBox();
		makeManagerButton.setText(" Make Manager?");
		makeManagerButton.setBackground(new Color(208, 212, 224));

		// if the current staff is the manager, or there is only 1 staff
		// member, lock the check box as selected
		Staff cPM = currentProjectGUI.getProject().getProjectManager();
		if (cPM == currentStaff
				|| currentProjectGUI.getProject().getNumberofStaff() == 1) {
			makeManagerButton.setEnabled(false);
			makeManagerButton.setSelected(true);
		} else {
			// otherwise do normal
			makeManagerButton.setEnabled(true);
		}

		// set the label text
		numberLabel.setText("Employee Number:");
		nameLabel.setText("Name:");
		roleLabel.setText("Role:");

		// set the text fields as original staff values
		numberText = new JTextField();
		numberText.setText("" + currentStaff.getEmployeeNumber());
		nameText = new JTextField();
		nameText.setText(currentStaff.getStaffName());
		roleText = new JTextField();
		roleText.setText(currentStaff.getStaffRole());

		// create buttons
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(true);
		saveButton = new JButton("Save");
		saveButton.setEnabled(true);

		// add action listeners
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
		// add components
		addComponent(contentPane, numberLabel, 10, 10, 120, 25);
		addComponent(contentPane, nameLabel, 10, 40, 120, 25);
		addComponent(contentPane, roleLabel, 10, 70, 120, 25);

		addComponent(contentPane, numberText, 140, 10, 120, 25);
		addComponent(contentPane, nameText, 140, 40, 120, 25);
		addComponent(contentPane, roleText, 140, 70, 120, 25);

		addComponent(contentPane, makeManagerButton, 80, 105, 150, 25);

		addComponent(contentPane, cancelButton, 10, 150, 80, 25);
		addComponent(contentPane, saveButton, 180, 150, 80, 25);

		// set the JFrame
		this.setTitle("Edit Staff - " + currentStaff.getStaffName());
		this.setLocation(new Point(200, 200));
		this.setSize(new Dimension(285, 220));
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
	 * Helper method to split the saveButtonMouseClicked method, to avoid having
	 * too many nested if statements.
	 */
	private void helpSplitMethod() {
		// convert the text field input into an int
		int employeeNumber = java.lang.Integer.parseInt(numberText.getText());
		boolean found = false;
		// call the method for array size once (efficient code)
		int length = currentProjectGUI.getProject().getNumberofStaff();
		// iterate through the staff array
		for (int i = 0; i < length; i++) {
			if (employeeNumber == currentProjectGUI.getProject()
					.getCollectionStaff().get(i).getEmployeeNumber()
					&& employeeNumber != currentStaff.getEmployeeNumber()) {
				// if the employee number is taken, 'found' set to true
				found = true;
			}
		}
		if (!found) {
			// if number is not taken, set the values in the staff
			String name = nameText.getText();
			String role = roleText.getText();
			currentStaff.setEmployeeNumber(employeeNumber);
			currentStaff.setStaffName(name);
			currentStaff.setStaffRole(role);
			// update the manager if selected
			if (makeManagerButton.isSelected()) {
				currentProjectGUI.setManagerNameLabel(currentStaff
						.getStaffName());
				currentProjectGUI.getProject().setProjectManager(currentStaff);
			}
			// update the staff table
			currentProjectGUI.getStaffTableModel().setValueAt(name,
					currentProjectGUI.getCurrentSelectedStaff(), 0);
			// dispose this JFrame
			this.dispose();
		} else {
			// if the number is found, show error
			JOptionPane.showMessageDialog(null,
					"That employee number is taken.", "Error Message",
					JOptionPane.ERROR_MESSAGE);
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
			// check the details are not empty (initial check)
			if (checkEmptyDetails()) {
				// if the employee number is not an int, show error
				if (!isIntNumber(numberText.getText())) {
					JOptionPane.showMessageDialog(null,
							"Enter a valid Employee Number", "Error Message",
							JOptionPane.ERROR_MESSAGE);
					numberText.setText("0001");
				} else {
					// else continue
					helpSplitMethod();
				}
			} else {
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
		if (numberText.getText().equals("")) {
			errorMessage += "- Employee Number\n";
			error = false;
		}
		if (nameText.getText().equals("")
				|| !nameText.getText().matches("[A-Z][a-z-]*$")) {
			errorMessage += "- Name\n";
			error = false;
		}
		if (roleText.getText().equals("")) {
			errorMessage += "- Role\n";
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
