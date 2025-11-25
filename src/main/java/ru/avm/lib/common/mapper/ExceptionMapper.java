package ru.avm.lib.common.mapper;

import ru.avm.lib.common.dto.ExceptionDto;

public interface ExceptionMapper {
    ExceptionDto toDto(Throwable ex);
}
