package com.dmp.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ServiceResponse {
    private int id;
    private String name;
    private double price;
    private String note;
}