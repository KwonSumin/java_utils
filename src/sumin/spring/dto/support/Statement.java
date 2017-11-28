package sumin.spring.dto.support;

public class Statement {
	private String field;
	private Object value;
	private String option;
	public Statement(String field, Object value, String option) {
		super();
		this.field = field;
		this.value = value;
		this.option = option;
	}
	public Statement(String field, Object value) {
		super();
		this.field = field;
		this.value = value;
	}
	@Override
	public String toString() {
		return "Statement [field=" + field + ", value=" + value + ", option=" + option + "]";
	}
	
}
