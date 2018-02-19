package gan.dircopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class publishTask2 extends Thread {

	private String[] SrcAndTarDir;
	private JProgressBar progressBar;
	private JProgressBar progressBar_all;
	private JEditorPane editorPane;
	private JButton btnStart;
	private JButton btnStop;
	
	
	public publishTask2(String[] SrcAndTarDir, JProgressBar progressBar, JProgressBar progressBar_all, JEditorPane editorPane, JButton btnStart, JButton btnStop) {
		// TODO Auto-generated constructor stub
		this.SrcAndTarDir = SrcAndTarDir;
		this.progressBar = progressBar;
		this.progressBar_all = progressBar_all;
		this.editorPane = editorPane;
		this.btnStart = btnStart;
		this.btnStop = btnStop;
	}
	
	@Override
	public void run() {

		DirCopy copy = new DirCopy();
		System.out.println("������ ����");
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
			
			editorPane.setText(fileList[i].getName()+" �� " +SrcAndTarDir[1]+"\\"+fileList[i].getName() +" �� ����Ǿ����ϴ�. "
					+ "���� �� "+fileList.length+"���� ���� �� "+(i+1)+"�� ����Ǿ����ϴ�.");
			
			
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
		JOptionPane.showMessageDialog(null,
				"���簡 �Ϸ�Ǿ����ϴ�. �� ����" + (i + 1) + " �� �ҿ�ð�" + (endTime - startTime) / 1000.0f);
		
		btnStart.setEnabled(true);
		btnStop.setVisible(false);
		
		System.out.println("������ ����");
		System.out.println("--------");
		
	}
}
