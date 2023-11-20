package com.smarbl.request;

import lombok.Data;

@Data
public class ProductRequest {
    private Integer id;
    private String name;
    private double price;
    private int quantity;
    private String description;

}
