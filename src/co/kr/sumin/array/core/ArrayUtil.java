package co.kr.sumin.array.core;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

import co.kr.sumin.array.support.basic.InnerAction;

public class ArrayUtil {
	
	
	public static void foreach(Object[] array,InnerAction innerAction) 
			throws  InvocationTargetException,IllegalAccessException {
		for(int i=0;i<=array.length-1;i++) {
			innerAction.execute(array[i],i);
		}
	}
	
	public static void foreach(Collection array,InnerAction innerAction) 
			throws  InvocationTargetException,IllegalAccessException {
		Iterator iter =  array.iterator();
		int i=0;
		while(iter.hasNext()) {
			innerAction.execute(iter.next(),i++);
		}
	}
}


