package com.uber.uberapi.controllers;

import com.uber.uberapi.repositories.AccountRepository;
import com.uber.uberapi.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DemoController implements CommandLineRunner {
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        accountRepository.findFirstByUsername("Max");
        driverRepository.findFirstByAccount_Username("skjgg");
        System.out.println("Demo controller is being run");
    }

}
