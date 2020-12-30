package com.uber.uberapi.models;

import lombok.Getter;

@Getter
public enum BookingStatus {
    ASSINGNING_DRIVER("The passenger has requested a booking but a driver has yet to be assigned"),
    CAB_ARRIVED("The driver has arrived at pickup location and has arrived"),
    CANCELLED("The booking has been cancelled due to one of many reasons"),
    COMPLETED("The ride has been completed"),
    IN_RIDE("The ride is currently in progress"),
    REACHING_PICKUP_LOCATION("The driver has been assigned and is on the way"),
    SCHEDULED("The booking is scheduled some time in future");


    private final String description;

    BookingStatus(String description) {
        this.description = description;
    }
    }
