package com.company.view.controller;

// URL주소 결정 - 접두사 , 접미사 
public class ViewResolver {
	//필드 선언
	public String prefix;	//접두사
	public String suffix;	//접미사
	
	//Setter만 필요
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
}
