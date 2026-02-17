package com.airport.pooling.repository;

import com.airport.pooling.entity.RideRequest;
import com.airport.pooling.entity.RideRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {

    List<RideRequest> findByStatus(RideRequestStatus status);
}
