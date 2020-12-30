package com.uber.uberapi.models;

import com.uber.uberapi.exceptions.InvalidOTPException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="otp")
public class OTP extends Auditable{
    private String code;
    private String sentToNumber;


    public boolean validateEnteredOTP(OTP otp,Long expiryMinutes) {
        if(!code.equals(otp.getCode())){
            return false;
        }
        // if the created at + exporytime> currenttime,then it is valid
        // otherwise return false

        return true;
    }
}
