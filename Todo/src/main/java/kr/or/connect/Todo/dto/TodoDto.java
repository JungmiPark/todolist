package kr.or.connect.Todo.dto;

public class TodoDto {
	private long id;
	private String title;
	private String name;
	private Integer sequence;
	private String type;
	private String regdate;
	
	public TodoDto() {
		
	}
	
	public TodoDto(String title, String name, Integer sequence) {
		this.title = title;
		this.name = name;
		this.sequence = sequence;
	}
	
	public TodoDto(String title, String regdate, String name, Integer sequence, String type, long id) {
		this.title = title;
		this.regdate = regdate;
		this.name = name;
		this.sequence = sequence;
		this.type = type;
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", title=" + title + ", name=" + name + ", sequence=" + sequence + ", type=" + type
				+ ", regdate=" + regdate + "]";
	}
	
	
	
}
