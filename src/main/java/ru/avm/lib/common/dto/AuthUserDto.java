package ru.avm.lib.common.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
@Jacksonized
public record AuthUserDto(
        Long id,
        String sid,
        String name,
        String tag,
        String phone,
        String email,
        Collection<? extends GrantedAuthority> authorities
) {
}
