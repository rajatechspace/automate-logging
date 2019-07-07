package com.gmail.automatelogging.repository;

import com.gmail.automatelogging.model.ScrapeData;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScrapeRepositoryImpl {

  @Autowired
  private ScrapeRepository scrapeRepository;

  public List<ScrapeData> getScrapeData() {
    List<ScrapeData> scrapeUserDataList = scrapeRepository.findAll();
    return scrapeUserDataList;
  }
}
