package com.nuzurie.realestate.repository;

import com.nuzurie.realestate.domain.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ListingRepository extends JpaRepository<Listing, UUID> {
}
