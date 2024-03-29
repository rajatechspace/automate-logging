package com.gmail.automatelogging.repository;

import com.gmail.automatelogging.model.ScrapeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapeRepository extends JpaRepository<ScrapeData, Long> {

    List<ScrapeData> findAllById(Long userId);


}
