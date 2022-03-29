package com.nuzurie.realestate.bucket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Bucket {
    LISTING_IMAGE("zubair-real-estate-images");

    private final String bucketName;
}
