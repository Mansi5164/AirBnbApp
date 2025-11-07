package com.coding.shuttle.airBnb.dto;

import com.coding.shuttle.airBnb.entity.HotelContactInfo;
import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String[] photos;        //it's going to store the url of images it does not store the actual images , actual images are store by the third party at somewhere
    private String[] amenities;
    private HotelContactInfo hotelContactInfo;
    private Boolean active;

}
