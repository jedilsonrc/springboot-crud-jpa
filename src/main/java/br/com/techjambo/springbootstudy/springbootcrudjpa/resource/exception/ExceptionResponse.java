package br.com.techjambo.springbootstudy.springbootcrudjpa.resource.exception;

import java.util.Date;

public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String description;
	
	
	public ExceptionResponse() {
		
	}


	public ExceptionResponse(Date timestamp, String message, String description) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
