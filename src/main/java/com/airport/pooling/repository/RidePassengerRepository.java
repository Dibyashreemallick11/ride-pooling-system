package com.airport.pooling.repository;

import com.airport.pooling.entity.RidePassenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RidePassengerRepository extends JpaRepository<RidePassenger, Long> {

    int countByRideId(Long rideId);
}
