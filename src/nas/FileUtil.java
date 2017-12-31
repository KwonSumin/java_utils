package nas;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileUtil {
	
	public static void main(String[] args) throws Exception {
		
		/*
		ProcessBuilder pb = new ProcessBuilder();
		Process p = pb.command("d:/wevoair_down/test.bat").start();
		
		
		InputStream err = p.getErrorStream();
		p.waitFor();
		*/
	
		RuntimeUtil.run("d:/wevoair_down/test.ahk");
	}
	
	public static ArrayList<String> getList(String filePath) throws Exception{
		ArrayList<String> result = new ArrayList<String>();
		
		File path = new File(filePath);
		File[] files = path.listFiles();
		for(int i=0; i<files.length; i++) {
			if(files[i].isFile()) {
				result.add(files[i].getName());
			}
			else
				result.add(files[i].getName()+"/dir");
		}
		return result;
	}
}
