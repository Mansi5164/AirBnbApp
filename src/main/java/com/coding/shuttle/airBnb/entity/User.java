package com.coding.shuttle.airBnb.entity;

import com.coding.shuttle.airBnb.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)        // it means ye constraints add krdega ki user sirf vahi roles add krr paye jo hmne role me add kiye hai he do not add anything else
    private Set<Role> roles;

}
