package com.nuzurie.realestate.utils.mapper;

import com.nuzurie.realestate.domain.Listing;
import com.nuzurie.realestate.dto.ListingDTO;
import com.nuzurie.realestate.dto.ListingDetailDTO;
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
    Listing listingDetailDTOToListing(ListingDetailDTO listingDTO);
    ListingDetailDTO listingToListingDetailDTO(Listing listing);
    void listingDetailDTOToListing(ListingDetailDTO listingDTO, @MappingTarget Listing listing);
//    List<ListingDTO> listingListToListingDTOList(List<Listing> listings);
}
