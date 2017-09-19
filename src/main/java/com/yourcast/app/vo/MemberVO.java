package com.yourcast.app.vo;

import java.sql.Date;

public class MemberVO {
	private int m_num;
	private String id;
	private String pwd;
	private String name;
	private Date birth;
	private int money;
	private Date limit_date;
	private int star_candy;
	private int gender_num;
	private int grade_num;
	
	public MemberVO() {
		
	}

	public MemberVO(int m_num, String id, String pwd, String name, Date birth, int money, Date limit_date,
			int star_candy, int gender_num, int grade_num) {
		super();
		this.m_num = m_num;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.money = money;
		this.limit_date = limit_date;
		this.star_candy = star_candy;
		this.gender_num = gender_num;
		this.grade_num = grade_num;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Date getLimit_date() {
		return limit_date;
	}

	public void setLimit_date(Date limit_date) {
		this.limit_date = limit_date;
	}

	public int getStar_candy() {
		return star_candy;
	}

	public void setStar_candy(int star_candy) {
		this.star_candy = star_candy;
	}

	public int getGender_num() {
		return gender_num;
	}

	public void setGender_num(int gender_num) {
		this.gender_num = gender_num;
	}

	public int getGrade_num() {
		return grade_num;
	}

	public void setGrade_num(int grade_num) {
		this.grade_num = grade_num;
	}
	
}
