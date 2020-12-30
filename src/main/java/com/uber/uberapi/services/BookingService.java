package com.uber.uberapi.services;

import com.uber.uberapi.models.Booking;
import com.uber.uberapi.models.Driver;

public interface BookingService {

    void createBooking(Booking booking);

    void acceptBooking(Driver driver, Booking booking);

    void cancelByDriver(Driver driver, Booking booking);

    void cancelByPassenger(Driver driver, Booking booking);


}