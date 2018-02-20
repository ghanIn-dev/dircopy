package gan.dircopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class DirCopy {
	
	public static void main(String[] args) throws FileNotFoundException {
        String SrcDir;
        String TarDir;
        Scanner src = new Scanner(System.in);
        Scanner tar = new Scanner(System.in);
        
        // 이름 입력
        System.out.println("소스폴더와 타켓폴더의 이름을 입력하세요");
        SrcDir = src.nextLine();
        TarDir = tar.nextLine();
        
        DirCopy copy = new DirCopy();
        
        //두 경로 받기
        File srcdir=copy.getSrcFile(SrcDir);
        File tardir=copy.getSrcFile(TarDir);
        
        //파일 복사
        try {
			doCopy(copy,srcdir,tardir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void doCopy(DirCopy copy, File source, File target) throws IOException {
		FileInputStream input = null;
        FileOutputStream output = null;
              
        File[] fileList=copy.getSrcFileList(source);
        String []filename = new String[fileList.length];
        
        long startTime = System.currentTimeMillis();
        for(int i=0; i<fileList.length; i++) {
 		
 			 if(fileList[i].isDirectory()) { 				 
 				 source=fileList[i];
 				 target=copy.getSrcFile(target.getPath()+"\\"+fileList[i].getName());
 				 filename[i]=fileList[i].getName();
 				 copyDirectory(source, target);
			  }
 			 else if(fileList[i].isFile()){
				filename[i]=fileList[i].getName();
	 			input = new FileInputStream(fileList[i]);
	        	output = new FileOutputStream(target+"\\"+filename[i]);
 			 }
 			System.out.println(fileList[i] + "에서"+target+"\\"+filename[i]+"으로 복사되었다.");
        	System.out.println("총 "+fileList.length+"개의 파일 중 "+(i+1)+"개 복사되었습니다." +  copy.getPercent((double)(i+1), (double)fileList.length) +"%");
         }
        long endTime = System.currentTimeMillis();
        System.out.println("##  총 소요시간(초.0f) : " + ( endTime - startTime )/1000.0f +"초");
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

	public File[] getSrcFileList(File source) {
		// TODO Auto-generated method stub
				
				File []fileList=source.listFiles();
	
		return fileList;
	}
	

	public File getSrcFile(String path) {
		// TODO Auto-generated method stub
				File dirFile=new File(path);
		return dirFile;
	}
	
	public static void copy(File sourceLocation, File targetLocation) throws IOException {
	    if (sourceLocation.isDirectory()) {
	        copyDirectory(sourceLocation, targetLocation);
	    } else {
	        copyFile(sourceLocation, targetLocation);
	    }
	}

	static void copyDirectory(File source, File target) throws IOException {
	    if (!target.exists()) {
	        target.mkdir();
	    }

	    for (String f : source.list()) {
	        copy(new File(source, f), new File(target, f));
	    }
	}

	private static void copyFile(File source, File target) throws IOException {        
	    try (
	            InputStream in = new FileInputStream(source);
	            OutputStream out = new FileOutputStream(target)
	            
	    ) {
	    	System.out.println(source + "에서"+target+"으로 복사되었다.");
	        byte[] buf = new byte[1024];
	        int length;
	        while ((length = in.read(buf)) > 0) {
	            out.write(buf, 0, length);
	            System.out.println("----------------------"+out);
	        }
	    }
	}
}
