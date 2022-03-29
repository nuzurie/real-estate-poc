package com.nuzurie.realestate.dto;

import com.nuzurie.realestate.domain.House;
import com.nuzurie.realestate.domain.Listing;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ListingDTO {
    protected UUID id;
    protected House house;
    protected int price;
    protected Listing.ListingType listingType;
    protected String imageUrl;
}
