package sumin.spring.dto.test;

import sumin.spring.dto.CommonQueryBean;
import sumin.spring.dto.support.Option;

public class ExampleBean extends CommonQueryBean {
	
	@Option("like")
	private String name;
	private int age;
	private String job;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}


	@Override
	public String toString() {
		return super.toString()+"\nExampleBean [name=" + name + ", age=" + age + ", job=" + job + "]";
	}
	
}
