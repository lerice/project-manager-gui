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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Graphical User Interface to choose a sort by method for the task table.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class TaskSortGUI extends JFrame {
	/**
	 * this variable is purely to remove the CheckStyle warning. has no current
	 * influence on the class itself.
	 */
	static final long serialVersionUID = 0L;

	/**
	 * global variables for labels.
	 */
	private JLabel titleLabel;
	/**
	 * global variables for check boxes.
	 */
	private JCheckBox startDateBox, endDateBox, staffNameBox;
	/**
	 * global variables for panels.
	 */
	private JPanel contentPane;
	/**
	 * global variables for buttons.
	 */
	private JButton saveButton, cancelButton;

	/**
	 * global variable for the projectGUI it is using.
	 */
	private ProjectGUI currentProjectGUI;

	/**
	 * Constructor.
	 *
	 * @param projectgui
	 *            the ProjectGUI to be used
	 */
	public TaskSortGUI(ProjectGUI projectgui) {
		// create the JFrame
		super();
		// set the projectGUI to edit
		currentProjectGUI = projectgui;
		// initialise the components
		initializeComponent();
		// reveal the JFrame
		this.setVisible(true);
	}

	/**
	 * Initialise the components.
	 */
	private void initializeComponent() {
		// create labels
		titleLabel = new JLabel();
		titleLabel.setText("Sort Tasks By:");

		// create check boxes
		startDateBox = new JCheckBox();
		startDateBox.setText(" Start Date");
		startDateBox.setBackground(new Color(208, 212, 224));

		endDateBox = new JCheckBox();
		endDateBox.setText(" End Date");
		endDateBox.setBackground(new Color(208, 212, 224));

		staffNameBox = new JCheckBox();
		staffNameBox.setText(" Staff Name");
		staffNameBox.setBackground(new Color(208, 212, 224));

		// add mouse listeners
		startDateBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				startDateBoxMouseClicked(e);
			}

		});

		endDateBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				endDateBoxMouseClicked(e);
			}

		});

		staffNameBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				staffNameBoxMouseClicked(e);
			}

		});

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
		// add components
		addComponent(contentPane, titleLabel, 70, 10, 150, 25);

		addComponent(contentPane, startDateBox, 65, 40, 120, 25);
		addComponent(contentPane, endDateBox, 65, 65, 120, 25);
		addComponent(contentPane, staffNameBox, 65, 90, 120, 25);

		addComponent(contentPane, cancelButton, 10, 130, 80, 25);
		addComponent(contentPane, saveButton, 130, 130, 80, 25);

		// set the JFrame
		this.setTitle("Sort Tasks");
		this.setLocation(new Point(200, 200));
		this.setSize(new Dimension(233, 200));
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
			// check the three possible box's to be clicked
			if (startDateBox.isSelected()) {
				// sort accordingly
				currentProjectGUI.getProject().sortByStartDate();
				// update the table
				currentProjectGUI.showAllTask();
				this.dispose();
			} else if (endDateBox.isSelected()) {
				// sort accordingly
				currentProjectGUI.getProject().sortByEndDate();
				// update the table
				currentProjectGUI.showAllTask();
				this.dispose();
			} else if (staffNameBox.isSelected()) {
				// sort accordingly
				currentProjectGUI.getProject().sortByStaffAssign();
				// update the table
				currentProjectGUI.showAllTask();
				// dispose this JFrame
				this.dispose();
			} else {
				// if no box was clicked
				JOptionPane.showMessageDialog(null,
						"Please select a sort by method.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Handler method for when the start date box is clicked.
	 *
	 * @param e
	 *            the mouseEvent
	 */
	public void startDateBoxMouseClicked(MouseEvent e) {
		// only 1 box can be selected at a time
		if (startDateBox.isSelected()) {
			endDateBox.setSelected(false);
			staffNameBox.setSelected(false);
		}
	}

	/**
	 * Handler method for when the end date box is clicked.
	 *
	 * @param e
	 *            the mouseEvent
	 */
	public void endDateBoxMouseClicked(MouseEvent e) {
		// only 1 box can be selected at a time
		if (endDateBox.isSelected()) {
			startDateBox.setSelected(false);
			staffNameBox.setSelected(false);
		}
	}

	/**
	 * Handler method for when the staff name box is clicked.
	 *
	 * @param e
	 *            the mouseEvent
	 */
	public void staffNameBoxMouseClicked(MouseEvent e) {
		// only 1 box can be selected at a time
		if (staffNameBox.isSelected()) {
			endDateBox.setSelected(false);
			startDateBox.setSelected(false);
		}
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
