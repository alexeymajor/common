package ru.avm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class ExceptionDto {
    String exception;
    String message;
}
