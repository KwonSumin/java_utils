package sumin.spring.dto.support;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectUtil {
	public static<T> ArrayList<Field> getNotNullFields(Object target) throws IllegalAccessException{
		
		Field[] fields = target.getClass().getDeclaredFields();
		ArrayList<Field> result = new ArrayList<Field>();
		for(Field t:fields) {
			t.setAccessible(true);
			if(t.get(target)!=null)
				result.add(t);
		}
		return result;
	}
	
	public static void setNullInDontSelectField(String[] fieldNames,Object target) 
			throws IllegalAccessException{
		Field[] fields = target.getClass().getDeclaredFields();
		List list = Arrays.asList(fieldNames);
		for(Field t:fields) {
			if(list.indexOf(t.getName()) == -1) {
				t.setAccessible(true);
				t.set(target, null);
			}
		}
	}
}
