package es.alex.learning;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloBean {

	private String message;

	
	@JsonProperty("SomeAmazingMessage")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HelloBean(String message) {
		super();
		this.message = message;
	}
	
	
}
