package com.pry20220262.augmentedanatomy.exception;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private int codError;

    private String message;

    private Date timestamp;

    List<String> details;

    ErrorResponse(String message) {
        this.message = message;
    }



}