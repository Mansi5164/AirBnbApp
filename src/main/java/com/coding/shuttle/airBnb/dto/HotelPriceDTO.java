package com.coding.shuttle.airBnb.dto;

import com.coding.shuttle.airBnb.entity.Hotel;
import lombok.Data;

@Data
public class HotelPriceDTO {

    private Hotel hotel;
    private Double price;

    public HotelPriceDTO(Hotel hotel, Double price) {
        this.hotel = hotel;
        this.price = price;
    }
}
