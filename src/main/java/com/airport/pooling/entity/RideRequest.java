package com.airport.pooling.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ride_requests",
        indexes = {
                @Index(name = "idx_request_status", columnList = "status")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @Column(nullable = false)
    private Double pickupLat;

    @Column(nullable = false)
    private Double pickupLng;

    @Column(nullable = false)
    private Double dropLat;

    @Column(nullable = false)
    private Double dropLng;

    @Column(nullable = false)
    private Integer luggageCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideRequestStatus status;
}
