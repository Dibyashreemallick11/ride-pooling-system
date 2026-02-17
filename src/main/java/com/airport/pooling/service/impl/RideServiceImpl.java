package com.airport.pooling.service.impl;

import com.airport.pooling.dto.RidePassengerResponseDTO;
import com.airport.pooling.dto.RideResponseDTO;
import com.airport.pooling.entity.*;
import com.airport.pooling.exception.ResourceNotFoundException;
import com.airport.pooling.repository.CabRepository;
import com.airport.pooling.repository.RidePassengerRepository;
import com.airport.pooling.repository.RideRepository;
import com.airport.pooling.repository.RideRequestRepository;
import com.airport.pooling.service.RideService;
import com.airport.pooling.util.AppConstants;
import com.airport.pooling.util.DistanceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRequestRepository rideRequestRepository;
    private final RideRepository rideRepository;
    private final CabRepository cabRepository;
    private final RidePassengerRepository ridePassengerRepository;

    @Override
    @Transactional
    public RideResponseDTO assignRide(Long rideRequestId) {

        // 1️ Fetch Ride Request
        RideRequest rideRequest = rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride request not found"));

        // 2️ Calculate passenger distance
        double passengerDistance = DistanceUtil.calculateDistance(
                rideRequest.getPickupLat(), rideRequest.getPickupLng(),
                rideRequest.getDropLat(), rideRequest.getDropLng()
        );

        // 3️ Check for active ride (Pooling)
        Optional<Ride> activeRideOpt =
                rideRepository.findFirstByStatusIn(List.of(RideStatus.ASSIGNED, RideStatus.ONGOING));

        Ride ride;

        if (activeRideOpt.isPresent()) {
            ride = activeRideOpt.get();
            int currentPassengers = ridePassengerRepository.countByRideId(ride.getId());

            // Seats full → create new ride
            if (currentPassengers >= ride.getCab().getTotalSeats()) {
                ride = createNewRide();
            }

        } else {
            ride = createNewRide();
        }

        // 4️ Calculate individual fare dynamically
        double baseFare = AppConstants.BASE_FARE;
        double ratePerKm = AppConstants.RATE_PER_KM;
        double luggageCharge = AppConstants.LUGGAGE_CHARGE;

        BigDecimal individualPrice = BigDecimal.valueOf(
                baseFare + (passengerDistance * ratePerKm) + (rideRequest.getLuggageCount() * luggageCharge)
        );

        // Apply pooling discount if ride already has passengers
        int passengersInRide = ridePassengerRepository.countByRideId(ride.getId());
        if (passengersInRide > 0) {
            individualPrice = individualPrice.multiply(BigDecimal.valueOf(1 - AppConstants.POOL_DISCOUNT));
        }

        // 5️ Add passenger to ride
        RidePassenger ridePassenger = RidePassenger.builder()
                .ride(ride)
                .passenger(rideRequest.getPassenger())
                .individualPrice(individualPrice)
                .pickupOrder(passengersInRide + 1)
                .dropOrder(passengersInRide + 1)
                .build();

        ridePassengerRepository.save(ridePassenger);

        // 6️ Update ride total price and distance
        ride.setTotalPrice(ride.getTotalPrice().add(individualPrice));
        ride.setTotalDistance(ride.getTotalDistance() + passengerDistance);

        rideRepository.save(ride);

        // 7️ Update RideRequest status
        rideRequest.setStatus(RideRequestStatus.MATCHED);
        rideRequestRepository.save(rideRequest);

        // 8️ Build response
        return buildRideResponse(ride);
    }

    @Override
    public RideResponseDTO getRide(Long rideId) {

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride not found"));

        return buildRideResponse(ride);
    }

    private Ride createNewRide() {

        Cab cab = cabRepository.findFirstByStatus(CabStatus.AVAILABLE)
                .orElseThrow(() -> new ResourceNotFoundException("No available cab found"));

        cab.setStatus(CabStatus.ON_RIDE);

        Ride ride = Ride.builder()
                .cab(cab)
                .status(RideStatus.ASSIGNED)
                .totalDistance(0.0)
                .totalPrice(BigDecimal.ZERO)
                .build();

        return rideRepository.save(ride);
    }

    private RideResponseDTO buildRideResponse(Ride ride) {

        List<RidePassengerResponseDTO> passengers =
                ridePassengerRepository.findAll().stream()
                        .filter(rp -> rp.getRide().getId().equals(ride.getId()))
                        .map(rp -> RidePassengerResponseDTO.builder()
                                .passengerId(rp.getPassenger().getId())
                                .passengerName(rp.getPassenger().getName())
                                .individualPrice(rp.getIndividualPrice())
                                .pickupOrder(rp.getPickupOrder())
                                .dropOrder(rp.getDropOrder())
                                .build())
                        .toList();

        return RideResponseDTO.builder()
                .rideId(ride.getId())
                .cabId(ride.getCab().getId())
                .driverName(ride.getCab().getDriverName())
                .status(ride.getStatus().name())
                .totalDistance(ride.getTotalDistance())
                .totalPrice(ride.getTotalPrice().doubleValue())
                .passengers(passengers)
                .build();
    }
}
