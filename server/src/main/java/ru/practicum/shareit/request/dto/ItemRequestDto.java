package ru.practicum.shareit.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class ItemRequestDto {
    long id;

    String description;

    @JsonProperty("requestor")
    long userId;

    @JsonProperty("created")
    LocalDateTime creationDate;
}