import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Graphical User Interface to edit the Project Name.
 *
 * @author Eric (Jun) Tan 20925931
 * @version 27/10/2011
 */
public class ProjectNameGUI extends JFrame {
	/**
	 * this variable is purely to remove the CheckStyle warning. has no current
	 * influence on the class itself.
	 */
	static final long serialVersionUID = 0L;
	/**
	 * global variables for labels.
	 */
	private JLabel nameLabel;
	/**
	 * global variables for text fields.
	 */
	private JTextField nameText;
	/**
	 * global variables for panels.
	 */
	private JPanel contentPane;
	/**
	 * global variables for buttons.
	 */
	private JButton saveButton, cancelButton;
	/**
	 * global variable for error message.
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
	 *            the ProjectGUI to be used
	 */
	public ProjectNameGUI(ProjectGUI projectgui) {
		// create the JFrame
		super();
		// set the current projectGUI it is working on
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
		// create the label
		nameLabel = new JLabel();
		nameLabel.setText("Name:");

		// create the text field
		nameText = new JTextField();

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
		addComponent(contentPane, nameLabel, 20, 10, 70, 25);
		addComponent(contentPane, nameText, 90, 10, 120, 25);

		addComponent(contentPane, cancelButton, 10, 60, 80, 25);
		addComponent(contentPane, saveButton, 130, 60, 80, 25);

		// set the JFrame
		this.setTitle("Edit Project Name");
		this.setLocation(new Point(200, 200));
		this.setSize(new Dimension(233, 130));
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
			// check the details are not empty
			if (checkEmptyDetails()) {
				// update the project name and JFrame
				currentProjectGUI.getProject().setProjectName(
						nameText.getText());
				currentProjectGUI.setProjectNameLabel(nameText.getText());
				// dispose this JFrame
				this.dispose();
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
		errorMessage = "Fill in these fields to continue OR enter a valid\n";
		if (nameText.getText().equals("")) {
			errorMessage += "- Project Name\n";
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
