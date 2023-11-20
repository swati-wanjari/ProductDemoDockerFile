package com.smarbl.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class BaseResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;


}