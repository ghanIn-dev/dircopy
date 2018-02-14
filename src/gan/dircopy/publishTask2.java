package gan.dircopy;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;

public class publishTask2 extends Thread {

	private String[] SrcAndTarDir;

	public publishTask2(String[] SrcAndTarDir) {
		// TODO Auto-generated constructor stub
		this.SrcAndTarDir = SrcAndTarDir;
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

		int i = 0;
		
		long startTime = System.currentTimeMillis();
		for (i = 0; i < fileList.length; i++) {

			try {
				input = new FileInputStream(fileList[i]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (fileList[i].isFile()) {
				filename[i] = fileList[i].getName();
			}
			try {
				output = new FileOutputStream(SrcAndTarDir[1] + "\\" + filename[i]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		JOptionPane.showMessageDialog(null, "복사가 완료되었습니다. 총 갯수"+(i+1) +" 총 소요시간"+( endTime - startTime )/1000.0f );

		System.out.println("스레드 종료");
		System.out.println("--------");
	}
}
