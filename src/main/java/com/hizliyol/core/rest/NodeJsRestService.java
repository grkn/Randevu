package com.hizliyol.core.rest;

import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hizliyol.core.rest.modal.ModelEntity;



@RestController
public class NodeJsRestService {

	
	@RequestMapping(value="/api/get/witai/entities",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getEntities(){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpHeaders headers = createJsonHeader();

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/get/witai/entities", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/post/intent/expressions",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String insertExpression(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8000/post/intent/expressions", entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/delete/intent/expressions",method = RequestMethod.DELETE,produces="application/json;charset=utf-8")
	public String deleteExpression(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/intent/expressions",HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/create/intent",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String createIntent(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/create/intent",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/delete/intent",method = RequestMethod.DELETE,produces="application/json;charset=utf-8")
	public String deleteIntent(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/intent",HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/get/meaningful/sentence",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getSentence(@RequestParam("intent") String intent){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/get/meaningful/sentence?intent="+intent,HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	
	@RequestMapping(value="/api/send/meaningful/sentence",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String addResponse(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/send/meaningful/sentence",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/delete/meaningful/sentence",method = RequestMethod.DELETE,produces="application/json;charset=utf-8")
	public String deleteResponseSentences(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/meaningful/sentence?intent="+modelEntity.getIntent(),HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/view/get/carousel",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String getCarousel(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/carousel",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/view/create/carousel",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String createCarousel(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/carousel",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/view/get/quickReply",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String getQuickReply(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/quickReply",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/view/create/quickReply",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String createQuickReply(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/quickReply",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/view/get/listTemplate",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String getListTemplate(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/listTemplate",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}

	@RequestMapping(value="/api/view/create/listTemplate",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String createListTemplate(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/listTemplate",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/view/get/genericButtons",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String getGenericButtons(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/genericButtons",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/view/create/genericButtons",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String createGenericButtons(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/genericButtons",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/view/get/attachment",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String getAttachments(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/attachment",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/view/create/attachment",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String createAttachment(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/attachment",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/get/threshold",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getThreshold(){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/get/threshold",HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/change/threshold",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getThreshold(@RequestParam("threshold") Float threshold){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/change/threshold/"+threshold.toString(),HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	
	@RequestMapping(value="/api/add/responseList",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getThreshold(@RequestParam("response") String response){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/add/responseList/"+response,HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}


	@RequestMapping(value="/api/delete/responseList",method = RequestMethod.DELETE,produces="application/json;charset=utf-8")
	public String deleteResponseList(@RequestParam("response") String response){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/responseList/"+response,HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/mongo/findByLimitTen/training_messages",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getTrainingMessages(){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/findByLimitTen/training_messages",HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/witai/delete",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String witAIDelete(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/witai/delete",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/witai/validate",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String witAIValidate(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/witai/validate",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/facebook/get",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String facebookGetDeploymentInfo(){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/facebook/get",HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}

	
	@RequestMapping(value="/api/facebook/post",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String facebookDeploy(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/facebook/post",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/witaiDeploy/post",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String witaiDeploy(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/witaiDeploy/post",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/witaiDeploy/get",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getWitaiDeploy(){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/witaiDeploy/get",HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/mongo/emoji/get",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getEmojiFromMongoDb(){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/emoji/get",HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	

	@RequestMapping(value="/api/view/create/emoji",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String createEmoji(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/emoji",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}

	@RequestMapping(value="/api/view/get/emoji",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String getEmoji(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/emoji",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/save/emoji/relation",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String setEmojiRelation(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/save/emoji/relation",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/mongo/emojiRelation/get",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public String getEmojiRelation(){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader());
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/emojiRelation/get",HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	

	@RequestMapping(value="/api/delete/emoji/relation",method = RequestMethod.DELETE,produces="application/json;charset=utf-8")
	public String deleteEmoji(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/emoji/relation",HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value="/api/mongo/post/subject",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public String createSubject(@RequestBody ModelEntity modelEntity){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity);
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/post/subject",HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	private HttpEntity<Object> createEntityAndHeader(ModelEntity modelEntity) {
		HttpHeaders headers = createJsonHeader();
        
        HttpEntity<Object> entity = new HttpEntity<Object>(modelEntity, headers);
		return entity;
	}

	private HttpHeaders createJsonHeader() {
		HttpHeaders headers = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        MediaType mediaType = new MediaType("application", "json", utf8);
        headers.setContentType(mediaType);
		return headers;
	}
	
}
