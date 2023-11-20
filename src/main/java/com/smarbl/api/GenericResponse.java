package com.smarbl.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class GenericResponse extends BaseResponse {

    @JsonProperty("total")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;

    @JsonProperty("errorMessage")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ErrorMessage;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonProperty("totalPages")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;

    @JsonProperty("pageNum")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageNum;

    @Override
    public String toString() {
        return "GenericResponse{" +
                "total=" + total +
                ", data=" + data +
                ", totalPages=" + totalPages +
                ", pageNum=" + pageNum +
                '}';
    }
}
