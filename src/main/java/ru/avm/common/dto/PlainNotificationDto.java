package ru.avm.common.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PlainNotificationDto {
    @With
    String sid;
    String from;
    String title;
    String text;
    @Builder.Default
    Boolean noMail = false;
    @Builder.Default
    Boolean noBot = false;
}
