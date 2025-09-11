package ru.avm.lib.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.security.core.GrantedAuthority;

@Builder
@Jacksonized
public class AuthorityDto implements GrantedAuthority {
    @Getter
    private final String authority;
}
