package com.zeed.isms;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.zeed.generic.RestApiClient;
import com.zeed.usermanagement.models.ManagedUser;
import com.zeed.usermanagement.models.OauthClientDetails;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Base64;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WireMockTest {

//    @Autowired
//    private RestApiClient restApiClient;

    @ClassRule
    public static WireMockClassRule wiremock = new WireMockClassRule(
            WireMockSpring.options().dynamicPort());

    @Test
    public void getTokenWithRestTemplate() throws Exception {
        String base64encodedString = Base64.getEncoder().encodeToString("isms-service:secret".getBytes());
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + base64encodedString);
        MultiValueMap<String, Object> request = new LinkedMultiValueMap();
        request.add("username", "superuser");
        request.add("password", "password");
        request.add("grant_type", "password");
        String url = "http://127.0.0.1:8011/oauth/token" + "?grant_type=password&username=" + "superuser" + "&password=" + "password";
//        ResponseEntity<OAuth2AccessToken> managedUser = restClient.apiPostWithHttpEntity(url, OAuth2AccessToken.class,request,headers);
        RestApiClient restApiClient = new RestApiClient();
        Object object = restApiClient.apiPostAndGetClass(url,OAuth2AccessToken.class,request,headers);
        System.out.println("Done");
    }
    @Test
    public void getTokenWithRestTemplate2(){
        String base64encodedString = Base64.getEncoder().encodeToString("isms-service:secret".getBytes());
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + base64encodedString);
        MultiValueMap<String, Object> request = new LinkedMultiValueMap();
        request.add("username", "superuser");
        request.add("password", "password");
        request.add("grant_type", "password");
        String url = "http://127.0.0.1:8011/home";
//        ResponseEntity<OAuth2AccessToken> managedUser = restClient.apiPostWithHttpEntity(url, OAuth2AccessToken.class,request,headers);
        System.out.println("Done");
    }

//    @Test
//    public void

}
