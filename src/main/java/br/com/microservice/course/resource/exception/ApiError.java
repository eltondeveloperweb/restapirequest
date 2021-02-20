package br.com.microservice.course.resource.exception;

import java.io.Serializable;
import java.util.Date;

public class ApiError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String msg;
	private Date date;	
	
	public ApiError() {
	}	
	
	public ApiError(int code, String msg, Date date) {
		super();
		this.code = code;
		this.msg = msg;
		this.date = date;
	}



	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
