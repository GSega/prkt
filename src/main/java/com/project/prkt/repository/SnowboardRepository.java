package com.project.prkt.repository;

import com.project.prkt.model.Snowboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnowboardRepository extends JpaRepository<Snowboard, Long> {

    List<Snowboard> findAllByOrderById();

    List<Snowboard> findAllByNameContaining(String partOfName);
}
