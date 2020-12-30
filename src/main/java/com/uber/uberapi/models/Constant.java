package com.uber.uberapi.models;

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
@Table(name="constant")
public class Constant extends Auditable{
    private String name;
    private String value;

    public Long getAsLong(){
        return Long.parseLong(value);
    }


}
