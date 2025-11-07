package com.coding.shuttle.airBnb.entity;

import com.coding.shuttle.airBnb.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

}
