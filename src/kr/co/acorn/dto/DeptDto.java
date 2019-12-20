package kr.co.acorn.dto;

public class DeptDto {
	private int no;
	private String name;
	private String loc;
	public DeptDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeptDto(int no, String name, String loc) {
		super();
		this.no = no;
		this.name = name;
		this.loc = loc;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
