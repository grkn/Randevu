package com.hizliyol.core.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class ChatBeanController extends BaseController{

	
	public String authenticate(){
		return "Bearer 91723e23-3537-4cbc-8c64-11fff4ed09ec";
	}
}
