package com.nuzurie.blog.utils.mapper;

import com.nuzurie.blog.domain.Listing;
import com.nuzurie.blog.dto.ListingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MapStructMapper {
    ListingDTO listingToListingDTO(Listing listing);
    Listing listingDTOToListing(ListingDTO listingDTO);
    void listingDTOToListing(ListingDTO listingDTO, @MappingTarget Listing listing);
    List<ListingDTO> listingListToListingDTOList(List<Listing> listings);
}
