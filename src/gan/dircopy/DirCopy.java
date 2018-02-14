package gan.dircopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class DirCopy {
	
	public static void main(String[] args) throws FileNotFoundException {
        String SrcDir;
        String TarDir;
        Scanner src = new Scanner(System.in);
        Scanner tar = new Scanner(System.in);
        
        // �̸� �Է�
        System.out.println("�ҽ������� Ÿ�������� �̸��� �Է��ϼ���");
        SrcDir = src.nextLine();
        TarDir = tar.nextLine();
        
        DirCopy copy = new DirCopy();
        
        //�� ��� �ޱ�
        String SrcAndTarDir[] = copy.getSrcAndTarDir(SrcDir, TarDir);
        
        
        //���� ����
        doCopy( copy,SrcDir,SrcAndTarDir); 
             
       
        
           

	}

	public static void doCopy(DirCopy copy, String SrcDirName ,String[] SrcAndTarDir) throws FileNotFoundException {
		FileInputStream input = null;
        FileOutputStream output = null;
        

        File[] fileList=copy.getSrcFileList(SrcDirName);
        String []filename = new String[fileList.length];
        
        long startTime = System.currentTimeMillis();
        for(int i=0; i<fileList.length; i++) {
 			input = new FileInputStream(fileList[i]);
			  if(fileList[i].isFile()) {
				  filename[i]=fileList[i].getName();
			  }		      	
        	output = new FileOutputStream(SrcAndTarDir[1]+"\\"+filename[i]);
        	
        	System.out.println(SrcAndTarDir[0]+"\\"+fileList[i].getName()+" �� " +SrcAndTarDir[1]+"\\"+fileList[i].getName() +" �� ����Ǿ����ϴ�.");
        	System.out.println("�� "+fileList.length+"���� ���� �� "+(i+1)+"�� ����Ǿ����ϴ�." +  copy.getPercent((double)(i+1), (double)fileList.length) +"%");
         }
        long endTime = System.currentTimeMillis();
        System.out.println("##  �� �ҿ�ð�(��.0f) : " + ( endTime - startTime )/1000.0f +"��");
	}
	
	
	
	
	public double getPercent(double num1,  double num2) {
		
		double per = Double.parseDouble(String.format("%.2f", num1/num2 * 100.0));
		
		return per;
		
	}

	public String getDirLocation() {
		// TODO Auto-generated method stub
		String SourceDirLocation = System.getProperty("user.dir");

		return SourceDirLocation;
	}

	public String[] getSrcAndTarDir(String src, String tar) {
		// TODO Auto-generated method stub
		String currentPath = getDirLocation();

		String SrcAndTarDir[] = { currentPath + "\\" + src, currentPath + "\\" + tar };
		return SrcAndTarDir;
	}

	public File[] getSrcFileList(String src) {
		// TODO Auto-generated method stub
				String path=getDirLocation()+"\\"+src;
				File dirFile=new File(path);
				File []fileList=dirFile.listFiles();
				
				for(int i = 0 ; i<fileList.length; i++) {
					File file = fileList[i];
				}
		
		return fileList;
	}
	
	public File getSrcFile(String src) {
		// TODO Auto-generated method stub
				String path=getDirLocation()+"\\"+src;
				File dirFile=new File(path);
				
		
		return dirFile;
	}
	
}
