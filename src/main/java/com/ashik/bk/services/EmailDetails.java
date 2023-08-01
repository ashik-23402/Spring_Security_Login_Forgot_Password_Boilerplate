package com.ashik.bk.services;

public class EmailDetails {
	
	private String from;
	
	private String to;
	
	private String subject;
	
	private String mssgContent;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMssgContent() {
		return mssgContent;
	}

	public void setMssgContent(String mssgContent) {
		this.mssgContent = mssgContent;
	}

	public EmailDetails(String from, String to, String subject, String mssgContent) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.mssgContent = mssgContent;
	}

	public EmailDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmailDetails [from=" + from + ", to=" + to + ", subject=" + subject + ", mssgContent=" + mssgContent
				+ "]";
	}
	
	

}
