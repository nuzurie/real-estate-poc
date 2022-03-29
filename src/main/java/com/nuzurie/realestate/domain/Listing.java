package com.nuzurie.realestate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nuzurie.realestate.domain.House;
import com.nuzurie.realestate.domain.Summary;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="listing")
public class Listing {
    public enum ListingType {
        FOR_SALE, FOR_RENT
    }
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name="summary_id", referencedColumnName = "id")
    private Summary summary;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name="house_id", referencedColumnName = "id")
    private House house;


    @Column(name="price", nullable = false)
    @Min(0)
    private int price;

    private String description;

    @Enumerated(EnumType.STRING)
    private ListingType listingType;

    @Nullable
    private String imageUrl;
}
