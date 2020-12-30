package com.uber.uberapi.controllers;

import com.uber.uberapi.exceptions.InvalidBookingException;
import com.uber.uberapi.exceptions.InvalidPassengerException;
import com.uber.uberapi.models.*;
import com.uber.uberapi.repositories.BookingRepository;
import com.uber.uberapi.repositories.PassengerRepository;
import com.uber.uberapi.repositories.ReviewRepository;
import com.uber.uberapi.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/passenger")
@RestController
public class PassengerController {
    @Autowired
    PassengerRepository passengerRepository;
    // all endpoint that  a driver use

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingService bookingService   ;

    @Autowired
    ReviewRepository reviewRepository;
;

    public Passenger getPassengerFromId(Long passengerId){
        Optional<Passenger> passenger=passengerRepository.findById(passengerId);
        if(passenger.isEmpty()){
            throw new InvalidPassengerException("No driver Id");
        }
        return passenger.get();
    }

    public Booking getPassengerBookingFromId(Long bookingId, Passenger passenger){
        Optional<Booking> optionalBooking=bookingRepository.findById(bookingId);
        if(optionalBooking.isEmpty()){
            throw new InvalidBookingException("No suh booking Id");
        }
        Booking booking=optionalBooking.get();
        if(booking.getPassenger().equals(passenger)){
            throw new InvalidBookingException("Driver "+passenger.getBookings()+"has no such booking"+bookingId);
        }
        return booking;
    }

    @GetMapping("/{passengerId}")
    public Passenger getPassengerDetails(@RequestParam(name="passengerId") Long passengerId){
        return getPassengerFromId(passengerId);
    }



    @GetMapping("{passengerId}/bookings")
    public List<Booking> getAllBookings(@RequestParam(name="passengerId") Long passengerId){
        Passenger passenger=getPassengerFromId(passengerId);
        return passenger.getBookings();
    }

    @GetMapping("{driverId}/bookings/{bookingId}")
    public Booking getBooking(@RequestParam(name="passengerId") Long passengerId,
                              @RequestParam(name="bookingId") Long bookingId){
        Passenger passenger=getPassengerFromId(passengerId);
        Booking booking=getPassengerBookingFromId(bookingId,passenger);
        return booking;
    }

    @PostMapping("{driverId}/bookings")
    public void requestBooking(@RequestParam(name="passengerId") Long passengerId,
                              @RequestBody Booking data){
        Passenger passenger=getPassengerFromId(passengerId);
        Booking booking=Booking.builder()
                .build();
        bookingService.createBooking(booking);
        bookingRepository.save(booking);
        passengerRepository.save(passenger);
        //
    }

    @DeleteMapping("{driverId}/bookings/{bookingId}")
    public void cancelBooking(@RequestParam(name="passengerId") Long passengerId,
                              @RequestParam(name="bookingId") Long bookingId){
        Passenger passenger=getPassengerFromId(passengerId);
        Booking booking=getPassengerBookingFromId(bookingId,passenger);
        bookingService.cancelByPassenger(passenger, booking);
    }

    // rate the booking
    // start the ride
    // end the ride



    @PatchMapping("{driverId}/bookings/{bookingId}/rate")
    public void rateRide(@RequestParam(name="passengerId") Long passengerId,
                         @RequestParam(name="bookingId") Long bookingId,
                         @RequestBody Review data){
        // get json data in the body
//        reviewRepository.save(data);

        Passenger passenger=getPassengerFromId(passengerId);
        Booking booking=getPassengerBookingFromId(bookingId,passenger);
        // confirm the OTP
        // the ride is currently in the correct state
        Review review=Review.builder()
                .note(data.getNote())
                .ratingOutOfFive(data.getRatingOutOfFive())
                .build();
        booking.setReviewByPassenger(review);
        reviewRepository.save(review);
        bookingRepository.save(booking);
    }


}
