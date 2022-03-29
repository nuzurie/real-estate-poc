package com.nuzurie.realestate.controller;

import com.nuzurie.realestate.assembler.ListingAssembler;
import com.nuzurie.realestate.dto.ListingDTO;
import com.nuzurie.realestate.dto.ListingDetailDTO;
import com.nuzurie.realestate.service.ListingService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/listings")
@CrossOrigin(origins = "*")
public class ListingController {

    private ListingService listingService;
    private ListingAssembler listingAssembler;
    private Logger logger = LoggerFactory.getLogger(ListingController.class);

    public ListingController(ListingService listingService, ListingAssembler listingAssembler) {
        this.listingService = listingService;
        this.listingAssembler = listingAssembler;
    }

    @PostMapping
    public ResponseEntity<?> createListing(@RequestBody ListingDetailDTO listingDTO) {

        EntityModel<ListingDTO> listing = listingAssembler.toModel(listingService.createListing(listingDTO));
        return new ResponseEntity<>(listing, HttpStatus.CREATED);
    }

    @PostMapping(
            path = "{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> uploadImage(@PathVariable UUID id,
                                         @RequestParam MultipartFile file) {
        listingService.uploadImage(id, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ListingDTO>>> getListings() {
        List<EntityModel<ListingDTO>> listings = listingService.getAllListings()
                                                        .stream()
                                                        .map(listingAssembler::toModel)
                                                        .collect(Collectors.toList());
        CollectionModel<EntityModel<ListingDTO>> collection = CollectionModel.of(listings, linkTo(methodOn(ListingController.class).getListings()).withSelfRel());
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ListingDTO>> getById(@PathVariable(name="id") UUID id) {
        EntityModel<ListingDTO> listing = listingAssembler.toModel(listingService.getListingById(id));
        return ResponseEntity.ok(listing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ListingDTO>> updateListing(@PathVariable(name="id") UUID id, @RequestBody ListingDetailDTO listingDTO) {
        listingDTO.setId(id);
        EntityModel<ListingDTO> listing = listingAssembler.toModel(listingService.updateListing(listingDTO));
        return ResponseEntity.accepted().body(listing);
    }
}
