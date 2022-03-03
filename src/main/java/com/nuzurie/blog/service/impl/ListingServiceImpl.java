package com.nuzurie.blog.service.impl;

import com.nuzurie.blog.domain.Listing;
import com.nuzurie.blog.dto.ListingDTO;
import com.nuzurie.blog.exception.ResourceNotFoundException;
import com.nuzurie.blog.repository.ListingRepository;
import com.nuzurie.blog.service.ListingService;
import com.nuzurie.blog.utils.mapper.MapStructMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListingServiceImpl implements ListingService {
    private ListingRepository listingRepository;
    private MapStructMapper mapper;

    public ListingServiceImpl(ListingRepository listingRepository, MapStructMapper mapper) {
        this.listingRepository = listingRepository;
        this.mapper = mapper;
    }

    @Override
    public ListingDTO createListing(ListingDTO listingDTO) {

        Listing listing = mapper.listingDTOToListing(listingDTO);
        System.out.println(listing);
        listingRepository.save(listing);
        listingRepository.flush();
        return listingDTO;
    }

    @Override
    public List<ListingDTO> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        return mapper.listingListToListingDTOList(listings);
    }

    @Override
    public ListingDTO getListingById(UUID id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("listing", "id", id.toString()));
        return mapper.listingToListingDTO(listing);
    }

    public ListingDTO updateListing(ListingDTO listing) {
        Listing existing = listingRepository.findById(listing.getId()).orElseThrow(() ->
                new ResourceNotFoundException("listing", "id", listing.getId().toString()));
        if (listing.getPrice() == 0) { listing.setPrice(existing.getPrice()); }
        mapper.listingDTOToListing(listing, existing);
        listingRepository.saveAndFlush(existing);
        return mapper.listingToListingDTO(existing);
    }
}
