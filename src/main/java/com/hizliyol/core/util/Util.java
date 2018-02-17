package com.hizliyol.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

public class Util {
	
    public static String getStackTrace(StackTraceElement[] arr){
        StringBuffer builder = new StringBuffer();
        for (StackTraceElement element :arr) {
            builder.append(element.toString()).append("\n");
        }
        return builder.toString();
    }
    
    public static String getAccessToken(String url,String userName,String password,String clientId){
    	 ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
         resourceDetails.setUsername(userName);
         resourceDetails.setPassword(password);
         resourceDetails.setAccessTokenUri(url);
         resourceDetails.setClientId(clientId);
         resourceDetails.setClientSecret(password);
         resourceDetails.setGrantType("password");
         resourceDetails.setScope(Arrays.asList("read", "write"));
         AccessTokenRequest atr = new DefaultAccessTokenRequest();

         OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails,new DefaultOAuth2ClientContext(atr));
         List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();

         MappingJackson2HttpMessageConverter jackson = new MappingJackson2HttpMessageConverter();
         list.add(jackson);

         restTemplate.setMessageConverters(list);


         String accessToken = restTemplate.getAccessToken().getValue();
         
         return accessToken;

    }
}
