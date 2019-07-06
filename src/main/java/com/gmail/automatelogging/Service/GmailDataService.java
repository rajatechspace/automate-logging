package com.gmail.automatelogging.Service;

import com.gmail.automatelogging.model.GmailUser;

public interface GmailDataService {

    public String getScrapeData(GmailUser gmailUser) throws Exception;
}
