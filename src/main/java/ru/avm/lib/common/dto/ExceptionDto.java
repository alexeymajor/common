package ru.avm.lib.common.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@ToString
@Value
@Builder
@Jacksonized
public class ExceptionDto {
    String source;
    String exception;
    String message;
    String uri;
    Integer status;
}
