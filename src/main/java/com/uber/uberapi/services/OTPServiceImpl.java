package com.uber.uberapi.services;

import com.uber.uberapi.models.Booking;
import com.uber.uberapi.models.Driver;
import com.uber.uberapi.models.OTP;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl implements OTPService {
    @Override
    public void sendPhoneNumberConfirmationOTP(Driver driver, Booking booking) {

    }

    @Override
    public void sendRideStartOTP(OTP otp) {

    }
}
