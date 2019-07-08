package com.gmail.automatelogging.repository;

import com.gmail.automatelogging.model.ScrapeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScrapeDataRepository extends JpaRepository<ScrapeData, Long> {
}
