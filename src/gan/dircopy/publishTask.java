package gan.dircopy;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;

public class publishTask extends Thread{
	private String filename;

	public publishTask(String filename) {
		// TODO Auto-generated constructor stub
		this.filename = filename;
	}

	@Override
	public void run() {
		
		System.out.println("스레드 실행");

		// TODO Auto-generated method stub
		FileInputStream input = null;
        FileOutputStream output = null;
       
		try {
			output = new FileOutputStream(filename);
			System.out.println(filename);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("스레드 종료");
		System.out.println("--------");
	}
}
