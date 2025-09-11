package ru.avm.lib.common.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record ExceptionDto(
        String exception,
        String message
) {
}
