package sumin.spring.dto.test;

import org.junit.Test;

public class TesterBean {
	@Test
	public void test() throws Exception{
		ExampleBean bean = new ExampleBean();
		bean.setName("sumin");
		bean.setAge(30);
		bean.setAutoIf();
		bean.setTableName("board");
		bean.setPrimaryKey("seq");
		System.out.println(bean);
	}
}
