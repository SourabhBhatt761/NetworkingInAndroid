package com.example.networkinginjava.api.model;

import java.util.List;

public class UsersResponse {
	private int perPage;
	private int total;
	private List<DataItem> data; 		//recycler view content
	private int page;
	private int totalPages;
	private Support support;

	public int getPerPage(){
		return perPage;
	}

	public int getTotal(){
		return total;
	}

	public List<DataItem> getData(){
		return data;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public Support getSupport(){
		return support;
	}
}