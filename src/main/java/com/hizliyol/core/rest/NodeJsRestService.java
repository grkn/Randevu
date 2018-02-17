package com.hizliyol.core.rest;

import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class NodeJsRestService {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value="/api/get/witai/entities",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getEntities(){
		HttpHeaders headers = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        MediaType mediaType = new MediaType("application", "json", utf8);
        headers.setContentType(mediaType);

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/get/witai/entities", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
}
