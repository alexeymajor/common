package ru.avm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor
@Builder
public class AuthorityDto implements Serializable {
    String authority;
}
