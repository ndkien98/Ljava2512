package com.t3h.uniqlo.model.response;

import lombok.Data;

@Data
public class BaseResponse <T>{

    private T data;
    private String message;
    private String code;
    private Integer totalPages;
    private Integer pageSize;
    private Integer currentPage;


}
