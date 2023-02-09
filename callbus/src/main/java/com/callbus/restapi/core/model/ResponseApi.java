package com.callbus.restapi.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseApi {

    private int responseCode;
    private String responseMessage;
    private Object responseData;

}
