package com.uber.uberapi.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class Account extends Auditable {
    @Column(unique = true,nullable = false)
    private String username;

    private String password;

    @ManyToMany
    private List<Role> roles=new ArrayList<>();

}
