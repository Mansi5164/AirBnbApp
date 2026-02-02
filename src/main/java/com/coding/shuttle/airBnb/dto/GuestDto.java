package com.coding.shuttle.airBnb.dto;

import com.coding.shuttle.airBnb.entity.User;
import com.coding.shuttle.airBnb.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
