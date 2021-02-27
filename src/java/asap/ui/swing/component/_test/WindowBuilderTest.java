package asap.ui.swing.component._test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import asap.ui.swing.component.EPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class WindowBuilderTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilderTest window = new WindowBuilderTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowBuilderTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		EPanel panel_1 = new EPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
	}

}
