package com.acme.acmetrade.domain;

public class ResponseStatus {

	private String statusCode;
	private String statusMessage;
	
	public ResponseStatus() {
		
	}
	
	public ResponseStatus(String statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseStatus [statusCode=");
		builder.append(statusCode);
		builder.append(", statusMessage=");
		builder.append(statusMessage);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
