package com.hzu.feirty.entity;

import java.sql.Date;

public class Construction {
	private String teacher_name;
	private long number;
	private String zipname;
	private String zipsize;
	private Date time;
	
	public Construction(String teacher_name,long number,String zipname,String zipsize,Date time){
		this.teacher_name = teacher_name;
		this.number= number;
		this.zipname = zipname;
		this.zipsize = zipsize;
		this.time = time;
		
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getZipname() {
		return zipname;
	}
	public void setZipname(String zipname) {
		this.zipname = zipname;
	}
	public String getZipsize() {
		return zipsize;
	}
	public void setZipsize(String zipsize) {
		this.zipsize = zipsize;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
