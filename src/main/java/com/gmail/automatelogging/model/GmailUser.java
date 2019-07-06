package com.gmail.automatelogging.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GmailUser {

    private String username;
    private String password;

    @Override
    public String toString() {
        return "GmailUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
