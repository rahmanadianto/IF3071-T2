package ui;
/**
 * *
 * @author Muhtar Hartopo
 * NIM : 13513068
 *
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;

import java.awt.Color;

import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
public class Layar {

	private JFrame frmTugasAi;
	private JTextField txtA;
	private JRadioButton radioBtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioBtn1;
	private JButton btn1;
	private int option; // 0 classifier, 1 testing
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layar window = new Layar();
					window.frmTugasAi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Layar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTugasAi = new JFrame();
		frmTugasAi.setTitle("Tugas 2 AI");
		frmTugasAi.setBounds(100, 100, 450, 300);
		frmTugasAi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTugasAi.setResizable(false);
		frmTugasAi.getContentPane().setLayout(new BoxLayout(frmTugasAi.getContentPane(), BoxLayout.X_AXIS));
		
		JDesktopPane desktopPane = new JDesktopPane();
		
		desktopPane.setBackground(new Color(255, 255, 255));
		frmTugasAi.getContentPane().add(desktopPane);
		
		txtA = new JTextField();		
		txtA.setBounds(153, 58, 240, 20);
		desktopPane.add(txtA);
		txtA.setColumns(10);
		txtA.setEditable(false);
		
		JButton btn = new JButton("Pilih File\r\n");
		JFileChooser fc;
		fc = new JFileChooser();
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int retVal = fc.showOpenDialog(desktopPane);
				if(retVal == JFileChooser.APPROVE_OPTION) {
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					File workingDirectory = new File(System.getProperty("user.dir"));
					fc.setCurrentDirectory(workingDirectory);
					String path = "";
					path = fc.getSelectedFile().getAbsolutePath();
					txtA.setText(path);
					btn1.setEnabled(radioBtn.isSelected() || radioBtn1.isSelected());
				}
			}
		});
		btn.setBounds(54, 57, 89, 23);
		desktopPane.add(btn);
		
		radioBtn = new JRadioButton("Classifier");
		radioBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(radioBtn.isSelected()) {
					btn1.setEnabled(getPath().compareTo("") != 0);
					option = 0;
				}
			}
		});
		radioBtn.setBackground(new Color(255, 255, 255));
		buttonGroup.add(radioBtn);
		radioBtn.setBounds(164, 128, 109, 23);
		desktopPane.add(radioBtn);
		
		radioBtn1 = new JRadioButton("Testing");
		radioBtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(radioBtn1.isSelected()) {
					btn1.setEnabled(getPath().compareTo("") != 0);
					option = 1;
				}
			}
		});
		radioBtn1.setBackground(new Color(255, 255, 255));
		buttonGroup.add(radioBtn1);
		radioBtn1.setBounds(164, 155, 109, 23);
		desktopPane.add(radioBtn1);
		
		btn1 = new JButton("GO\r\n");
		
		btn1.setBounds(164, 204, 89, 23);
		btn1.setEnabled(false);
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(option == 1) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								TestWindow frame = new TestWindow(getPath());
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		desktopPane.add(btn1);
		
	}
	
	private String getPath() {
		return txtA.getText();
	}

	public int getOption() {
		return option;
	}
}
