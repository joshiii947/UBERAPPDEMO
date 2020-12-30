package com.uber.uberapi.models;

import com.uber.uberapi.exceptions.InvalidActionForBookingStateException;
import com.uber.uberapi.exceptions.InvalidBookingException;
import com.uber.uberapi.exceptions.InvalidOTPException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.uber.uberapi.models.constants.RIDE_START_OTP_EXPIRY_MINUTES;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Review reviewByPassenger;

    @OneToOne
    private Review reviewByDriver;

    @OneToOne
    private PaymentReceipt paymentReceipt;
    
    @OneToMany
    @JoinTable(
            name="booking_route",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "exact_location_id"),
            indexes = {@Index(columnList = "booking_id")}
    )
    @OrderColumn(name = "location_index")
    private List<ExactLocation> route=new ArrayList<>();
    // ordered list
    // start location
    // next location
    

    @Temporal(value = TemporalType.DATE)
    private Date startTime;

    @Temporal(value = TemporalType.DATE)
    private Date endTime;

    private Long totalDistanceMeters;

    @OneToOne
    private OTP rideStartOTP;

    public void startRide(OTP otp) {

        if(bookingStatus.equals(BookingStatus.CAB_ARRIVED)){
            throw new InvalidActionForBookingStateException("Cannot start the ride before the driver has reached the pickup");
        }
        if(!rideStartOTP.validateEnteredOTP(otp,RIDE_START_OTP_EXPIRY_MINUTES))
            throw new InvalidOTPException();

        bookingStatus=bookingStatus.IN_RIDE;
    }

    public void endRide() {
        if(bookingStatus.equals(BookingStatus.CAB_ARRIVED)){
            throw new InvalidActionForBookingStateException("THE RIDE HAS NOT STARTED YET");
        }

        bookingStatus=BookingStatus.COMPLETED;
    }
}
