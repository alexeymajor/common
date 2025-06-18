package ru.avm.common.dto;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Value
@Builder
@Jacksonized
public class PlainNotificationDto {
    @With
    String sid;
    @With
    String group;
    @With
    String phone;
    String from;
    String title;
    String text;
    String template;
    @Singular("param")
    Map<String, Object> params;
    @Builder.Default
    Boolean noMail = false;
    @Builder.Default
    Boolean noBot = false;
}
