package ru.avm.lib.common.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record CompanyDto(
        Long id,
        Long parentId,
        String name,
        String code,
        String innKpp,
        String legal,
        String projects
) {
}
