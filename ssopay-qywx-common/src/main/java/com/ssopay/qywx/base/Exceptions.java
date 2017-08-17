package com.ssopay.qywx.base;

public class Exceptions extends RuntimeException {
	private static final long serialVersionUID = -6695188947450520803L;
	private String key;

	private Object[] values;

	public Exceptions() {
		super();
	}

	public Exceptions(String message, Throwable throwable) {
		super(message, throwable);
	}

	public Exceptions(String message) {
		super(message);
	}

	public Exceptions(Throwable throwable) {
		super(throwable);
	}

	public Exceptions(String message,String key){
		super(message);
		this.key = key;
	}

	public Exceptions(String message,String key,Object value){
		super(message);
		this.key = key;
		this.values = new Object[]{value};
	}

	public Exceptions(String message,String key,Object[] values){
		super(message);
		this.key = key;
		this.values = values;
	}

	public String getKey() {
		return key;
	}

	public Object[] getValues() {
		return values;
	}

}
