import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Main {

	private JFrame frmLeapYearChecker;
	private JTextField txtYear;
	private JTextField txtIsItA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmLeapYearChecker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLeapYearChecker = new JFrame();
		frmLeapYearChecker.setLocation(new Point(960, 540));
		frmLeapYearChecker.setTitle("Leap Year Checker");
		frmLeapYearChecker.setResizable(false);
		frmLeapYearChecker.setBounds(100, 100, 441, 288);
		frmLeapYearChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLeapYearChecker.getContentPane().setLayout(new BorderLayout(0, 0));
		frmLeapYearChecker.setLocationRelativeTo(null);
		
		final Background panel = new Background();
		frmLeapYearChecker.getContentPane().add(panel);
		panel.setLayout(null);
		
		Font font = new Font("Arial", Font.PLAIN, 17);
		txtYear = new JTextField();
		txtYear.setForeground(Color.WHITE);
		txtYear.setOpaque(false);
		txtYear.setFont(font.deriveFont(17f));
		txtYear.setHorizontalAlignment(SwingConstants.CENTER);
		txtYear.setText("Enter your year");
		txtYear.setBounds(114, 110, 200, 41);
		panel.add(txtYear);
		txtYear.setColumns(10);
		txtYear.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				JTextField source = (JTextField)e.getComponent();
		        source.setText("");
		        source.removeFocusListener(this);
		    }
		});
		txtYear.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
					(c == KeyEvent.VK_BACK_SPACE) ||
			        (c == KeyEvent.VK_DELETE))) {
			        e.consume();
			    }
			}
		});
		
		txtYear.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateTxtIsItA();
			}
			public void removeUpdate(DocumentEvent e) {
				updateTxtIsItA();
			}
			public void insertUpdate(DocumentEvent e) {
				updateTxtIsItA();
			}

			public void updateTxtIsItA() {
				if (!txtYear.getText().isEmpty() && (txtYear.getText().length() <= 9)) {
					txtIsItA.setText((Operations.checkLeapYear(Integer.parseInt(txtYear.getText()))) ? "Yes" : "No");
				} else if (txtYear.getText().length() > 9) {
					txtIsItA.setText("Overflow");
				} else {
					txtIsItA.setText("Is it a leap year?");
				}
				
			}}
		);
		
		JLabel title_label = new JLabel("Leap Year Checker");
		
		title_label.setFont(font.deriveFont(30f));
		title_label.setForeground(Color.WHITE);
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(0, 0, 435, 132);
		panel.add(title_label);
		
		txtIsItA = new JTextField();
		txtIsItA.setEditable(false);
		txtIsItA.setText("Is it a leap year?");
		txtIsItA.setOpaque(false);
		txtIsItA.setHorizontalAlignment(SwingConstants.CENTER);
		txtIsItA.setForeground(Color.WHITE);
		txtIsItA.setFont(font.deriveFont(17f));
		txtIsItA.setColumns(10);
		txtIsItA.setBounds(114, 162, 200, 41);
		panel.add(txtIsItA);
		
		JLabel creatorName = new JLabel("Created by: Moscosa, Arjhi P.");
		creatorName.setForeground(Color.WHITE);
		creatorName.setHorizontalAlignment(SwingConstants.RIGHT);
		creatorName.setBounds(235, 234, 190, 14);
		panel.add(creatorName);
		frmLeapYearChecker.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel, frmLeapYearChecker.getContentPane(), title_label, txtYear, txtIsItA}));
		panel.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				panel.mouseX = e.getX();
				panel.mouseY = e.getY();
			}
		});
	}
}
