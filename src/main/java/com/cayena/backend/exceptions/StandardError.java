package com.cayena.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
public class StandardError{
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
