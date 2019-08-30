package com.user.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Emp implements Serializable{
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private Double sal;
	private Double comm;
	private Integer deptno;	
}
