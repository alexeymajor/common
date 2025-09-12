package ru.avm.lib.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.avm.lib.common.dto.ExceptionDto;
import ru.avm.lib.common.mapper.ExceptionMapper;

@Configuration
public class ExceptionMapperConfig {

    private HttpServletRequest getCurrentRequest() {
        val attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest();
    }

    @Bean
    public ExceptionMapper exceptionMapper(@Value("${spring.application.name:unknown}") String applicationName) {
        return ex -> ExceptionDto.builder()
                .message(ex.getMessage())
                .source(applicationName)
                .exception(ex.getClass().getSimpleName())
                .uri(getCurrentRequest().getRequestURI())
                .build();
    }
}
