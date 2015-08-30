import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Typer extends Thread implements ClipboardOwner {

	public void run() {
		
		System.out.println("Created new typing thread.");
		
		Runtime runtime = Runtime.getRuntime();
			
		setClipboardContents(output);
		try {
				
			runtime.exec(MainWindow.programPath); // Other program to find prime numbers, other to solve number for its factors
			
		} catch (IOException e) {
				
			System.out.println("Program Path is invalid!!!");
			return;
				
		}
		
		try {
			
			Thread.sleep(1000);
		
		} catch (InterruptedException e) {
			
			return;
			
		}
		
		typeWithControl(KeyEvent.VK_V);
		
		System.out.println("Typer Thread has ended run() method.");
		
	}
	
	public void startTyper(String output) {

		this.output = new String(output);
		this.start();
		
		try {

			typer = new Robot(); 

		} catch (AWTException e) {
			
			System.out.println("Could not create keyboard emulator. Closing Typer Thread.");
			e.printStackTrace();
			
		}
		
	}
	
	public void stopTyper() {
		
		this.interrupt();
		
	}
	
	public void setClipboardContents(String clipboardString) {
	    
		StringSelection stringSelection = new StringSelection(clipboardString);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(stringSelection, this);
	    
	}
	
	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {}
	
	private void typeWithControl(int i) {
		
		typer.keyPress(KeyEvent.VK_CONTROL);
		typer.keyPress(i);
		typer.keyRelease(KeyEvent.VK_CONTROL);
		
	}
	
	private Robot typer;
	private String output;

}
