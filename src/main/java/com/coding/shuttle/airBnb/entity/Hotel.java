package com.coding.shuttle.airBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String city;

    @Column(columnDefinition = "TEXT[]")
    private String[] photos;        //it's going to store the url of images it does not store the actual images , actual images are store by the third party at somewhere

    @Column(columnDefinition = "TEXT[]")
    private String[] amenities;     //it contains what things are avaible in our hotel like wifi, swmimming pool, which type of rooms
    /*
    🧠 Java vs PostgreSQL mapping ka concept:
        🔹 Java side:
        private String[] amenities;
        Ye Java compiler ko batata hai:
        “Yeh field ek String type ka array hai.”
        Matlab: Tum Java object me ["Wifi", "Parking", "Pool"] jaisi values rakh sakti ho.
        But Java ko database ke internal column structure se koi matlab nahi hota —
        wo bas datatype janta hai.

        🔹 PostgreSQL side:
        PostgreSQL ek relational database hai, jisme tables aur columns hote hain.
        Usko yeh samajhna padta hai:
                “is column me kis tarah ka data store hoga — ek single text ya ek array of text?”
        Agar tum sirf String rakhti ho → TEXT ya VARCHAR
        Agar tum String[] rakhti ho → TEXT[] ✅ (array of text)
                Lekin Hibernate automatically nahi samajhta ki Java ka String[] → PostgreSQL ka TEXT[]
        (isliye manually specify karna padta hai).

        ⚙️ Isliye hum likhte hain:
        @Column(columnDefinition = "TEXT[]")
        private String[] amenities;
        Yeh line Hibernate ko bridge ki tarah guide karti hai
        “Java me String[] hai → PostgreSQL me TEXT[] column banao.”
     */

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    private HotelContactInfo hotelContactInfo;
    /*
    In our table we have field like this :
        hotelContactInfo_phoneNumber
        hotelContactInfo_address
     */

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<Room> rooms;
}
