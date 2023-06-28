package ru.avm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class CompanyDto {
    Long id;
    Long parentId;
    String name;
    String code;
    String innKpp;
    String legal;
}
