package ru.avm.lib.common.mapper;

import ru.avm.lib.common.dto.ExceptionDto;

//@Component
//@Mapper(componentModel = "spring")
public interface ExceptionMapper {

    //    @Value("${spring.application.name}")
//    public String applicationName;
//
//    @Mapping(target = "source", constant = "java(applicationName)")
//    @Mapping(target = "exception", expression = "java( ex.getClass().getSimpleName() )")
    ExceptionDto toDto(Throwable ex);
}
