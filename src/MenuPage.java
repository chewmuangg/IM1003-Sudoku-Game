import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class MenuPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPage frame = new MenuPage();
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
	public MenuPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 40, 938, 620);
		setTitle("Sudoku - Home");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 192, 203));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Simple");
		btnNewButton.setBounds(270, 200, 335, 95);
		btnNewButton.setBackground(new Color(253, 245, 230));
		btnNewButton.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 40));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Simple();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Medium");
		btnNewButton_1.setBounds(270, 320, 335, 95);
		btnNewButton_1.setBackground(new Color(253, 245, 230));
		btnNewButton_1.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 40));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Medium();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Hard");
		btnNewButton_2.setBounds(270, 440, 335, 95);
		btnNewButton_2.setBackground(new Color(253, 245, 230));
		btnNewButton_2.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 40));
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Difficult();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Welcome to the Game of Sudoku! ");
		lblNewLabel.setBounds(72, 36, 797, 71);
		lblNewLabel.setFont(new Font("Gill Sans Ultra Bold", Font.ITALIC, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Choose your difficulty level:");
		lblNewLabel_1.setBounds(163, 118, 557, 35);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Gill Sans Ultra Bold", Font.ITALIC, 30));
		panel.setLayout(null);
		panel.add(btnNewButton_2);
		panel.add(btnNewButton_1);
		panel.add(btnNewButton);
		panel.add(lblNewLabel);
		panel.add(lblNewLabel_1);
	}
}
