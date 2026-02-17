package com.airport.pooling.repository;

import com.airport.pooling.entity.Ride;
import com.airport.pooling.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Long> {

    Optional<Ride> findFirstByStatusIn(List<RideStatus> statuses);
}

