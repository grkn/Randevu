package com.hizliyol.core.rest.modal;

import java.util.List;

public class ListTemplate {

	private String viewMoreButtonName;
	private String viewMoreButtonUrl;
	private List<ListTemplateItem> list;
	
	public String getViewMoreButtonName() {
		return viewMoreButtonName;
	}
	public void setViewMoreButtonName(String viewMoreButtonName) {
		this.viewMoreButtonName = viewMoreButtonName;
	}
	public String getViewMoreButtonUrl() {
		return viewMoreButtonUrl;
	}
	public void setViewMoreButtonUrl(String viewMoreButtonUrl) {
		this.viewMoreButtonUrl = viewMoreButtonUrl;
	}
	public List<ListTemplateItem> getList() {
		return list;
	}
	public void setList(List<ListTemplateItem> list) {
		this.list = list;
	}
	
	
}
