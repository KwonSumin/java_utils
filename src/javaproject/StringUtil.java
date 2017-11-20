package javaproject;

import org.junit.Test;

public class StringUtil {
	
	@Test
	public void test() {
		Integer[] nums = {1,2,3,4};
		
	}
	
	public static String changeIdx(int idx,String target,String change) {
		return target.substring(0,idx) + change + target.substring(++idx);
	}
}
