package temp_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Number {
	public static void main(String[] args) {
		System.out.println(calculator(4,"*",6));
		int c = 10;
		BiFunction<Integer, Integer, Integer> test = (a,b) -> {
			if(a>=3) return 3; else return 4;
		};
		System.out.println(test.apply(5, 5));
		String[] array = {"Using", "Stream", "API", "From", "Java8"};
		ArrayList list = new ArrayList();
		Stream<String> stream = Stream.of(array);
		stream.forEach(list::add);
		System.out.println(list);
		ArrayList<Integer> nums = new ArrayList();
		nums.add(1);
		nums.add(2);
		nums.add(1);
		nums.add(4);
		int[] ints = {1,2,3,4};
		BiFunction<Integer,Integer,Integer> functon1 = (a,b) -> {
			Integer result = 1;
			return result;
		};
		Number.print();
	}
    private static final Map<String, BiFunction<Integer, Integer, Integer>> operators = new HashMap<>();
    
    static {
        operators.put("+", (a, b) -> a+b);
        operators.put("-", (a, b) -> a-b);
        operators.put("*", (a, b) -> a*b);
        operators.put("/", (a, b) -> a/b);
    }
    public static int calculator(int a,String expression,int b) {
    	return operators.get(expression).apply(a, b);
    }
    
    public static void test(ArrayList<Integer> list,TestInterface t) {
    	System.out.println(t.test(list.toArray(new Integer[list.size()])));
    }
    
    public static void print(String...strings) {
    	for(String t : strings)System.out.println(t);
    }
    
}
@FunctionalInterface
interface TestInterface {
	public Integer test(Integer...interger);
}