package temp_test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegExrUtil {
	
	@Test
	public void test() {
		String str ="test ${test1}, ${test2} end";
		Pattern p = Pattern.compile("\\$\\{[^}]*\\}");
		Matcher m = p.matcher(str);
		System.out.println(getPatternValueList(str, p));
	}
	
	public static ArrayList<String> getPatternValueList(String str,Pattern p){
		ArrayList<String> list = null;
		Matcher m = p.matcher(str);
		while(m.find()){
			if(list == null)list = new ArrayList();
			list.add(m.group());
		}
		return list;
	}
	
	public static int getPatternCount(String str,Pattern p) {
		Matcher m = p.matcher(str);
		int i=0;
		while(m.find()) {
			i++;
		}
		return i;
	}
	public static String getPatternValue(String str,Pattern p,int idx) {
		Matcher m = p.matcher(str);
		int i=0;
		while(m.find()) {
			if(i==idx) return m.group();
			i++;
		}
		return null;
	}
	public static String patternToValues(String str,Pattern p,String...values) {
		Matcher m = p.matcher(str);
		int i=0;
		while(m.find()) {
			System.out.println(m.group());
			str = str.replaceFirst(p.pattern(), values[i++]);
		}
		return str;
	}
}
