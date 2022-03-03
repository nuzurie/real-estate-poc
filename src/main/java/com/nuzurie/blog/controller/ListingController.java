package com.nuzurie.blog.controller;

import com.nuzurie.blog.dto.ListingDTO;
import com.nuzurie.blog.service.ListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    private ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping
    public ResponseEntity<ListingDTO> createListing(@RequestBody ListingDTO listing) {
        return new ResponseEntity<>(listingService.createListing(listing), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListingDTO>> getListings() {
        return new ResponseEntity<>(listingService.getAllListings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingDTO> getById(@PathVariable(name="id") UUID id) {
        return ResponseEntity.ok(listingService.getListingById(id));
    }

    @PutMapping
    public ResponseEntity<ListingDTO> updateListing(@RequestBody ListingDTO listingDTO) {
        return ResponseEntity.accepted().body(listingService.updateListing(listingDTO));
    }
}
