package com.gmail.automatelogging.Service;

import com.gmail.automatelogging.model.GmailUser;
import com.gmail.automatelogging.model.ScrapeUserData;
import java.util.List;

public interface GmailDataService {

    public String getScrapeData(GmailUser gmailUser) throws Exception;
    public List<ScrapeUserData> showScrapeData(GmailUser gmailUser) throws Exception;
}
