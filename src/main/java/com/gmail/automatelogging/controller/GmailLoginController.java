package com.gmail.automatelogging.controller;

import com.gmail.automatelogging.Service.GmailDataService;
import com.gmail.automatelogging.model.GmailUser;
import com.gmail.automatelogging.model.ScrapeUserData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller

public class GmailLoginController {


    @Autowired
    private GmailDataService gmailDataService;

    @Value("${test.message}")
    private String messsage;

    @RequestMapping("/test")

    public String helloWorldTest(Map<String, Object> model) {
        model.put("message", this.messsage);
        return "test";
    }

    @RequestMapping("/login")
    public ModelAndView gmailLogin(@ModelAttribute("gmailuser") GmailUser gmailUser) {
        ModelAndView modelAndView = new ModelAndView("gmailuser");
        System.out.println("********** pringting username and password in controller" + gmailUser.toString());
        modelAndView.addObject("gmailuser", new GmailUser());
        return modelAndView;

    }

    @RequestMapping("/scrapeGmailData")
    public ModelAndView scrapeGmailData(@ModelAttribute("gmailuser") GmailUser gmailUser) throws Exception {
        ModelAndView modelAndView = new ModelAndView("gmailuser");
        System.out.println("********** pringting username and password in controller" + gmailUser.toString());
        System.out.println("------------" + gmailDataService.getScrapeData(gmailUser));
        modelAndView.addObject("gmailuser", gmailUser);
        return modelAndView;

    }
    @RequestMapping("/showScrapeData")
    public ModelAndView showScrapeData(@ModelAttribute("gmailuser") GmailUser gmailUser) throws Exception {
        ModelAndView modelAndView = new ModelAndView("scrapedata");
        System.out.println("********** Swathi in showScrapeData() printing username and password in controller" + gmailUser.toString());
        List<ScrapeUserData> scrapedata =  gmailDataService.showScrapeData(gmailUser);
        modelAndView.addObject("scrapedata", scrapedata);
        return modelAndView;
    }

}
