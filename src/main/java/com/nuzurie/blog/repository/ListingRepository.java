package com.nuzurie.blog.repository;

import com.nuzurie.blog.domain.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListingRepository extends JpaRepository<Listing, UUID> {
}
