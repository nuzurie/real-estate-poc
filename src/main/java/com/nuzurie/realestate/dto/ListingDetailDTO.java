package com.nuzurie.realestate.dto;

import com.nuzurie.realestate.domain.House;
import com.nuzurie.realestate.domain.Listing;
import com.nuzurie.realestate.domain.Summary;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListingDetailDTO extends ListingDTO {
    protected UUID id;
    protected House house;
    protected int price;
    protected Listing.ListingType listingType;
    protected String imageUrl;
    @NonNull
    private Summary summary;
    @NonNull
    private String description;
}
