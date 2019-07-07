package com.gmail.automatelogging.repository;

import com.gmail.automatelogging.model.ScrapeData;
import com.gmail.automatelogging.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapeRepository extends JpaRepository<ScrapeData, Long> {

  List<ScrapeData> findAllById(Long userId);
}
