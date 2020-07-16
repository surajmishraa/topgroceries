package com.user.register.model;

import java.io.Serializable;

public class JwtResponseWithUsername implements Serializable {
    String user_id;
    String Token;

    public JwtResponseWithUsername(String token, String user_id) {
        this.user_id = user_id;
        Token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getToken() {
        return Token;
    }
}
