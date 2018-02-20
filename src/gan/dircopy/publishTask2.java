package gan.dircopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class publishTask2 extends Thread {

	private String[] SrcAndTarDir;
	private JProgressBar progressBar;
	private JProgressBar progressBar_all;
	private JEditorPane taskOutput;
	private JButton btnStart;
	private JButton btnStop;
	private JTextArea textArea;
	
	
	public publishTask2(String[] SrcAndTarDir, JProgressBar progressBar, JProgressBar progressBar_all, JEditorPane editorPane, JButton btnStart, JButton btnStop, JTextArea textArea) {
		// TODO Auto-generated constructor stub
		this.SrcAndTarDir = SrcAndTarDir;
		this.progressBar = progressBar;
		this.progressBar_all = progressBar_all;
		this.taskOutput = editorPane;
		this.btnStart = btnStart;
		this.btnStop = btnStop;
		this.textArea=textArea;
	}
	
	@Override
	public void run() {

		DirCopy copy = new DirCopy();
		System.out.println("스레드 실행");
		String SrcDirName = SrcAndTarDir[0].replace(copy.getDirLocation(), "");
		// TODO Auto-generated method stub
		FileInputStream input = null;
		FileOutputStream output = null;
		

		File[] fileList = copy.getSrcFileList(SrcDirName);
		String[] filename = new String[fileList.length];
		progressBar_all.setMaximum(fileList.length);
		int i = 0;

		long startTime = System.currentTimeMillis();
		for (i = 0; i < fileList.length; i++) {
			progressBar.setValue(0);
			try {
				input = new FileInputStream(fileList[i]);

				if (fileList[i].isFile()) {
					filename[i] = fileList[i].getName();
				}

				output = new FileOutputStream(SrcAndTarDir[1] + "\\" + filename[i]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			taskOutput.setText(fileList[i].getName()+"=>" +SrcAndTarDir[1]+"\\"+fileList[i].getName() 
					+ "현재 총 "+fileList.length+"개의 파일 중 "+(i+1)+"개 복사되었습니다.");
			
//			textArea.append(fileList[i].getName()+"=>" +SrcAndTarDir[1]+"\\"+fileList[i].getName() 
//			+ "현재 총 "+fileList.length+"개의 파일 중 "+(i+1)+"개 복사되었습니다.");
			
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
		
		System.out.println("스레드 종료");
		System.out.println("--------");
		
		JOptionPane.showMessageDialog(null,"복사가 완료되었습니다. 총 갯수" + (i + 1) + " 총 소요시간" + (endTime - startTime) / 1000.0f);
	}
}
