package co.kr.sumin.array.core;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

import co.kr.sumin.array.support.basic.InnerAction;
/*
 * @author : sumin
 * @since : 2017-11-20 ; @modifyed : 2017-11-20
 * @version : 1.0.0
 */
public class ArrayUtil {
	
	/*
	 * @param : array - loop돌릴 배열,
	 *  innerAction(t,idx) t = array[해당 인덱스] idx = 인덱스
	 * @description : execute메소드를 구현하여 배열인덱스만큼 루프돌려 execute문 실행
	 * 	InnerAction 상속받아 확장하여 사용 가능.
	 * @since : 2017-11-20 ; @modifyed : 2017-11-20
	 */
	public static void foreach(Object[] array,InnerAction innerAction) 
			throws  InvocationTargetException,IllegalAccessException {
		for(int i=0;i<=array.length-1;i++) {
			innerAction.execute(array[i],i);
		}
	}
	/*
	 * @param : array - loop돌릴 배열,
	 *  innerAction(t,idx) t = array[해당 인덱스] idx = 인덱스
	 * @description : execute메소드를 구현하여 배열인덱스만큼 루프돌려 execute문 실행
	 * 	InnerAction 상속받아 확장하여 사용 가능.
	 * @since : 2017-11-20 ; @modifyed : 2017-11-20
	 */
	public static void foreach(Collection array,InnerAction innerAction) 
			throws  InvocationTargetException,IllegalAccessException {
		Iterator iter =  array.iterator();
		int i=0;
		while(iter.hasNext()) {
			innerAction.execute(iter.next(),i++);
		}
	}
}


