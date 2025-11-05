package ru.avm.lib.common.dto;

import lombok.Builder;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;

@Builder(toBuilder = true)
@Jacksonized
public record AuthUserDto(
        Long id,
        String sid,
        String name,
        String tag,
        String phone,
        String email,
        @Singular("authority")
        Collection<String> authorities
) {
}
