package org.example;

import org.example.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Main {

    static String cookies;

    public static void main(String[] args) {
        getRequest();
        postRequest();
        putRequest();
        deleteRequest();
    }

    public static void getRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        cookies = responseEntity.getHeaders().getFirst(httpHeaders.SET_COOKIE);

        System.out.println(cookies);

    }

    public static void postRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users";

        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 44);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Cookie", cookies);
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);
        String result = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class).getBody();

        System.out.println(result);
    }

    public static void putRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users";

        User user = new User();
        user.setId(3L);
        user.setName("Thomas");
        user.setLastName("Shelby");
        user.setAge((byte) 44);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Cookie", cookies);
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);
        String result = restTemplate.exchange(
                url, HttpMethod.PUT, entity, String.class).getBody();

        System.out.println(result);
    }

    public static void deleteRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users/3";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Cookie", cookies);
        HttpEntity<User> entity = new HttpEntity<User>(headers);
        String result = restTemplate.exchange(
                url, HttpMethod.DELETE, entity, String.class).getBody();

        System.out.println(result);
    }
}