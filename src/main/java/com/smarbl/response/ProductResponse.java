package com.smarbl.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private Integer id;
    private String name;
    private double price;
    private int quantity;
    private String description;
}
