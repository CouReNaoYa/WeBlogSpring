package com.yc.blog.common;

public class Response<T> {
	private int code; //状态码
	private String msg; //状态描述
	private T data; //数据
	
	public static <T> Response<T> ok(T t) {
		return new Response<T>(200,"success",t);
	}

	public static <T> Response<T> error(int code,String msg,T t) {
		return new Response<T>(code,msg,t);
	}

	public static <T> Response<T> error(T t) {
		return new Response<T>(10001,"error",t);
	}

	public Response() {
	}

	public Response(int code,String msg,T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
