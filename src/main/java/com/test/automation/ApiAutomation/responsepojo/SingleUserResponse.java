package com.test.automation.ApiAutomation.responsepojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleUserResponse {
	@SerializedName("page")
	@Expose
	private Integer page;
	@SerializedName("per_page")
	@Expose
	private Integer perPage;
	@SerializedName("total")
	@Expose
	private Integer total;
	@SerializedName("total_pages")
	@Expose
	private Integer totalPages;
	@SerializedName("data")
	@Expose
	private List<Datum> data = null;
	@SerializedName("ad")
	@Expose
	private Ad ad;

	public Integer getPage() {
	return page;
	}

	public void setPage(Integer page) {
	this.page = page;
	}

	public SingleUserResponse withPage(Integer page) {
	this.page = page;
	return this;
	}

	public Integer getPerPage() {
	return perPage;
	}

	public void setPerPage(Integer perPage) {
	this.perPage = perPage;
	}

	public SingleUserResponse withPerPage(Integer perPage) {
	this.perPage = perPage;
	return this;
	}

	public Integer getTotal() {
	return total;
	}

	public void setTotal(Integer total) {
	this.total = total;
	}

	public SingleUserResponse withTotal(Integer total) {
	this.total = total;
	return this;
	}

	public Integer getTotalPages() {
	return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
	this.totalPages = totalPages;
	}

	public SingleUserResponse withTotalPages(Integer totalPages) {
	this.totalPages = totalPages;
	return this;
	}

	public List<Datum> getData() {
	return data;
	}

	public void setData(List<Datum> data) {
	this.data = data;
	}

	public SingleUserResponse withData(List<Datum> data) {
	this.data = data;
	return this;
	}

	public Ad getAd() {
	return ad;
	}

	public void setAd(Ad ad) {
	this.ad = ad;
	}

	public SingleUserResponse withAd(Ad ad) {
	this.ad = ad;
	return this;
	}

}
