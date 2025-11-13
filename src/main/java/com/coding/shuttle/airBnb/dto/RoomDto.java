package com.coding.shuttle.airBnb.dto;

import java.math.BigDecimal;

public class RoomDto {

    private Long id;
    private String type;
    private BigDecimal basePrice;
    private String[] amenities;
    private String[] photos;
    private Integer totalCount;     //total number of rooms of this type
    private Integer capacity;       //number of things which are available in room

}
