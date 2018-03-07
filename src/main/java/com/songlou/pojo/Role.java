package com.songlou.pojo;

public class Role {
	private int id;
	private String name;//��ɫ��
	private String note;//��ע
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public boolean equals(Object obj) {  
    	Role role = (Role) obj;
    	return this.id == role.id;
	}
}
