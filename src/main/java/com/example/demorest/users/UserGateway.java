package com.example.demorest.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserGateway {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api_url}")
    private String url;

    public User getById(int id) {
        String path = String.format("%s/users/%d", url, id);
        User response = restTemplate.getForObject(path, User.class);
        return response;
    }

}
