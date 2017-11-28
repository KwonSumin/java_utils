package temp_test;

import java.io.File;

public class Qna {
	public static void main(String[] args) {
		File questionDir = new File("c:/dev/Test/qna/question");
		for(String t : questionDir.list()) System.out.println(t);
	}
}
