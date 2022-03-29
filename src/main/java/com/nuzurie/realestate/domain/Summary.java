package com.nuzurie.realestate.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
    public enum BuildingType {
        CONDO, OFFICE, APARTMENT
    }
    public enum PropertyType {
        SINGLE_FAMILY, MULTIPLE_FAMILY
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Min(1900)
    @Max(2021) //todo: create custom constraint
    private int builtYear;

    private int storeys;

    @JsonAlias("neighbourhood_name")
    @Column(name="neighbourhood_name")
    private String neighbourhoodName;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @JsonIgnore
    @OneToOne(mappedBy = "summary")
    private Listing listing;
}
