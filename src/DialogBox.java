import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class DialogBox extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		
		JFrame frame = new MainWindow();
		frame.setVisible(true);
		
	}
	
	public DialogBox() {
		
		initComponents();
	
	}

	public void closeSettingsWindow() {
		
		this.dispose();
		
	}
	
	public void acceptSettingsWindow() {
		
		MainWindow.programPath = pathVariable.getText();
		MainWindow.delimiter = delimiterVariable.getText();
		MainWindow.saveSettings();
		this.dispose();
		
	}
	
	private void initComponents() {
		
		pathLabel = new JTextField();
		pathVariable = new JTextField();
		delimiterLabel = new JTextField();
		delimiterVariable = new JTextField();
		acceptKey = new JButton();
		cancelKey = new JButton();

		setBackground(new Color(100, 100, 100));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(100, 100);
		setTitle("Settings Menu");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
				
			"5px, 100px, 5px, 300px, 5px, 100px, 5px",
			"5px, 25px, 5px, 25px, 5px, 25px, 5px"
			
		));
			
		pathLabel.setText("Execution Path");
		pathLabel.setEditable(false);		
		contentPane.add(pathLabel, CC.xywh(2, 2, 1, 1, CC.FILL, CC.FILL));
		
		pathVariable.setText(MainWindow.programPath);		
		contentPane.add(pathVariable, CC.xywh(4, 2, 3, 1, CC.FILL, CC.FILL));
		
		delimiterLabel.setText("Delimiter Value");
		delimiterLabel.setEditable(false);
		contentPane.add(delimiterLabel, CC.xywh(2, 4, 1, 1, CC.FILL, CC.FILL));
		
		delimiterVariable.setText(MainWindow.delimiter);		
		contentPane.add(delimiterVariable, CC.xywh(4, 4, 3, 1, CC.FILL, CC.FILL));
		
		cancelKey.setText("Cancel");
		cancelKey.setFocusable(false);
		cancelKey.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				closeSettingsWindow();
				
			}
		
		});

		contentPane.add(cancelKey, CC.xywh(2, 6, 1, 1, CC.FILL, CC.FILL));

		
		acceptKey.setText("Accept");
		acceptKey.setFocusable(false);
		acceptKey.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				acceptSettingsWindow();
				
			}
		
		});

		contentPane.add(acceptKey, CC.xywh(6, 6, 1, 1, CC.FILL, CC.FILL));

		pack();
		setMinimumSize(this.getSize());
		
	}
	
	JTextField pathLabel;
	JTextField pathVariable;
	JTextField delimiterLabel;
	JTextField delimiterVariable;
	JButton acceptKey;
	JButton cancelKey;
	
}
