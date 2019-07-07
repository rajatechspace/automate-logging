package com.gmail.automatelogging.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScrapeUserData {

    private String sender;
    private String subject;

    @Override
    public String toString() {
        return "ScrapeUserData{" +
                "sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
