package com.nuzurie.blog.service;

import com.nuzurie.blog.dto.ListingDTO;

import java.util.List;
import java.util.UUID;

public interface ListingService {
    ListingDTO createListing(ListingDTO listingDTO);
    List<ListingDTO> getAllListings();
    ListingDTO getListingById(UUID id);
    ListingDTO updateListing(ListingDTO listingDTO);
}
