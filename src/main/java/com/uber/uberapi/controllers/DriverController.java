package com.uber.uberapi.controllers;

import com.uber.uberapi.exceptions.NoSuchDriverException;
import com.uber.uberapi.exceptions.UnapprovedDriverException;
import com.uber.uberapi.models.Driver;
import com.uber.uberapi.models.DriverApprovalStatus;
import com.uber.uberapi.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/driver")
@RestController
public class DriverController {
    @Autowired
    DriverRepository driverRepository;
    // all endpoint that  a driver use

    public Driver getDriverFromId(Long driverId){
        Optional<Driver> driver=driverRepository.findById(driverId);
        if(driver.isEmpty()){
            throw new NoSuchDriverException("No driver Id");
        }
        return driver.get();
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


    public void acceptBooking(@RequestParam(name="driverId") Long driverId,@RequestParam(name="bookingId") Long bookingId){
        
    }

}
