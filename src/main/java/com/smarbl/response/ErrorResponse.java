package com.smarbl.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smarbl.api.BaseResponse;
import lombok.Data;

@Data
public class ErrorResponse extends BaseResponse {
    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorReason")
    private String errorReason;


    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorReason='" + errorReason + '\'' +
                '}';
    }
}
