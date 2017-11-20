package co.kr.sumin.array.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import co.kr.sumin.array.core.ArrayUtil;
import co.kr.sumin.array.support.basic.InnerAction;
import co.kr.sumin.array.support.custom.EqualsAction;

public class ArrayUtilTest {
	@Test
	public void foreach() throws Exception {
		String[] array = {
				"1","2","3","1"
		};
		String equarsTarget = "1";
		Collection collection = Arrays.asList(array);
		System.out.println("array");
		ArrayUtil.foreach(array, new InnerAction() {
			@Override
			public void execute(Object t,int idx) {
				
				System.out.print(idx + " : ");
				System.out.println(t.equals(equarsTarget));
			}
		});
		System.out.println("collection");
		ArrayUtil.foreach(collection, new InnerAction() {
			@Override
			public void execute(Object t,int idx) {
				System.out.print(idx + " : ");
				System.out.println(t.equals(equarsTarget));
			}
		});
		
		EqualsAction hasEquals = new EqualsAction() {
			
			@Override
			public void execute(Object t, int idx) {
				if(t.equals(equarsTarget)) System.out.println(idx + " : ok");
			}
		};
		hasEquals.setEqualsTarget("1");
		ArrayUtil.foreach(array, hasEquals);
	}
}
