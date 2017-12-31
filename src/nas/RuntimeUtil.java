package nas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RuntimeUtil {
	
	
	public static void run(String command) throws Exception{
		File execute = new File(command);
		File bat = new File("D:/WevoAir_Down/test.bat");
		FileWriter w = new FileWriter(bat);
		BufferedWriter writer = new BufferedWriter(w); 
		
		String path = execute.getParent();
		
		writer.write("path " + path+"/");
		writer.newLine();
		writer.write("start "+execute.getName());
		writer.close();
		Runtime run = Runtime.getRuntime();
		Process p;
		p = run.exec(bat.getPath());
		p.waitFor();
	}
}
