package com.fooddelivery.response;

import java.util.List;

import lombok.Data;

@Data
public class ReciepeListResponse {
    private String responseMessage;
    private String responseStatus;
    private List<ReciepeResponse> reciepeList;

    public ReciepeListResponse(String responseMessage, String responseStatus, List<ReciepeResponse> reciepeList) {
        this.responseMessage = responseMessage;
        this.responseStatus = responseStatus;
        this.reciepeList = reciepeList;
    }

}
