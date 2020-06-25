package net.javaguides.springboot.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.javaguides.springboot.entity.User;

public class RestClien {

	private static final String GET_ALL_USER_API = "http://localhost:9090/api/users";
	private static final String GET_USERS_BY_ID_API = "http://localhost:9090/api/users/{id}";
	private static final String CREATE_USER_API = "http://localhost:9090/api/users";
	private static final String UPDATE_USER_API = "http://localhost:9090/api/users/{id}";
	private static final String DELETE_USER_API = "http://localhost:9090/api/users/{id}";
	
	static RestTemplate restTemplate = new RestTemplate();
	public static void main(String[] args) {
		callGetAllUsersAPI();
		callGetUserByIdAPI();
		callCreateUserAPI();
		callUpdateUserAPI();
		callDeleteUserAPI();
	}
	
	private static void callDeleteUserAPI() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 3);
		restTemplate.delete(DELETE_USER_API, param);
		
	}

	private static void callUpdateUserAPI() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 2);
		User updateUser = new User("Pinki", "pink", "Pinki@ymail.com");
		restTemplate.put(UPDATE_USER_API, updateUser, param);
		
	}

	private static void callCreateUserAPI() {
		User user = new User("Ramesh", "sing", "Ramesh@gmail.com");
		ResponseEntity<User> userResult =restTemplate.postForEntity(CREATE_USER_API, user, User.class);
		System.out.println(userResult.getBody());
	}

	private static void callGetUserByIdAPI() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 2);
		
		User user = restTemplate.getForObject(GET_USERS_BY_ID_API, User.class, param);
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());
	}

	private static void callGetAllUsersAPI() {
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters",header);
		ResponseEntity< String> result = restTemplate.exchange(GET_ALL_USER_API, HttpMethod.GET, entity, String.class);
		System.out.println(result);
	}
	
	
}
