package com.uber.uberapi.services;

import com.uber.uberapi.models.Booking;
import com.uber.uberapi.models.BookingStatus;
import com.uber.uberapi.models.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class DefaultBookingService implements BookingService {
    @Autowired
    DriverMatchingService driverMatchingService;
    @Autowired
    OTPService otpService;

    @Override
    public void createBooking(Booking booking) {
        if(booking.getStartTime().after(new Date())){
            booking.setBookingStatus(BookingStatus.SCHEDULED);
        }else{
          booking.setBookingStatus(BookingStatus.ASSINGNING_DRIVER);
          otpService.sendRideStartOTP(booking.getRideStartOTP());
            {
                // use a task queue to push this task
                driverMatchingService.assignDriver(booking);
            }
        }

    }

    @Override
    public void acceptBooking(Driver driver, Booking booking) {

    }

    @Override
    public void cancelByDriver(Driver driver, Booking booking) {

    }

    @Override
    public void cancelByPassenger(Driver driver, Booking booking) {

    }
}


// 1:34 minutes