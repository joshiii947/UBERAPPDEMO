package com.uber.uberapi.models;

import lombok.*;

import javax.persistence.*;
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
            //  todo (fetch= FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();

}
