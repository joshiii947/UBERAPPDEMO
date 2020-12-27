package com.uber.uberapi.repositories;

import com.uber.uberapi.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {

}
