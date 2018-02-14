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
        
        // 이름 입력
        System.out.println("소스폴더와 타켓폴더의 이름을 입력하세요");
        SrcDir = src.nextLine();
        TarDir = tar.nextLine();
        
        DirCopy copy = new DirCopy();
        
        //두 경로 받기
        String SrcAndTarDir[] = copy.getSrcAndTarDir(SrcDir, TarDir);
        
        
        //파일 복사
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
        	
        	System.out.println(SrcAndTarDir[0]+"\\"+fileList[i].getName()+" 이 " +SrcAndTarDir[1]+"\\"+fileList[i].getName() +" 로 복사되었습니다.");
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
