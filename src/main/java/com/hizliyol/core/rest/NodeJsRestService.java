package com.hizliyol.core.rest;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/api/get/witai/entities", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getEntities(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpHeaders headers = createJsonHeader(request.getHeader("Authorization"));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/get/witai/entities", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/post/intent/expressions", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String insertExpression(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8000/post/intent/expressions", entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/delete/intent/expressions", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public String deleteExpression(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/intent/expressions", HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/create/intent", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createIntent(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/create/intent", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/delete/intent", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public String deleteIntent(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/intent", HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/get/meaningful/sentence", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getSentence(@RequestParam("intent") String intent,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/get/meaningful/sentence?intent=" + intent, HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/send/meaningful/sentence", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String addResponse(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/send/meaningful/sentence", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/delete/meaningful/sentence", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public String deleteResponseSentences(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/meaningful/sentence?intent=" + modelEntity.getIntent(), HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/view/get/carousel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String getCarousel(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/carousel", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/view/create/carousel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createCarousel(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/carousel", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/view/get/quickReply", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String getQuickReply(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/quickReply", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/view/create/quickReply", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createQuickReply(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/quickReply", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/view/get/listTemplate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String getListTemplate(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/listTemplate", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}

	@RequestMapping(value = "/api/view/create/listTemplate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createListTemplate(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/listTemplate", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/view/get/genericButtons", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String getGenericButtons(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/genericButtons", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/view/create/genericButtons", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createGenericButtons(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/genericButtons", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/view/get/attachment", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String getAttachments(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/attachment", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/view/create/attachment", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createAttachment(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/attachment", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/get/threshold", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getThreshold(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/get/threshold", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/change/threshold", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getThreshold(@RequestParam("threshold") Float threshold,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/change/threshold/" + threshold.toString(), HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	
	@RequestMapping(value = "/api/add/responseList", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getThreshold(@RequestParam("response") String response,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/add/responseList/" + response, HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}


	@RequestMapping(value = "/api/delete/responseList", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public String deleteResponseList(@RequestParam("response") String response,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/responseList/" + response, HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/mongo/findByLimitTen/training_messages", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getTrainingMessages(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/findByLimitTen/training_messages", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/witai/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String witAIDelete(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/witai/delete", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/witai/validate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String witAIValidate(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/witai/validate", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/facebook/get", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String facebookGetDeploymentInfo(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/facebook/get", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/facebook/post", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String facebookDeploy(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/facebook/post", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/witaiDeploy/post", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String witaiDeploy(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/witaiDeploy/post", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/witaiDeploy/get", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getWitaiDeploy(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/witaiDeploy/get", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/mongo/emoji/get", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getEmojiFromMongoDb(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/emoji/get", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}

	@RequestMapping(value = "/api/view/create/emoji", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createEmoji(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/create/emoji", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}

	@RequestMapping(value = "/api/view/get/emoji", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String getEmoji(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/view/get/emoji", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/save/emoji/relation", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String setEmojiRelation(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/save/emoji/relation", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/mongo/emojiRelation/get", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getEmojiRelation(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/emojiRelation/get", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}

	@RequestMapping(value = "/api/delete/emoji/relation", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public String deleteEmoji(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/delete/emoji/relation", HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/mongo/post/subject", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createSubject(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/post/subject", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/mongo/get/subjects",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public String getSubjects(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/get/subjects", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}

	@RequestMapping(value = "/api/mongo/delete/subject", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public String deleteSubject(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/delete/subject", HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/mongo/post/subjectRelation", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createSubjectRelation(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/post/subjectRelation", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/mongo/delete/subjectRelation", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public String deleteSubjectRelation(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/delete/subjectRelation", HttpMethod.DELETE, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	
	@RequestMapping(value = "/api/mongo/get/subject/{intentName}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public String getSubjectByPathVariable(@PathVariable String intentName,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		ModelEntity modelEntity = new ModelEntity();
		modelEntity.setIntent(intentName);
		HttpEntity<Object> entity = new HttpEntity<>(modelEntity,createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/mongo/get/subject", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	
	
	@RequestMapping(value = "/api/witaiCreateApp/post", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String createWitaiApp(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/witaiCreateApp/post", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/add/persistentMenu", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String addPersistentMenuItem(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/add/persistentMenu", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/get/persistentMenu",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public String getPersistentMenu(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/get/persistentMenu", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	
	@RequestMapping(value = "/api/chatbase/post", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String chatbase(@RequestBody ModelEntity modelEntity,HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = createEntityAndHeader(modelEntity,request.getHeader("Authorization"));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/chatbase/post", HttpMethod.POST, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	@RequestMapping(value = "/api/chatbase/get", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getchatbase(HttpServletRequest request){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		
		HttpEntity<Object> entity = new HttpEntity<>(createJsonHeader(request.getHeader("Authorization")));
        
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8000/chatbase/get", HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        
        return result;
	}
	
	
	private HttpEntity<Object> createEntityAndHeader(ModelEntity modelEntity,String authorization) {
		HttpHeaders headers = createJsonHeader(authorization);
        
        HttpEntity<Object> entity = new HttpEntity<Object>(modelEntity, headers);
		return entity;
	}

	private HttpHeaders createJsonHeader(String authorization) {
		HttpHeaders headers = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        MediaType mediaType = new MediaType("application", "json", utf8);
        headers.setContentType(mediaType);
        
        
        headers.set("Authorization", request.getHeader("Authorization"));
		return headers;
	}
	
}
