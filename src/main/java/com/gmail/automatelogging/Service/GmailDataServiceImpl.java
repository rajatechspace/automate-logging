package com.gmail.automatelogging.Service;

import com.gmail.automatelogging.model.GmailUser;
import com.gmail.automatelogging.model.ScrapeData;
import com.gmail.automatelogging.model.ScrapeUserData;
import com.gmail.automatelogging.repository.ScrapeDataRepository;
import com.gmail.automatelogging.repository.ScrapeRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.*;
import java.util.*;

@Slf4j
@Service
public class GmailDataServiceImpl implements GmailDataService {

    @Autowired
    private ScrapeRepositoryImpl scrapeRepository;

    @Autowired
    private ScrapeDataRepository scrapeDataRepository;


    private static String STORE = "imap.gmail.com";
    private static String FOLDER = "[Gmail]/All Mail";

    /* select addresses to extract */
    private static Boolean GETFROM = true;
    private static Boolean GETTO = true;
    private static Boolean GETCC = false;
    private static Boolean GETBCC = false;

    /* select addresses to filter */
    private static Boolean FILTER_FROM = true;
    private static Boolean FILTER_TO = false;

    /* filter addresses to extract */
    private static String addressFilter[] = {
            "noreply@", "no-reply@", "no.reply@", "donotreply@", "do_not_reply@", "webmaster@",
            "Gaming", "Fiesta", "Replica", "VIAGRA", "Watches", "Cialis"
            /* your filters here ... */
    };

    @Override
    public String getScrapeData(GmailUser gmailUser) throws Exception {

        String user = gmailUser.getUsername();
        String password = gmailUser.getPassword();
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        try {
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect(STORE, user, password);

            // Choose folder to work in
            Folder myfolder = store.getFolder(FOLDER);
            myfolder.open(Folder.READ_ONLY);

            Message messages[] = myfolder.getMessages();
            List<ScrapeData> scrapeDataList = new ArrayList<>();

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                if (GETFROM) {
                    Address fromAddresses[] = message.getFrom();
                    ScrapeData myData = new ScrapeData();
                    addAddresses(fromAddresses, FILTER_FROM, myData);
                    if (!StringUtils.isEmpty(myData.getSender())) {
                        myData.setSubject(message.getSubject());
                        scrapeDataList.add(myData);
                    }

                }
            }

            scrapeDataRepository.saveAll(scrapeDataList);
            System.out.println("***** Data Saved Successfully *******");

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.exit(2);
        }

        System.out.println("Writing collected addresses to file.");
        return null;

    }

    private static Address filter(Address address) {
        String addressString = address.toString();
        for (String filter : addressFilter) {
            if (addressString.contains(filter)) {
                return null;
            }
        }
        return address;


    }

    private static String cleanAddress(String address) {
        if (address.contains("<"))
            address = address.substring(address.lastIndexOf("<") + 1, address.indexOf(">"));
        return address.toLowerCase();
    }


    private static void addAddresses(Address[] addresses, Boolean filter, ScrapeData scrapeData) {
        for (Address address : addresses) {
            if (filter)
                address = filter(address);
            if (address == null)
                continue;
            else
                scrapeData.setSender(cleanAddress(address.toString()));
        }
    }

    @Override
    public List<ScrapeUserData> showScrapeData(GmailUser gmailUser) throws Exception {
        log.info("Inside showScrapeData service class Swathi");
        List<ScrapeUserData> scrapeUserDataList = new ArrayList<>();

        List<ScrapeData> scrapeData = scrapeRepository.getScrapeData();
        for (ScrapeData data : scrapeData) {
            log.debug("Sender is  {} and subject is {}", data.getSender(), data.getSubject());
            scrapeUserDataList.add(new ScrapeUserData(data.getSender(), data.getSubject()));
        }
        return scrapeUserDataList;
    }
}
