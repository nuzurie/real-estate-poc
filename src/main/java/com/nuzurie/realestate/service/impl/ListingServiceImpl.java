package com.nuzurie.realestate.service.impl;

import com.nuzurie.realestate.bucket.Bucket;
import com.nuzurie.realestate.domain.Listing;
import com.nuzurie.realestate.dto.ListingDTO;
import com.nuzurie.realestate.dto.ListingDetailDTO;
import com.nuzurie.realestate.exception.ResourceNotFoundException;
import com.nuzurie.realestate.repository.ListingRepository;
import com.nuzurie.realestate.service.ListingService;
import com.nuzurie.realestate.utils.mapper.MapStructMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.apache.http.entity.ContentType.*;

@Slf4j
@Service
public class ListingServiceImpl implements ListingService {
    private ListingRepository listingRepository;
    private FileStore fileStore;
    private MapStructMapper mapper;

    public ListingServiceImpl(ListingRepository listingRepository, MapStructMapper mapper, FileStore fileStore) {
        this.listingRepository = listingRepository;
        this.mapper = mapper;
        this.fileStore = fileStore;
    }

    @Override
    public ListingDetailDTO createListing(ListingDetailDTO listingDTO) {
        Listing listing = mapper.listingDetailDTOToListing(listingDTO);
        return mapper.listingToListingDetailDTO(listingRepository.save(listing));
    }

    @Override
    public List<ListingDTO> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        return listings.stream().map(listing -> mapper.listingToListingDTO(listing))
                .collect(Collectors.toList());
    }

    @Override
    public ListingDetailDTO getListingById(UUID id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("listing", "id", id.toString()));
        return mapper.listingToListingDetailDTO(listing);
    }


    public ListingDetailDTO updateListing(ListingDetailDTO listing) {
        Listing existing = listingRepository.findById(listing.getId()).orElseThrow(() ->
                new ResourceNotFoundException("listing", "id", listing.getId().toString()));
        mapper.listingDetailDTOToListing(listing, existing);
        listingRepository.saveAndFlush(existing);
        return mapper.listingToListingDetailDTO(existing);
    }

    @Override
    public void uploadImage(UUID id, MultipartFile file) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No such listing found"));

        ensureFileIsValid(file);

        String path = String.format("%s/%s", Bucket.LISTING_IMAGE.getBucketName(), id);
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, fileName, Optional.empty(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = fileStore.getUrl(String.format("%s/%s", id, fileName));
        log.info(url);
        listing.setImageUrl(url);
        listingRepository.save(listing);
    }

    private void ensureFileIsValid(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalStateException("File not found");
        }

        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType())
                .contains(file.getContentType())) {
            throw new IllegalStateException("Only jpeg, png and gif files can uploaded, not " + file.getContentType() + ".");
        }
    }
}
