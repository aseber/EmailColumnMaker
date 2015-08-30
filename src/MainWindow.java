import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_TYPING_FIELD_TEXT = "Type here";
	private static Typer typer;
	static String programPath = "";
	static String delimiter = "";
	
	public static void main(String[] args) {
		
		try {
			
			DataInputStream input = new DataInputStream(new FileInputStream("settings.dat"));
			programPath = input.readUTF();
			delimiter = input.readUTF();
			input.close();
			System.out.println("Old settings loaded!");
			
		}
		
		catch (IOException e) {
			
			programPath = "C:\\Program Files\\Microsoft Office\\Office14\\excel.exe";
			delimiter = "; ";
			saveSettings();
			System.out.println("Created and saved new settings!");
			
			
		}
		
		JFrame frame = new MainWindow();
		frame.setVisible(true);
		
	}
	
	public MainWindow() {
		
		initComponents();
	
	}
	
	public static void saveSettings() {
		
		try {
			
			DataOutputStream output = new DataOutputStream(new FileOutputStream("settings.dat"));
			output.writeUTF(programPath);
			output.writeUTF(delimiter);
			output.flush();
			output.close();
			System.out.println("Settings Saved!");
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Could not find file to save to!");
		
		} catch (IOException e) {
			
			System.out.println("Failed to save settings!");
			
		}
		
	}
	
	private String ConvertKey(String input) {
		
		String element = "";
		String output = new String("");
		String[] list = input.split(delimiter);
		
		for (int i = 0; i < list.length; i++) {
			
			element = list[i];
			element += "\n";
			output += element;
			System.out.println("Element " + i + ": " + element);
			System.out.println("Output " + i + ": " + output);
			
		}
	
		return output;
		
	}
	
	private void startTyping(String input) {
		
		typer = new Typer();
		TypeKey.setName("Stop Execution");
		typer.startTyper(ConvertKey(input));
		
	}
	
	private void stopTyping() {
		
		TypeKey.setName("Execute");
		typer.stopTyper();
		
	}
	
	private void TypingFieldKeyTyped(KeyEvent e) {
		
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {// The button changes text when it needs to tell error or success, use swing.runlater or something
			// Scrollpane is derpy for the JTextArea
			// Should be opening excel (Add area to look for program) and paste to it)
			System.out.println("osndamdwoaiwd");
			//e.
			
		}
		
	}
	
	private void TypingFieldFocusGained(FocusEvent e) {
		
		TypingField.setForeground(Color.BLACK);
		
		if (TypingField.getText().equals(DEFAULT_TYPING_FIELD_TEXT)) {
		
			TypingField.setText("");
			
		}
	
	}

	private void TypingFieldFocusLost(FocusEvent e) {
	
		if (TypingField.getText().equals("") || TypingField.getText().equals(DEFAULT_TYPING_FIELD_TEXT)) {
			
			TypingField.setForeground(new Color(150,150,150));
			TypingField.setText(DEFAULT_TYPING_FIELD_TEXT);
			
		}
	
	
	}

	private void initComponents() {
		
		//scrollPane1 = new JScrollPane();
		TypingField = new JTextField();
		SettingsKey = new JButton();
		TypeKey = new JButton();

		setBackground(new Color(100, 100, 100));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(100, 100);
		setTitle("Hmm");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
				
			"5px, 100px, 5px, 400px, 5px",
			"5px, 25px, 5px, 25px, 5px"
			
		));
		
		{
			
			TypingField.setText("Type here");
			TypingField.setForeground(new Color(150, 150, 150));
			TypingField.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent e) {}

				@Override
				public void keyReleased(KeyEvent e) {}

				@Override
				public void keyTyped(KeyEvent e) {
					
					TypingFieldKeyTyped(e);
					
				}
				
			});
			TypingField.addFocusListener(new FocusAdapter() {
				
				@Override
				public void focusGained(FocusEvent e) {
				
					TypingFieldFocusGained(e);
			
				}
			
				@Override
				public void focusLost(FocusEvent e) {
				
					TypingFieldFocusLost(e);
				
				}
				
			});
		
		}
		
		contentPane.add(TypingField, CC.xywh(2, 2, 3, 1, CC.FILL, CC.FILL));
		
		SettingsKey.setText("Settings");
		SettingsKey.setFocusable(false);
		SettingsKey.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new DialogBox();
				// Create Dialog
		
			}
		
		});

		contentPane.add(SettingsKey, CC.xywh(2, 4, 1, 1, CC.FILL, CC.FILL));

		
		TypeKey.setText("Execute");
		TypeKey.setFocusable(false);
		TypeKey.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if (TypeKey.getText().equals("Execute")) {
					
					startTyping(TypingField.getText());
					
				}
				
				if (TypeKey.getText().equals("Stop Execution")) {
					
					stopTyping();
					
				}
		
			}
		
		});

		contentPane.add(TypeKey, CC.xywh(4, 4, 1, 1, CC.FILL, CC.FILL));

		pack();
		setMinimumSize(this.getSize());
		//setMaximumSize(new Dimension(this.getHeight(), 700));
		
	}
	
	//private static JScrollPane scrollPane1;
	private static JTextField TypingField;
	private static JButton SettingsKey;
	private static JButton TypeKey;
	
}
