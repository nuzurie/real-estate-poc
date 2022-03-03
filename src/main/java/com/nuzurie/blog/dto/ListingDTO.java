package com.nuzurie.blog.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ListingDTO {
    @NotNull
    private UUID id;
    private String title;
    private String description;
    private String address;
//    private House house;
    private int price;
}
