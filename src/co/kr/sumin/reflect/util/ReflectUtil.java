package co.kr.sumin.reflect.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class ReflectUtil {
	
	public static Object executeMethod(Object obj,String method,Object...args)
		throws IllegalAccessException,InvocationTargetException{
		Method[] methods = obj.getClass().getDeclaredMethods();
		for(Method target : methods) {
			target.setAccessible(true);
			String name = target.getName();
			if(name.equals(method)) {
				Class[] types = target.getParameterTypes();
					
				
				for(int i=0;i<=types.length-1;i++) {
					
					args[i] = castType(args[i],types[i]);
					
				}
				
				
				return target.invoke(obj, args);
			}
		}
		return null;
	}
	
	private static Object castType(Object target,Class type) throws ClassCastException {//1차 테스트 완료
		if(target != null) {
			String castName = type.getSimpleName();
			String simpleName = target.getClass().getSimpleName();
			
			//TODO : 추가로 필요한 형변환 추가..
			try {
				if(castName.equals("int") && simpleName.equals("BigDecimal")) {
					target = ( (BigDecimal)target ).intValueExact();
				}else if(castName.equals("int") && simpleName.equals("String")){
					target = Integer.parseInt(String.valueOf(target));
				}else if (castName.equals("int")){
					target = Integer.parseInt(target.toString());
				}else if(castName.equals("Integer") && simpleName.equals("String")) {
					target = Integer.parseInt(String.valueOf(target));
				}else if(castName.equals("String")){
					target = String.valueOf(target);
				}else {
					type.cast(target);
				}
			}catch(ClassCastException e) {
				throw new ClassCastException("ReflectUtil 형변환 추가해주세요.");
			}
			
			
		}
		return target;
	}
	
	
}
