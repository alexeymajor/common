package ru.avm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
@AllArgsConstructor
public class AuthUserDto {
    Long id;
    String sid;
    String name;
    String phone;
    String email;
    Collection<AuthorityDto> authorities;
}
