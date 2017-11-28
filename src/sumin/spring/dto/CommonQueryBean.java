package sumin.spring.dto;

import java.lang.reflect.Field;
import java.util.ArrayList;

import sumin.spring.dto.support.Option;
import sumin.spring.dto.support.ReflectUtil;
import sumin.spring.dto.support.Statement;

public class CommonQueryBean {
	private ArrayList<Statement> ifStatement;
	private ArrayList<Statement> statement;
	private String tableName;
	private String seqName;
	private String primaryKey;
	public void setAutoIf() {
		try {
			ArrayList<Field> fields = ReflectUtil.getNotNullFields(this);
			ifStatement = null;
			for(int i=0;i<=fields.size()-1;i++) {
				String field = fields.get(i).getName();
				Object value = fields.get(i).get(this);
				
				
				if(fields.get(i).getDeclaredAnnotation(Option.class)!=null) {
					addIf(field,value,fields.get(i).getDeclaredAnnotation(Option.class).value());
				}else {
					addIf(field, value);
				}
			}
			
			
		}catch(Exception e) {e.printStackTrace();}
	}
	public void setAutoState() {
		try {
			ArrayList<Field> fields = ReflectUtil.getNotNullFields(this);
			statement = null;
			for(int i=0;i<=fields.size()-1;i++) {
				String field = fields.get(i).getName();
				Object value = fields.get(i).get(this);
				addState(field, value);
			}
			
			
		}catch(Exception e) {e.printStackTrace();}
	}
	public void addIf(String field,Object value) {
		if(ifStatement == null) ifStatement = new ArrayList();
		ifStatement.add(new Statement(field,value));
	}
	public void addIf(String field,Object value,String option) {
		if(ifStatement == null) ifStatement = new ArrayList();
		ifStatement.add(new Statement(field,value,option));
	}
	public void addState(String field,Object value) {
		if(statement == null) statement = new ArrayList();
		statement.add(new Statement(field,value));
	}
	public void addState(String field,Object value,String option) {
		if(statement == null) statement = new ArrayList();
		statement.add(new Statement(field,value,option));
	}
	public ArrayList<Statement> getIfStatement() {
		return ifStatement;
	}
	public void setIfStatement(ArrayList<Statement> ifStatement) {
		this.ifStatement = ifStatement;
	}
	public ArrayList<Statement> getStatement() {
		return statement;
	}
	public void setStatement(ArrayList<Statement> statement) {
		this.statement = statement;
	}
	public String getSeqName() {
		return seqName;
	}
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Override
	public String toString() {
		return "CommonQueryBean [ifStatement=" + ifStatement + ", statement=" + statement + ", tableName=" + tableName
				+ ", seqName=" + seqName + ", primaryKey=" + primaryKey + "]";
	}
	
	
}
