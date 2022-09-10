package com.project.prkt.repository;

import com.project.prkt.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Sergei Gavrilov
 */
@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

    List<Rider> findAllByOrderById();



}
