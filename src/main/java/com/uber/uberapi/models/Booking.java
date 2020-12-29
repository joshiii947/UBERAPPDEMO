package com.uber.uberapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="booking",indexes = {
        @Index(columnList = "passenger_id"),
        @Index(columnList = "driver_id")
})
public class Booking extends Auditable {
    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Driver driver;

    @Enumerated(value= EnumType.STRING)
    private BookingType bookingType;

    private BookingStatus bookingStatus;

    @OneToOne
    private Review reviewByUser;

    @OneToOne
    private Review reviewByDriver;

    @OneToOne
    private PaymentReceipt paymentReceipt;

    @OneToMany
    private List<ExactLocation> route=new ArrayList<>();

    @Temporal(value = TemporalType.DATE)
    private Date startTime;

    @Temporal(value = TemporalType.DATE)
    private Date endTime;

    private Long totalDistanceMeters;

    @OneToOne
    private OTP rideStartOTP;

}
