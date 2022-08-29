package com.project.prkt.repository;

import com.project.prkt.model.Ski;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiRepository extends JpaRepository<Ski, Long> {
}
