package com.hizliyol.core.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hizliyol.core.session.SessionBean;
import com.hizliyol.core.util.Util;

@RestController
public class HomePageRestController {

	@Autowired
	private SessionBean sessionBean;
	
	@RequestMapping(value="/admin",method = RequestMethod.GET,produces="text/html")
	public ModelAndView getAdminPage(HttpServletRequest request){
		Map<String,String> authToken = new HashMap<>() ;
		
		String req =request.getRequestURL().toString().replaceAll(request.getRequestURI(), "");
		req = req + "/"+request.getContextPath();
		if(sessionBean.isRoot()){
			authToken.put("authToken",Util.getAccessToken(new StringBuilder(req).append("/oauth/token").toString(),"root","passw0rd!", "root"));
		}else if(sessionBean.isAdmin()){
			authToken.put("authToken",Util.getAccessToken(new StringBuilder(req).append("/oauth/token").toString(),"admin","adminPassword", "admin"));
		}else{
			authToken.put("authToken",Util.getAccessToken(new StringBuilder(req).append("/oauth/token").toString(),"user","userPassword", "user"));
		}
		
		return new ModelAndView("index", authToken);
	}
	
}
