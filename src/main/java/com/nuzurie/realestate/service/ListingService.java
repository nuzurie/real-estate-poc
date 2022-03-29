package com.nuzurie.realestate.service;

import com.nuzurie.realestate.dto.ListingDTO;
import com.nuzurie.realestate.dto.ListingDetailDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ListingService {
    ListingDetailDTO createListing(ListingDetailDTO listingDTO);
    List<ListingDTO> getAllListings();
    ListingDetailDTO getListingById(UUID id);
    ListingDTO updateListing(ListingDetailDTO listingDTO);
    void uploadImage(UUID id, MultipartFile file);
}
