package com.gmail.automatelogging.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
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
