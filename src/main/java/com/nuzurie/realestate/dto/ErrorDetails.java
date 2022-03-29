package com.nuzurie.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ErrorDetails {
    private Date date;
    private String error;
    private String request;
}
