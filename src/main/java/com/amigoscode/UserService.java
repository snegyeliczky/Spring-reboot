package com.amigoscode;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    public String getUserName() {
        return "AmigosCode";
    }
}
