package com.callbus.restapi.core.model;

import lombok.Data;

@Data
public class ResponseApi {

    private int responseCode;
    private String responseMessage;
    private Object responseData;

}
