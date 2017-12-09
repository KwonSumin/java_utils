package work;

import java.util.HashMap;
import java.util.List;

public class TestVO {
	@TestMapper(columId="TO",tagLocation="note/to")
	private String to;
	@TestMapper(columId="FROM",tagLocation="note/from",startNum=1,endNum=10)
	private List<String> from;
	private HashMap data;
	
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public List<String> getFrom() {
		return from;
	}
	public void setFrom(List<String> from) {
		this.from = from;
	}
	public HashMap getData() {
		return data;
	}
	public void setData(HashMap data) {
		this.data = data;
	}
	
	
}
