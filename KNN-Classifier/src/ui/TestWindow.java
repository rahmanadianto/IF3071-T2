package ui;
/**
 * *
 * @author Muhtar Hartopo
 * NIM : 13513068
 *
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSpinner;

import java.awt.Color;

import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;

import classifier.*;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextPane;

public class TestWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JButton btnGo;
	private JRadioButton rbKNN;
	private JRadioButton rbNB;
	private JRadioButton rbFT;
	private JRadioButton rbCV;
	private JSpinner spinner;
	private int skema;
	private int alg;
	private JTextPane textPane;
	private String filePath;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWindow frame = new TestWindow("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestWindow(String filePath) {
		this.filePath = filePath;
		setTitle("Pengujian - Tugas AI 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
		panel.setBounds(2, 0, 122, 462);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		rbKNN = new JRadioButton("K-NN");
		rbKNN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				alg = 0;
				btnGo.setEnabled((rbKNN.isSelected() || rbNB.isSelected()) && (rbFT.isSelected() || rbCV.isSelected()));
			}
		});
		rbKNN.setBounds(6, 70, 59, 23);
		panel.add(rbKNN);
		rbKNN.setBackground(new Color(245, 245, 245));
		buttonGroup_1.add(rbKNN);
		
		rbNB = new JRadioButton("NaiveBayes");
		rbNB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				alg = 1;
				btnGo.setEnabled((rbKNN.isSelected() || rbNB.isSelected()) && (rbFT.isSelected() || rbCV.isSelected()));
			}
		});
		rbNB.setBounds(6, 103, 109, 23);
		panel.add(rbNB);
		rbNB.setBackground(new Color(245, 245, 245));
		buttonGroup_1.add(rbNB);
		
		rbFT = new JRadioButton("Full training");
		rbFT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				skema = 0;
				btnGo.setEnabled((rbKNN.isSelected() || rbNB.isSelected()) && (rbFT.isSelected() || rbCV.isSelected()));
			}
		});
		rbFT.setBounds(7, 217, 109, 23);
		panel.add(rbFT);
		rbFT.setBackground(new Color(245, 245, 245));
		buttonGroup.add(rbFT);
		
		rbCV = new JRadioButton("CrossValidation");
		rbCV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				skema = 1;
				btnGo.setEnabled((rbKNN.isSelected() || rbNB.isSelected()) && (rbFT.isSelected() || rbCV.isSelected()));
			}
		});
		rbCV.setBounds(7, 249, 109, 23);
		panel.add(rbCV);
		rbCV.setBackground(new Color(245, 245, 245));
		buttonGroup.add(rbCV);
		
		btnGo = new JButton("GO");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(alg == 0) {
					if(skema == 0) {
						textPane.setText(modelFTKNN());
					} else {
						textPane.setText(modelCVKNN());
					}
				} else {
					//Naive bayes
				}
			}
		});
		btnGo.setBounds(15, 316, 89, 23);
		panel.add(btnGo);
		btnGo.setEnabled(false);
		
		spinner = new JSpinner();
		spinner.setBounds(71, 72, 38, 20);
		panel.add(spinner);
		spinner.setModel(new SpinnerNumberModel(3, 1, 10, 1));
		spinner.setToolTipText("K value");
		
		JLabel lblAlgoritma = DefaultComponentFactory.getInstance().createTitle("Algoritma");
		lblAlgoritma.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAlgoritma.setBounds(25, 24, 88, 27);
		panel.add(lblAlgoritma);
		
		JLabel lblSkema = DefaultComponentFactory.getInstance().createTitle("Skema");
		lblSkema.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSkema.setBounds(27, 181, 88, 14);
		panel.add(lblSkema);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Courier New", Font.PLAIN, 11));
		textPane.setBounds(135, 0, 355, 462);
		desktopPane.add(textPane);
		
	}
	
	private String modelFTKNN() {
		DataReader dr;
		String S = "";
		try {
			dr = new DataReader(filePath);
			KNNClassifier kn = new KNNClassifier(dr.getData(),(Integer) spinner.getValue()); 
			FullTrainingKNN ftk = new FullTrainingKNN(kn);
			ftk.test();
			S = ftk.getModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return S;
	}
	private String modelCVKNN() {
		DataReader dr;
		String S = "";
		try {
			dr = new DataReader(filePath);
			KNNClassifier kn = new KNNClassifier(dr.getData(),(Integer) spinner.getValue()); 
			CrossValidation cv = new CrossValidation(kn, 10);
			cv.test();
			S = cv.getModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return S;
		
	}
}
