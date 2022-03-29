package com.nuzurie.realestate.assembler;

import com.nuzurie.realestate.controller.ListingController;
import com.nuzurie.realestate.domain.Listing;
import com.nuzurie.realestate.dto.ListingDTO;
import com.nuzurie.realestate.dto.ListingDetailDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ListingAssembler implements RepresentationModelAssembler<ListingDTO, EntityModel<ListingDTO>> {

    @Override
    public EntityModel<ListingDTO> toModel(ListingDTO listing) {
        return EntityModel.of(listing,
                linkTo(methodOn(ListingController.class).getById(listing.getId())).withSelfRel(),
                linkTo(methodOn(ListingController.class).getListings()).withRel("listings"));
    }

    @Override
    public CollectionModel<EntityModel<ListingDTO>> toCollectionModel(Iterable<? extends ListingDTO> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
