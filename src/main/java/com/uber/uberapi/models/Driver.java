package com.uber.uberapi.models;

import com.uber.uberapi.exceptions.UnapprovedDriverException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="driver")
public class Driver extends Auditable{

    @OneToOne
    private Account account;

    private Gender gender;

    private String name;

    @OneToOne(mappedBy = "driver")
    private Car car;

    private String licenseDetails;

    @Temporal(value= TemporalType.DATE)
    private Date date;

    @Enumerated(value=EnumType.STRING)
    private DriverApprovalStatus approvalStatus;

    @OneToMany(mappedBy = "driver")
    private List<Booking> bookings;

    private Boolean isAvailable;

    private String activeCity;

    @OneToOne
    private ExactLocation lastKnownLocation;

    @OneToOne
    private ExactLocation home;


    public void setAvailable(Boolean available) {
        if(available && !approvalStatus.equals(DriverApprovalStatus.APPROVED)){
            throw new UnapprovedDriverException("Driver approval pending or denied " +getId());
        }
//        driver.setIsAvailable(available);
        isAvailable = available;
    }
}
