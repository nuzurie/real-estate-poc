package com.nuzurie.realestate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class House {
    public enum Province {
        ALBERTA, BRITISH_COLUMBIA, MANITOBA, NEW_BRUNSWICK, NEWFOUNDLAND_AND_LABRADOR, NORTHWEST_TERRITORIES, NOVA_SCOTIA,
        NUNAVUT, ONTARIO, QUEBEC, PRINCE_EDWARD_ISLAND, SASKATCHEWAN, YUKON
    }
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private int rooms;
    private int bathrooms;

    @Column(name="civic_address", nullable = false)
    private String civicAddress;
    private String area;
    private String city;
    @Enumerated(EnumType.STRING)
    private Province province;
    @Column(name="postal_code")
    private String postalCode;

    @OneToOne(mappedBy = "house")
    @JsonIgnore
    private Listing listing;
}
