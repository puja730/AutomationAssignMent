package com.test.automation.ApiAutomation.responsepojo;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ad {

	@SerializedName("company")
	@Expose
	private String company;
	@SerializedName("url")
	@Expose
	private String url;
	@SerializedName("text")
	@Expose
	private String text;

	public String getCompany() {
	return company;
	}

	public void setCompany(String company) {
	this.company = company;
	}

	public Ad withCompany(String company) {
	this.company = company;
	return this;
	}

	public String getUrl() {
	return url;
	}

	public void setUrl(String url) {
	this.url = url;
	}

	public Ad withUrl(String url) {
	this.url = url;
	return this;
	}

	public String getText() {
	return text;
	}

	public void setText(String text) {
	this.text = text;
	}

	public Ad withText(String text) {
	this.text = text;
	return this;
	}

}
