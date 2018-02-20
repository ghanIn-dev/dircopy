package gan.dircopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class publishTask2 extends Thread {

	private File source;
	private File target;
	private JProgressBar progressBar;
	private JProgressBar progressBar_all;
	private JEditorPane taskOutput;
	private JButton btnStart;
	private JButton btnStop;
	private JTextArea textArea;
	private DefaultTableModel m;
	
	public publishTask2(File source, File target, JProgressBar progressBar, JProgressBar progressBar_all, JEditorPane editorPane, JButton btnStart, JButton btnStop, JTextArea textArea, DefaultTableModel m) {
		// TODO Auto-generated constructor stub
		this.source=source;
		this.target=target;
		this.progressBar = progressBar;
		this.progressBar_all = progressBar_all;
		this.taskOutput = editorPane;
		this.btnStart = btnStart;
		this.btnStop = btnStop;
		this.textArea=textArea;
		this.m=m;
	}
	
	@Override
	public void run() {

		DirCopy copy = new DirCopy();
		System.out.println("������ ����");
		
		// TODO Auto-generated method stub
		FileInputStream input = null;
		FileOutputStream output = null;
		
		File[] fileList = copy.getSrcFileList(source);
		
		String[] filename = new String[fileList.length];
		progressBar_all.setMaximum(fileList.length);
		int i = 0;

		long startTime = System.currentTimeMillis();
		for (i = 0; i < fileList.length; i++) {
			progressBar.setValue(0);
			try {
	 			 if(fileList[i].isDirectory()) {
	 				 source=fileList[i];
	 				 File newTarFolder=copy.getSrcFile(target.getPath()+"\\"+fileList[i].getName());
	 				 filename[i]=fileList[i].getName();
	 				 DirCopy.copyDirectory(source, newTarFolder);
				  }
	 			 else if(fileList[i].isFile()){
					filename[i]=fileList[i].getName();
		 			input = new FileInputStream(fileList[i]);
		        	output = new FileOutputStream(target+"\\"+filename[i]);
		        	System.out.println(filename[i] + "����"+target+"\\"+filename[i]+"���� ����Ǿ���.");
	 			 }


			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			taskOutput.setText(fileList[i].getName()+"=>" +target+"\\"+fileList[i].getName() 
					+ "���� �� "+fileList.length+"���� ���� �� "+(i+1)+"�� ����Ǿ����ϴ�.");
			
//			textArea.append(fileList[i].getName()+"=>" +SrcAndTarDir[1]+"\\"+fileList[i].getName() 
//			+ "���� �� "+fileList.length+"���� ���� �� "+(i+1)+"�� ����Ǿ����ϴ�.");
			
			progressBar.setMaximum(i);
			progressBar.setValue(i);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			progressBar_all.setValue(i+1);
			
		}
		long endTime = System.currentTimeMillis();
		
		
		btnStart.setEnabled(true);
		btnStop.setVisible(false);
		
		System.out.println("������ ����");
		System.out.println("--------");
		
		JOptionPane.showMessageDialog(null,"���簡 �Ϸ�Ǿ����ϴ�. �� ����" + (i + 1) + " �� �ҿ�ð�" + (endTime - startTime) / 1000.0f);
	}
}
