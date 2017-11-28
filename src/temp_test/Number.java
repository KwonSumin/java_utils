package temp_test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Number {
	public static void main(String[] args) {
		
	}
	
    private static final Map<String, BiFunction<Integer, Integer, Integer>> operators = new HashMap<>();
    
    static {
        operators.put("+", (a, b) -> a+b);
        operators.put("-", (a, b) -> a+b);
        operators.put("*", (a, b) -> a+b);
        operators.put("/", (a, b) -> a+b);
    }


}