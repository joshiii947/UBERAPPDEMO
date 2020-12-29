package com.uber.uberapi.controllers;

import com.uber.uberapi.exceptions.InvalidBookingException;
import com.uber.uberapi.exceptions.InvalidDriverException;
import com.uber.uberapi.models.Booking;
import com.uber.uberapi.models.Driver;
import com.uber.uberapi.repositories.BookingRepository;
import com.uber.uberapi.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/driver")
@RestController
public class DriverController {
    @Autowired
    DriverRepository driverRepository;
    // all endpoint that  a driver use

    @Autowired
    BookingRepository bookingRepository;

    public Driver getDriverFromId(Long driverId){
        Optional<Driver> driver=driverRepository.findById(driverId);
        if(driver.isEmpty()){
            throw new InvalidDriverException("No driver Id");
        }
        return driver.get();
    }

    public Booking getDriverBookingFromId(Long bookingId,Driver driver){
        Optional<Booking> optionalBooking=bookingRepository.findById(bookingId);
        if(optionalBooking.isEmpty()){
            throw new InvalidBookingException("No suh booking Id");
        }
        Booking booking=optionalBooking.get();
        if(booking.getDriver().equals(driver)){
            throw new InvalidBookingException("Driver "+driver.getBookings()+"has no such booking"+bookingId);
        }
        return booking;
    }

    @GetMapping("/{driverId}")
    public Driver getDriverDetails(@RequestParam(name="driverId") Long driverId){
        return  getDriverFromId(driverId);
    }

    @PatchMapping("/{driverId}")
    public void changeAvailablity(@RequestParam(name="driverId") Long driverId, @RequestBody Boolean available){
        Driver driver=getDriverFromId(driverId);
        driver.setIsAvailable(available);
        driverRepository.save(driver);
    }


    @GetMapping("{driverId}/bookings")
    public List<Booking> getAllBookings(@RequestParam(name="driverId") Long driverId){
        Driver driver=getDriverFromId(driverId);
        return driver.getBookings();
    }

    @GetMapping("{driverId}/bookings/{bookingId}")
    public Booking getBooking(@RequestParam(name="driverId") Long driverId,
                              @RequestParam(name="bookingId") Long bookingId){
        Driver driver=getDriverFromId(driverId);
        Booking booking=getDriverBookingFromId(bookingId,driver);
        return booking;
    }

    @PostMapping("{driverId}/bookings/{bookingId}")
    public void acceptBooking(@RequestParam(name="driverId") Long driverId,
                              @RequestParam(name="bookingId") Long bookingId){
        Driver driver=getDriverFromId(driverId);
        Booking booking=getDriverBookingFromId(bookingId,driver);


    }

    @DeleteMapping("{driverId}/bookings/{bookingId}")
    public void cancelBooking(@RequestParam(name="driverId") Long driverId,
                              @RequestParam(name="bookingId") Long bookingId){


    }



}
