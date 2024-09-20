package com.ing.ls.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseStatus implements Serializable {

    private String StatusCode;
    private String StatusText;
    private String Message;
}
