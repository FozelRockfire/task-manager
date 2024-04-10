package com.t1study.taskmanager.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorMessage(

        int statusCode,
        String description,
        LocalDateTime currentTime
) {
}
