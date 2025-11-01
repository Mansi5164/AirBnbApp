package com.coding.shuttle.airBnb.App.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class HotelContactInfo {
    private String address;
    private String phoneNumber;

}
