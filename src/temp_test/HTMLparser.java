package temp_test;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLparser {
	public static void main(String[] args) throws Exception{
		Document google1 = Jsoup.connect("http://www.google.com").get();
		Connection conn = Jsoup.connect("http://www.google.com");
		//Document google2 = Jsoup.connect("http://www.google.com").post();

		// Response로부터 Document 얻어오기
		Connection.Response response = Jsoup.connect("http://localhost/mvc/board/list")
		                                    .method(Connection.Method.GET)
		                                    .execute();
		Document google3 = new Document("http://localhost/mvc/board/list");
		
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("javascript");
		engine.eval("print(Math.ceil(102 /10));");
		Thread.sleep(5000);
		System.out.println(google3);
		System.out.println("완료");
	}
}
