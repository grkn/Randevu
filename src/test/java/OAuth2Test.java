import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bilge_gilleez on 16.01.2018.
 */
public class OAuth2Test {

    @Test
    public void getAccessToken(){
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername("root");
        resourceDetails.setPassword("passw0rd!");
        resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
        resourceDetails.setClientId("root");
        resourceDetails.setClientSecret("passw0rd!");
        resourceDetails.setGrantType("password");
        resourceDetails.setScope(Arrays.asList("read", "write"));
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails,new DefaultOAuth2ClientContext(atr));
        List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();

        MappingJackson2HttpMessageConverter jackson = new MappingJackson2HttpMessageConverter();
        list.add(jackson);

        restTemplate.setMessageConverters(list);


        String accessToken = restTemplate.getAccessToken().getValue();


        System.out.println(accessToken);
        RestTemplate template = new RestTemplate(list);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+accessToken);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<List> listResponseEntity = template.exchange("http://localhost:8080/secure/users/get/allUsers", HttpMethod.GET,entity,List.class);
        System.out.println(listResponseEntity);

    }
}
