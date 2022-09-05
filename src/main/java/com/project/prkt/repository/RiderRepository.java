package com.project.prkt.repository;

import com.project.prkt.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

    public List<Rider> findAllByOrderById();



}
